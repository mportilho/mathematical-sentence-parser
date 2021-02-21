package io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class LogarithmOperation extends AbstractBinaryOperation {

	public LogarithmOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal leftResult = BigDecimalMath.log(getLeftOperand().evaluate(context), context.getMathContext());
		BigDecimal rightResult = BigDecimalMath.log(getRightOperand().evaluate(context), context.getMathContext());
		return rightResult.divide(leftResult, context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "log";
	}

}
