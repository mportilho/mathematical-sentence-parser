package io.github.mportilho.mathsentenceparser.operation.value.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.OperationContext;

public class TestConstantOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testBooleanConstantValueOperation() {
		assertThat(new BooleanConstantValueOperation("true").<Boolean>evaluate(context)).isTrue();
		assertThat(new BooleanConstantValueOperation("false").<Boolean>evaluate(context)).isFalse();
	}

	@Test
	public void testDateConstantValueOperation() {
		assertThat(new DateConstantValueOperation("2000-03-04").<LocalDate>evaluate(context)).isEqualTo("2000-03-04");
		assertThat(new DateConstantValueOperation().<LocalDate>evaluate(context)).isEqualTo(LocalDate.now());
	}

	@Test
	public void testDateTimeConstantValueOperation() {
		assertThat(new DateTimeConstantValueOperation("2000-03-04T23:33:44").<LocalDateTime>evaluate(context)).isEqualTo("2000-03-04T23:33:44");
		assertThat(new DateTimeConstantValueOperation().<LocalDateTime>evaluate(context).toLocalDate()).isEqualTo(LocalDate.now());
	}

	@Test
	public void testEulerNumberConstantValueOperation() {
		assertThat(new EulerNumberConstantValueOperation().<BigDecimal>evaluate(context))
				.isEqualByComparingTo(BigDecimalMath.e(context.getMathContext()));
	}

	@Test
	public void testPiNumberConstantValueOperation() {
		assertThat(new PiNumberConstantValueOperation().<BigDecimal>evaluate(context))
				.isEqualByComparingTo(BigDecimalMath.pi(context.getMathContext()));
	}

	@Test
	public void testPreciseNumberConstantValueOperation() {
		assertThat(new PreciseNumberConstantValueOperation("0").<BigDecimal>evaluate(context)).isEqualByComparingTo("0");
		assertThat(new PreciseNumberConstantValueOperation("-0.3").<BigDecimal>evaluate(context)).isEqualByComparingTo("-0.3");
		assertThat(new PreciseNumberConstantValueOperation("0.3").<BigDecimal>evaluate(context)).isEqualByComparingTo("0.3");
		assertThat(new PreciseNumberConstantValueOperation("0.0003").<BigDecimal>evaluate(context)).isEqualByComparingTo("0.0003");
		
		PreciseNumberConstantValueOperation operation = new PreciseNumberConstantValueOperation("-0.3");
		operation.transformToPositiveValue();
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.3");
	}

	@Test
	public void testStringConstantValueOperation() {
		assertThat(new StringConstantValueOperation("ABC").<String>evaluate(context)).isEqualTo("ABC");
		assertThat(new StringConstantValueOperation("123").<String>evaluate(context)).isEqualTo("123");
		assertThat(new StringConstantValueOperation("ABC.-123").<String>evaluate(context)).isEqualTo("ABC.-123");
	}

	@Test
	public void testTimeConstantValueOperation() {
		assertThat(new TimeConstantValueOperation("13:11:44").<LocalTime>evaluate(context)).isEqualTo("13:11:44");
		assertThat(new TimeConstantValueOperation("02:12:46").<LocalTime>evaluate(context)).isEqualTo("02:12:46");
		assertThat(new TimeConstantValueOperation("23:13:47").<LocalTime>evaluate(context)).isEqualTo("23:13:47");
		assertThat(new TimeConstantValueOperation().<LocalTime>evaluate(context).getHour()).isEqualTo(LocalTime.now().getHour());
	}

}
