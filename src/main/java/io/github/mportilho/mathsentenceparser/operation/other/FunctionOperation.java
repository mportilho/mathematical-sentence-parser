package io.github.mportilho.mathsentenceparser.operation.other;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class FunctionOperation extends AbstractOperation {

	private String functionName;
	private List<AbstractOperation> parameters;
	private boolean noCache = false;
	private String functionKey;

	public FunctionOperation(String functionName, List<AbstractOperation> parameters, boolean noCache) {
		this.functionName = functionName;
		this.parameters = parameters;
		this.noCache = noCache;
		this.functionKey = functionName + parameters.size();

		if (parameters == null || parameters.isEmpty()) {
			this.parameters = Collections.emptyList();
		} else {
			for (AbstractOperation operation : this.parameters) {
				operation.addParent(this);
			}
		}
		if (noCache) {
			this.caching(false);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		int count = this.parameters.size();
		Object[] args = new Object[count];
		for (int i = 0; i < count; i++) {
			args[i] = this.parameters.get(i).evaluate(context);
		}

		Function<Object[], Object> function = context.getExternalFunctions().get(functionKey);
		if (function == null) {
			throw new IllegalStateException(String.format("Function '%s' not found", functionName));
		}
		Object response = function.apply(args);

		if (response instanceof Number) {
			return new BigDecimal(response.toString(), context.getMathContext());
		} else if (response instanceof Date) {
			return LocalDateTime.ofInstant(((Date) response).toInstant(), ZoneId.systemDefault());
		}
		return response;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		List<AbstractOperation> newOperationList = new ArrayList<>();
		for (AbstractOperation param : parameters) {
			newOperationList.add(param.copy(context));
		}
		return new FunctionOperation(this.functionName, newOperationList, this.noCache);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public boolean isNoCache() {
		return noCache;
	}

	public List<AbstractOperation> getParameters() {
		return parameters;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
