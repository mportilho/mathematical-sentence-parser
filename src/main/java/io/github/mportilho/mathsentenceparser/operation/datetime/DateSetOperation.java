package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class DateSetOperation extends AbstractDateTimeOperation {

	public DateSetOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);
	}

	@Override
	protected Object resolve(OperationContext context) {
		LocalDate leftResult = getLeftOperand().evaluate(context);
		BigDecimal rightResult = getRightOperand().evaluate(context);
		switch (getDateElement()) {
		case DAY:
			return leftResult.withDayOfMonth(rightResult.intValue());
		case MONTH:
			return leftResult.withMonth(rightResult.intValue());
		case YEAR:
			return leftResult.withYear(rightResult.intValue());
		default:
			throw new IllegalStateException("Date information not supported: " + getDateElement());
		}
	}

	@Override
	protected String getOperationToken() {
		return "set";
	}

}
