package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class OrOperation extends AbstractBinaryOperation {

	public OrOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getLeftOperand().<Boolean>evaluate(context) || getRightOperand().<Boolean>evaluate(context);
	}

	@Override
	protected String getOperationToken() {
		return "or";
	}

}
