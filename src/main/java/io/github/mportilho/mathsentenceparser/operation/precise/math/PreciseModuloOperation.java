package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class PreciseModuloOperation extends AbstractBinaryOperation {

	public PreciseModuloOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal leftValue = getLeftOperand().evaluate(context);
		BigDecimal rightValue = getRightOperand().evaluate(context);
		return leftValue.remainder(rightValue, context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "mod";
	}

}
