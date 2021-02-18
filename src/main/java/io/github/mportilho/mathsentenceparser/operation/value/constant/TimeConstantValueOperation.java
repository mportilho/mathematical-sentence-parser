package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class TimeConstantValueOperation extends AbstractConstantValueOperation {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	public TimeConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return FORMATTER.parse(getValue().toString(), LocalTime::from);
	}

}
