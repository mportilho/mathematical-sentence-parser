package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class DateConstantValueOperation extends AbstractConstantValueOperation {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
		return FORMATTER.parse(getValue().toString(), LocalDate::from);
	}

}
