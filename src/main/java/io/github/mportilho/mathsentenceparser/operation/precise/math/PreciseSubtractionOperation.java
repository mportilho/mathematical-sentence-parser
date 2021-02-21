package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseSubtractionOperation extends AbstractBinaryOperation {

	public PreciseSubtractionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getLeftOperand().<BigDecimal>evaluate(context).subtract(getRightOperand().<BigDecimal>evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "-";
	}

}
