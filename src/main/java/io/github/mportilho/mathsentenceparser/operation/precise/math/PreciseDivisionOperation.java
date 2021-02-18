package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class PreciseDivisionOperation extends AbstractBinaryOperation {

	public PreciseDivisionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return getLeftOperand().<BigDecimal>evaluate(context).divide(getRightOperand().<BigDecimal>evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "/";
	}

}
