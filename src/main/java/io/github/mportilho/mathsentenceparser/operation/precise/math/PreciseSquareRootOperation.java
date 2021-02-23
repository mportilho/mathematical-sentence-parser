package io.github.mportilho.mathsentenceparser.operation.precise.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseSquareRootOperation extends AbstractUnaryOperator {

	public PreciseSquareRootOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.FUNCTION);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.sqrt(getOperand().evaluate(context), context.getMathContext());
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		builder.append(2);
		builder.append(getOperationToken()).append('(');
		getOperand().generateRepresentation(builder);
		builder.append(')');
	}

	@Override
	protected String getOperationToken() {
		return "\u221A";
	}

}
