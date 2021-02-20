package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class DateTimeSetOperation extends AbstractDateTimeOperation {

	public DateTimeSetOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalDateTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case DAY:
			return leftResult.withDayOfMonth(rightResult.intValue());
		case HOUR:
			return leftResult.withHour(rightResult.intValue());
		case MINUTE:
			return leftResult.withMinute(rightResult.intValue());
		case MONTH:
			return leftResult.withMonth(rightResult.intValue());
		case SECOND:
			return leftResult.withSecond(rightResult.intValue());
		case YEAR:
			return leftResult.withYear(rightResult.intValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
