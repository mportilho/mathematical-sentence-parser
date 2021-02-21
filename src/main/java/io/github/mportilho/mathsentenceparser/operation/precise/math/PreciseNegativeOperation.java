package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseNegativeOperation extends AbstractUnaryOperator {

	private boolean negatingValue = true;

	public PreciseNegativeOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.LEFT);
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (negatingValue) {
			return getOperand().<BigDecimal>evaluate(context).negate(context.getMathContext());
		}
		return getOperand().evaluate(context);
	}

	public void negatingValue(boolean negating) {
		this.negatingValue = negating;
	}

	public boolean isNegatingValue() {
		return negatingValue;
	}

	@Override
	protected String getOperationToken() {
		return negatingValue ? "-" : "";
	}

}
