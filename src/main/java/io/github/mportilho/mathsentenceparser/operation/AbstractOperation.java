package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public abstract class AbstractOperation {

	private Object cache;
	private boolean caching;

	private List<AbstractOperation> parents;
	private static Map<Class<?>, CallSite> constructors;

	private boolean applyingParenthesis;

	protected abstract CallSite createCloningMethodHandle() throws Throwable;

	protected final CallSite cloneOperation() throws Throwable {
		if (constructors == null) {
			constructors = new WeakHashMap<>();
		}
		Class<?> clazz = getClass();
		if (!constructors.containsKey(clazz)) {
			constructors.put(getClass(), createCloningMethodHandle());
		}
		return constructors.get(clazz);
	}

	public abstract Object resolve(ParsingContext context);

	@SuppressWarnings("unchecked")
	public <T> T evaluate(ParsingContext context) {
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
		} catch (ParsingSentenceExpression e) {
			throw new IllegalStateException(e.getMessage(), e.getCause());
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Error during calculation of expression %s", toString()), e);
		}
		if (result == null && !context.getOptions().isAllowingNull()) {
			if (this instanceof AbstractVariableOperation) {
				throw new IllegalArgumentException(String.format("A variável \"%s\" não possui valor atribuído", this.toString()));
			} else {
				throw new NullPointerException(String.format("Not expected null value for expression %s ", toString()));
			}
		}
		return (T) result;
	}

	private Object readValue(ParsingContext context) {
		Object result = null;
		if (caching) {
			if (cache == null) {
				result = resolve(context);
				cache = caching ? result : null;
			} else {
				result = cache;
			}
		} else {
			result = resolve(context);
		}
		return result;
	}

	void applyingParenthesis() {
		this.applyingParenthesis = true;
	}

	void addParent(AbstractOperation operation) {
		if (parents == null) {
			parents = new ArrayList<>();
		}
		parents.add(operation);
	}

	public boolean isCaching() {
		return caching;
	}

	public boolean isApplyingParenthesis() {
		return applyingParenthesis;
	}

}
