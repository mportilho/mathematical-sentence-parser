package io.github.mportilho.mathsentenceparser.operation.datetime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestTimeOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testTimeAdditionOperation() {
		TimeAdditionOperation operation;

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
	}

	@Test
	public void testTimeSetOperation() {
		TimeSetOperation operation;

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");
	}

	@Test
	public void testTimeSubtractionOperation() {
		TimeSubtractionOperation operation;

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");
	}

}
