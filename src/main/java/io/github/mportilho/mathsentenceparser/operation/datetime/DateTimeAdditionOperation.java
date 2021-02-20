package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class DateTimeAdditionOperation extends AbstractDateTimeOperation {

	public DateTimeAdditionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalDateTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case DAY:
			return leftResult.plusDays(rightResult.longValue());
		case HOUR:
			return leftResult.plusHours(rightResult.longValue());
		case MINUTE:
			return leftResult.plusMinutes(rightResult.longValue());
		case MONTH:
			return leftResult.plusMonths(rightResult.longValue());
		case SECOND:
			return leftResult.plusSeconds(rightResult.longValue());
		case YEAR:
			return leftResult.plusYears(rightResult.longValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return "plus";
	}

}
