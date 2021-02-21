package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalTime;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class TimeSubtractionOperation extends AbstractDateTimeOperation {

	public TimeSubtractionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case HOUR:
			return leftResult.minusHours(rightResult.longValue());
		case MINUTE:
			return leftResult.minusMinutes(rightResult.longValue());
		case SECOND:
			return leftResult.minusSeconds(rightResult.longValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return "minus";
	}

}
