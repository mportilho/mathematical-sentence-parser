package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class DateTimeSubtractionOperation extends AbstractDateTimeOperation {

	public DateTimeSubtractionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalDateTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case DAY:
			return leftResult.minusDays(rightResult.longValue());
		case HOUR:
			return leftResult.minusHours(rightResult.longValue());
		case MINUTE:
			return leftResult.minusMinutes(rightResult.longValue());
		case MONTH:
			return leftResult.minusMonths(rightResult.longValue());
		case SECOND:
			return leftResult.minusSeconds(rightResult.longValue());
		case YEAR:
			return leftResult.minusYears(rightResult.longValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
