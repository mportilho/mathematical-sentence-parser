package io.github.mportilho.mathsentenceparser.operation.precise.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseFactorialOperation extends AbstractUnaryOperator {

	public PreciseFactorialOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.RIGHT);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.factorial(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "!";
	}

}
