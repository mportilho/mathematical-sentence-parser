package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class XnorOperation extends AbstractBinaryOperation {

	public XnorOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return !(getLeftOperand().<Boolean>evaluate(context) ^ getRightOperand().<Boolean>evaluate(context));
	}

	@Override
	protected String getOperationToken() {
		return "xnor";
	}

}
