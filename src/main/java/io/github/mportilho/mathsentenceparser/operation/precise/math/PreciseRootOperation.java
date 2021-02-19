package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class PreciseRootOperation extends AbstractBinaryOperation {

	public PreciseRootOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal leftValue = getLeftOperand().evaluate(context);
		BigDecimal rightValue = getRightOperand().evaluate(context);
		return BigDecimalMath.root(leftValue, rightValue, context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "\u221A";
	}

}
