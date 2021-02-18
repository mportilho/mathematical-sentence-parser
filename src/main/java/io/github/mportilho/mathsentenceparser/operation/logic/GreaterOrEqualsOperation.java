package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class GreaterOrEqualsOperation extends AbstractBinaryOperation {

	public GreaterOrEqualsOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return getLeftOperand().<Comparable<?>>evaluate(context).compareTo(getRightOperand().evaluate(context)) >= 0;
	}

	@Override
	protected String getOperationToken() {
		return ">=";
	}

}
