package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class NotEqualsOperation extends AbstractBinaryOperation {

	public NotEqualsOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		Object leftOperand = getLeftOperand().evaluate(context);
		if (leftOperand instanceof Comparable<?>) {
			return ((Comparable<?>) leftOperand).compareTo(getRightOperand().evaluate(context)) != 0;
		}
		return !leftOperand.equals(getRightOperand().evaluate(context));
	}

	@Override
	protected String getOperationToken() {
		return "<>";
	}

}
