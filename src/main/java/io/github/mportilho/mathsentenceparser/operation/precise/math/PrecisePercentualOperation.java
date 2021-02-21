package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PrecisePercentualOperation extends AbstractUnaryOperator {

	private static final BigDecimal PERCENT = new BigDecimal("0.01");

	public PrecisePercentualOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.RIGHT);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getOperand().<BigDecimal>evaluate(context).multiply(PERCENT, context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "%";
	}

}
