package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.WarmUpOperationVisitor;

public class OperationSyntaxTree {

	private AbstractOperation operation;
	private OperationSyntaxTreeContext syntaxTreeContext;
	private OperationContext operationContext;

	public OperationSyntaxTree(AbstractOperation operation, OperationSyntaxTreeContext syntaxTreeContext) {
		super();
		this.operation = operation;
		this.syntaxTreeContext = syntaxTreeContext;
	}

	void setOperationContext(OperationContext operationContext) {
		this.operationContext = operationContext;
	}

	public Object compute() {
		operationContext.clearContext();
		return operation.evaluate(operationContext);
	}

	public void warmUp(OperationContext context) {
		boolean originalAllowingNull = context.isAllowingNull();
		try {
			context.setAllowingNull(true);
			visitOperation(new WarmUpOperationVisitor(context));
		} finally {
			context.setAllowingNull(originalAllowingNull);
		}
	}

	public void provideValue(String variableName, Object value) {
		try {
			if (value instanceof Integer) {
				syntaxTreeContext.getProvidedVariables().get(variableName).provideNewValue(new BigDecimal((Integer) value));
			} else if (value instanceof Long) {
				syntaxTreeContext.getProvidedVariables().get(variableName).provideNewValue(BigDecimal.valueOf((Long) value));
			} else if (value instanceof Number && !(value instanceof BigDecimal)) {
				syntaxTreeContext.getProvidedVariables().get(variableName).provideNewValue(new BigDecimal(value.toString()));
			} else if (value instanceof Date) {
				syntaxTreeContext.getProvidedVariables().get(variableName)
						.provideNewValue(LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()));
			} else {
				syntaxTreeContext.getProvidedVariables().get(variableName).provideNewValue(value);
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
			OperationSyntaxTree syntaxTree = new OperationSyntaxTree(copy, grammarBaseVisitorContext);
			syntaxTree.operationContext = this.operationContext;
			return syntaxTree;
		} catch (Throwable e) {
			throw new IllegalStateException("Error while coping operation syntax tree", e);
		}
	}

	public <T> T visitOperation(OperationVisitor<T> visitor) {
		return operation.accept(visitor);
	}

}