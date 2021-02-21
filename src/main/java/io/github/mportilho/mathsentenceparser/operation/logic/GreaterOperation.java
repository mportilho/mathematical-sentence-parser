package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class GreaterOperation extends AbstractBinaryOperation {

	public GreaterOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getLeftOperand().<Comparable<?>>evaluate(context).compareTo(getRightOperand().evaluate(context)) > 0;
	}

	@Override
	protected String getOperationToken() {
		return ">";
	}

}
