package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.MathSentenceComputingException;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractOperation {

	private Object cache;
	private boolean caching;
	private boolean cachingForever;

	private List<AbstractOperation> parents;
	private static Map<Class<?>, CallSite> constructors;

	private boolean applyingParenthesis;

	public AbstractOperation() {
		this.caching = true;
	}

	protected abstract Object resolve(OperationContext context);

	protected abstract AbstractOperation createClone(CloningContext context) throws Throwable;

	protected abstract String getOperationToken();

	public abstract <T> T accept(OperationVisitor<T> visitor);

	protected abstract void composeTextualRepresentation(StringBuilder builder);

	public final void generateRepresentation(StringBuilder builder) {
		if (this.applyingParenthesis) {
			builder.append('(');
			composeTextualRepresentation(builder);
			builder.append(')');
		} else {
			composeTextualRepresentation(builder);
		}
	}

	public final AbstractOperation copy(CloningContext cloningContext) throws Throwable {
		AbstractOperation copy = null;
		if (!cloningContext.getClonedOperationMap().keySet().contains(this)) {
			copy = createClone(cloningContext).copyAtributes(this);
			cloningContext.getClonedOperationMap().put(this, copy);
		} else {
			copy = cloningContext.getClonedOperationMap().get(this);
		}
		return copy;
	}

	@SuppressWarnings("unchecked")
	public final <T> T evaluate(OperationContext context) {
		Object result;
		try {
			result = readValue(context);
		} catch (ClassCastException e) {
			throw new IllegalStateException(String.format("Wrong operands type for expression %s", toString()), e);
		} catch (ArithmeticException e) {
			ArithmeticException newException = new ArithmeticException(
					String.format("Arithmetic error for expression %s: %s", toString(), e.getMessage()));
			newException.setStackTrace(e.getStackTrace());
			throw newException;
		} catch (MathSentenceComputingException e) {
			throw new IllegalStateException(e.getMessage(), e.getCause());
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Error during calculation of expression %s", toString()), e);
		}
		if (result == null && !context.isAllowingNull()) {
			if (this instanceof AbstractVariableValueOperation) {
				throw new IllegalArgumentException(String.format("The variable \"%s\" does not have any provided value", this.toString()));
			} else {
				throw new NullPointerException(String.format("Not expected null value for expression %s ", toString()));
			}
		}
		return (T) result;
	}

	private Object readValue(OperationContext context) {
		Object result = null;
		if (caching || cachingForever) {
			if (cache == null) {
				result = resolve(context); // resolve method can disable cache
				cache = caching || cachingForever ? result : null;
			} else {
				result = cache;
			}
		} else {
			result = resolve(context);
		}
		return result;
	}

	private final AbstractOperation copyAtributes(AbstractOperation sourceOperation) {
		this.cache = sourceOperation.cache;
		this.caching = sourceOperation.caching;
		this.cachingForever = sourceOperation.cachingForever;
		this.applyingParenthesis = sourceOperation.applyingParenthesis;
		return this;
	}

	protected CallSite cacheCopingFunction(Class<?> clazz, CallSiteSupplier supplier) throws Throwable {
		if (constructors == null) {
			constructors = new HashMap<>();
		}
		CallSite cachedCallSite = constructors.get(clazz);
		if (cachedCallSite == null) {
			cachedCallSite = supplier.get(clazz);
			constructors.put(clazz, cachedCallSite);
		}
		return cachedCallSite;
	}

	public void applyingParenthesis() {
		this.applyingParenthesis = true;
	}

	public void addParent(AbstractOperation operation) {
		if (operation == null) {
			return;
		}
		if (parents == null) {
			parents = new ArrayList<>();
		}
		parents.add(operation);
	}

	public void caching(boolean value) {
		this.caching = value;
	}

	protected boolean isCaching() {
		return caching || cachingForever;
	}

	protected void cachingForever() {
		this.cachingForever = true;
	}

	protected boolean isCachingForever() {
		return cachingForever;
	}

	protected void clearCache() {
		clearCache(null);
	}

	protected final void clearCache(Set<Class<? extends AbstractOperation>> stopOnOperationTypes) {
		if (!isCaching() || this.cache == null) {
			return;
		}
		this.cache = null;
		if (parents != null) {
			int size = parents.size();
			for (int i = 0; i < size; i++) {
				AbstractOperation currParent = parents.get(i);
				if (currParent.cache != null && (stopOnOperationTypes == null || !stopOnOperationTypes.contains(getClass()))) {
					currParent.clearCache(stopOnOperationTypes);
				}
			}
		}
	}

	public Object getCache() {
		return cache;
	}

	protected boolean isApplyingParenthesis() {
		return applyingParenthesis;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		generateRepresentation(builder);
		return builder.toString();
	}

	protected interface CallSiteSupplier {
		CallSite get(Class<?> clazz) throws Throwable;
	}

}
