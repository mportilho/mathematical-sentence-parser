package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class DateTimeConstantValueOperation extends AbstractConstantValueOperation {

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
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(getValue().toString(), LocalDateTime::from);
	}

}
