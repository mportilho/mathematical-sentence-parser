package io.github.mportilho.mathsentenceparser.operation.datetime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestDateTimeOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testDateTimeAdditionOperation() {
		DateTimeAdditionOperation operation;

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateTimeAdditionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
	}

	@Test
	public void testDateTimeSetOperation() {
		DateTimeSetOperation operation;

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateTimeSetOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 25, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");
	}

	@Test
	public void testDateTimeSubtractionOperation() {
		DateTimeSubtractionOperation operation;

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateTimeSubtractionOperation(new GenericValueOperation(LocalDateTime.of(2000, 3, 2, 23, 55, 50)),
				new GenericValueOperation(valueOf(2)), DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");
	}
}
