package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class DateConstantValueOperation extends AbstractConstantValueOperation {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public DateConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return FORMATTER.parse(getValue().toString(), LocalDate::from);
	}

}
