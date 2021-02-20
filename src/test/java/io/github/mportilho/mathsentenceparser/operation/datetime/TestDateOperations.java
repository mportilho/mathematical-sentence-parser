package io.github.mportilho.mathsentenceparser.operation.datetime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestDateOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testDateAdditionOperation() {
		DateAdditionOperation operation;

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
	}

	@Test
	public void testDateSetOperation() {
		DateSetOperation operation;

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 25)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
	}

	@Test
	public void testDateSubtractionOperation() {
		DateSubtractionOperation operation;

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
	}

}
