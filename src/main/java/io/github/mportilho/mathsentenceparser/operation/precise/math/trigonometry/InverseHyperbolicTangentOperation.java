package io.github.mportilho.mathsentenceparser.operation.precise.math.trigonometry;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;

public class InverseHyperbolicTangentOperation extends AbstractUnaryOperator {

	public InverseHyperbolicTangentOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.FUNCTION);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.atanh(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "atanh";
	}

}
