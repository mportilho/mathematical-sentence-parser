package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseExponentialOperation extends AbstractBinaryOperation {

	public PreciseExponentialOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal rightValue = getRightOperand().evaluate(context);
		if (rightValue.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ONE;
		}
		BigDecimal leftValue = getLeftOperand().evaluate(context);
		return BigDecimalMath.pow(leftValue, rightValue, context.getMathContext());
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		getLeftOperand().generateRepresentation(builder);
		builder.append(getOperationToken());
		getRightOperand().generateRepresentation(builder);
	}

	@Override
	protected String getOperationToken() {
		return "^";
	}

}
