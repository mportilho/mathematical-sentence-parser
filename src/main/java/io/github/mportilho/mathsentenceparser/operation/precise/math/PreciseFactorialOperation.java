package io.github.mportilho.mathsentenceparser.operation.precise.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;

public class PreciseFactorialOperation extends AbstractUnaryOperator {

	public PreciseFactorialOperation(AbstractOperation operand) {
		super(operand);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return BigDecimalMath.factorial(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "!";
	}

}
