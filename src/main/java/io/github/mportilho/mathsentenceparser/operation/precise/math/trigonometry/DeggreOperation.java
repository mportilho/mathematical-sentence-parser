package io.github.mportilho.mathsentenceparser.operation.precise.math.trigonometry;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class DeggreOperation extends AbstractUnaryOperator {

	private static final BigDecimal HALF_RADIUS = BigDecimal.valueOf(180l);

	public DeggreOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.RIGHT);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getOperand().<BigDecimal>evaluate(context).multiply(BigDecimalMath.pi(context.getMathContext())).divide(HALF_RADIUS,
				context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "°";
	}

}
