package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalTime;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class TimeSetOperation extends AbstractDateTimeOperation {

	public TimeSetOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalTime leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case HOUR:
			return leftResult.withHour(rightResult.intValue());
		case MINUTE:
			return leftResult.withMinute(rightResult.intValue());
		case SECOND:
			return leftResult.withSecond(rightResult.intValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return "set";
	}

}
