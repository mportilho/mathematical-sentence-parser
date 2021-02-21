package io.github.mportilho.mathsentenceparser.operation.precise.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseSquareRootOperation extends AbstractUnaryOperator {

	public PreciseSquareRootOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.FUNCTION);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.sqrt(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "sqrt";
	}

}
