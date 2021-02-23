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
	private String functionKey;

	public FunctionOperation(String functionName, List<AbstractOperation> parameters, boolean caching) {
		this.functionName = functionName;
		this.parameters = parameters != null ? parameters : Collections.emptyList();
		this.caching(caching);
		this.functionKey = functionName + this.parameters.size();

		int count = this.parameters.size();
		for (int i = 0; i < count; i++) {
			this.parameters.get(i).addParent(this);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		int count = this.parameters.size();
		Object[] args = new Object[count];
		for (int i = 0; i < count; i++) {
			args[i] = this.parameters.get(i).evaluate(context);
		}

		Function<Object[], Object> function;
		try {
			function = context.getExternalFunctions().get(functionKey);
		} catch (NullPointerException e) {
			throw new IllegalStateException("No functions loaded in this math sentence object", e);
		}
		if (function == null) {
			function = context.getExternalFunctions().get(functionName);
		}
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
		return new FunctionOperation(functionName, newOperationList, isCaching());
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		if (!isCaching()) {
			builder.append('~');
		}
		builder.append("f.").append(functionName).append("(");
		int count = parameters.size();
		int index = count;
		for (int i = 0; i < count; i++) {
			parameters.get(i).generateRepresentation(builder);
			if (--index != 0) {
				builder.append(", ");
			}
		}
		builder.append(")");
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public List<AbstractOperation> getParameters() {
		return parameters;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
