package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

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
	public void composeTextualRepresentation(StringBuilder builder) {
		getRightOperand().generateRepresentation(builder);
		builder.append(getOperationToken()).append('(');
		getLeftOperand().generateRepresentation(builder);
		builder.append(')');
	}

	@Override
	protected String getOperationToken() {
		return "\u221A";
	}

}
