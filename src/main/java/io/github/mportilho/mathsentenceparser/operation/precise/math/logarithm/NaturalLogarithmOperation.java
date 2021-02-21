package io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class NaturalLogarithmOperation extends AbstractUnaryOperator {

	public NaturalLogarithmOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.FUNCTION);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.log(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "ln";
	}

}
