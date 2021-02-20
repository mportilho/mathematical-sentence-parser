package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class DateConstantValueOperation extends AbstractConstantValueOperation {

	private boolean current;

	public DateConstantValueOperation(String value) {
		super(value);
	}

	public DateConstantValueOperation() {
		super("currDate");
		this.current = true;
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (current) {
			return context.getCurrentDateTime().toLocalDate();
		}
		return DateTimeFormatter.ISO_LOCAL_DATE.parse(getValue().toString(), LocalDate::from);
	}

}
