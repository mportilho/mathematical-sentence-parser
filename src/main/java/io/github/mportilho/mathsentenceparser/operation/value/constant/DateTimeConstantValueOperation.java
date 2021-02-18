package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class DateTimeConstantValueOperation extends AbstractConstantValueOperation {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");

	public DateTimeConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return FORMATTER.parse(getValue().toString(), LocalDateTime::from);
	}

}
