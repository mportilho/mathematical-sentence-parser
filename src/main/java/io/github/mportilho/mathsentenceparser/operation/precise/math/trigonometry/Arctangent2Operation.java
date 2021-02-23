package io.github.mportilho.mathsentenceparser.operation.precise.math.trigonometry;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class Arctangent2Operation extends AbstractBinaryOperation {

	public Arctangent2Operation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.atan2(getLeftOperand().evaluate(context), getRightOperand().evaluate(context), context.getMathContext());
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		builder.append(getOperationToken()).append('(');
		getLeftOperand().generateRepresentation(builder);
		builder.append(", ");
		getRightOperand().generateRepresentation(builder);
		builder.append(')');
	}

	@Override
	protected String getOperationToken() {
		return "atan2";
	}

}
