package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalTime;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class TimeAdditionOperation extends AbstractDateTimeOperation {

	public TimeAdditionOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case HOUR:
			return leftResult.plusHours(rightResult.longValue());
		case MINUTE:
			return leftResult.plusMinutes(rightResult.longValue());
		case SECOND:
			return leftResult.plusSeconds(rightResult.longValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return "plus";
	}

}
