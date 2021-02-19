package io.github.mportilho.mathsentenceparser.parser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.visitor.WarmUpOperationVisitor;

public class OperationSyntaxTree {

	private AbstractOperation operation;
	private OperationSyntaxTreeContext context;

	public OperationSyntaxTree(AbstractOperation operation, OperationSyntaxTreeContext context) {
		super();
		this.operation = operation;
		this.context = context;
	}

	public Object compute(OperationContext context) {
		context.clearContext();
		return operation.evaluate(context);
	}

	public void warmUp(OperationContext context) {
		boolean originalAllowingNull = context.getOptions().isAllowingNull();
		try {
			context.getOptions().setAllowingNull(true);
			visitOperation(new WarmUpOperationVisitor(context));
		} finally {
			context.getOptions().setAllowingNull(originalAllowingNull);
		}
	}

	public void provideValue(String variableName, Object value) {
		try {
			if (value instanceof Integer) {
				context.getProvidedVariables().get(variableName).provideNewValue(new BigDecimal((Integer) value));
			} else if (value instanceof Long) {
				context.getProvidedVariables().get(variableName).provideNewValue(BigDecimal.valueOf((Long) value));
			} else if (value instanceof Number && !(value instanceof BigDecimal)) {
				context.getProvidedVariables().get(variableName).provideNewValue(new BigDecimal(value.toString()));
			} else if (value instanceof Date) {
				context.getProvidedVariables().get(variableName)
						.provideNewValue(LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()));
			} else {
				context.getProvidedVariables().get(variableName).provideNewValue(value);
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(
					String.format("Variable name '%s' not found on sentence or the parameter 'value' is null", variableName), e);
		}
	}

	public OperationSyntaxTree copy() {
		CloningContext cloningContext = new CloningContext();
		try {
			AbstractOperation copy = operation.copy(cloningContext);
			OperationSyntaxTreeContext grammarBaseVisitorContext = new OperationSyntaxTreeContext(cloningContext.getProvidedVariables(),
					cloningContext.getAssignedVariables());
			return new OperationSyntaxTree(copy, grammarBaseVisitorContext);
		} catch (Throwable e) {
			throw new IllegalStateException("Error while coping operation syntax tree", e);
		}
	}

	public <T> T visitOperation(OperationVisitor<T> visitor) {
		return operation.accept(visitor);
	}

	public AbstractOperation getOperation() {
		return operation;
	}

	public OperationSyntaxTreeContext getContext() {
		return context;
	}

}
