package io.github.mportilho.mathsentenceparser.operation.datetime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestTimeOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testTimeAdditionOperation() {
		TimeAdditionOperation operation;

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("01:55:50");

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:57:50");

		operation = new TimeAdditionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:55:52");
	}

	@Test
	public void testTimeSetOperation() {
		TimeSetOperation operation;

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("02:55:50");

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:02:50");

		operation = new TimeSetOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:55:02");
	}

	@Test
	public void testTimeSubtractionOperation() {
		TimeSubtractionOperation operation;

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.HOUR);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("21:55:50");

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MINUTE);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:53:50");

		operation = new TimeSubtractionOperation(new GenericValueOperation(LocalTime.of(23, 55, 50)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.SECOND);
		assertThat(operation.<LocalTime>evaluate(context)).isEqualTo("23:55:48");
	}

}
