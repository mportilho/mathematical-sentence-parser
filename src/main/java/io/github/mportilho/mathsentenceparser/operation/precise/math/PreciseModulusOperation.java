package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;

public class PreciseModulusOperation extends AbstractUnaryOperator {

	public PreciseModulusOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.WRAPPED);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getOperand().<BigDecimal>evaluate(context).abs(context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "|";
	}

}
