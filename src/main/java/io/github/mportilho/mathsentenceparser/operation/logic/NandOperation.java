package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class NandOperation extends AbstractBinaryOperation {

	public NandOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return !getLeftOperand().<Boolean>evaluate(context) || !getRightOperand().<Boolean>evaluate(context);
	}

	@Override
	protected String getOperationToken() {
		return "nand";
	}

}
