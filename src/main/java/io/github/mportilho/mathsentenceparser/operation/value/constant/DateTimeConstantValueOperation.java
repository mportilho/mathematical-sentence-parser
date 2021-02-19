package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class DateTimeConstantValueOperation extends AbstractConstantValueOperation {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");

	private boolean current;

	public DateTimeConstantValueOperation(String value) {
		super(value);
	}

	public DateTimeConstantValueOperation() {
		super("currDateTime");
		this.current = true;
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (current) {
			return context.getCurrentDateTime();
		}
		return FORMATTER.parse(getValue().toString(), LocalDateTime::from);
	}

}
