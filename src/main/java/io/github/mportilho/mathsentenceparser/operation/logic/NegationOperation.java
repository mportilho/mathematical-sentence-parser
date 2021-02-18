package io.github.mportilho.mathsentenceparser.operation.logic;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;

public class NegationOperation extends AbstractUnaryOperator {

	public NegationOperation(AbstractOperation operand) {
		super(operand);

	}

	@Override
	protected Object resolve(ParsingContext context) {
		return !getOperand().<Boolean>evaluate(context);
	}

	@Override
	protected String getOperationToken() {
		return "!";
	}

}
