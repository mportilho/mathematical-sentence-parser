package io.github.mportilho.mathsentenceparser.operation.precise.math.trigonometry;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class InverseHyperbolicCosineOperation extends AbstractUnaryOperator {

	public InverseHyperbolicCosineOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.FUNCTION);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.acosh(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "acosh";
	}

}
