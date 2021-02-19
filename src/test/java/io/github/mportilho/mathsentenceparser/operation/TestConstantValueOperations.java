package io.github.mportilho.mathsentenceparser.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.BooleanConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.DateConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.DateTimeConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.EulerNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PiNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.StringConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.TimeConstantValueOperation;

public class TestConstantValueOperations {

	OperationContext context = new OperationContext();

	@Test
	public void testBooleanConstantValueOperation() {
		AbstractOperation operation;

		operation = new BooleanConstantValueOperation("true");
		assertThat((Boolean) operation.evaluate(context)).isTrue();
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());

		operation = new BooleanConstantValueOperation("false");
		assertThat((Boolean) operation.evaluate(context)).isFalse();
	}

	@Test
	public void testDateConstantValueOperation() {
		AbstractOperation operation;

		operation = new DateConstantValueOperation("12/04/2001");
		assertThat((LocalDate) operation.evaluate(context)).isEqualTo(LocalDate.of(2001, 4, 12));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());
	}

	@Test
	public void testTimeConstantValueOperation() {
		AbstractOperation operation;

		operation = new TimeConstantValueOperation("04:12:33");
		assertThat((LocalTime) operation.evaluate(context)).isEqualTo(LocalTime.of(4, 12, 33));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());
	}

	@Test
	public void testDateTimeConstantValueOperation() {
		AbstractOperation operation;

		operation = new DateTimeConstantValueOperation("12/04/2001-04:12:33");
		assertThat((LocalDateTime) operation.evaluate(context)).isEqualTo(LocalDateTime.of(2001, 4, 12, 4, 12, 33));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());
	}

	@Test
	public void testEulerNumberConstantValueOperation() {
		AbstractOperation operation;

		operation = new EulerNumberConstantValueOperation();
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(2.718281828459045));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());
	}

	@Test
	public void testPiNumberConstantValueOperation() {
		AbstractOperation operation;

		operation = new PiNumberConstantValueOperation();
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(3.141592653589793));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());
	}

	@Test
	public void testPreciseNumberConstantValueOperation() {
		AbstractOperation operation;

		operation = new PreciseNumberConstantValueOperation("3.141592653589793");
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(3.141592653589793));
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());

		operation = new PreciseNumberConstantValueOperation("-3.141592653589793");
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(-3.141592653589793));

		operation = new PreciseNumberConstantValueOperation("0");
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(0));
	}

	@Test
	public void testStringConstantValueOperation() {
		AbstractOperation operation;

		operation = new StringConstantValueOperation("3.141592653589793");
		assertThat((String) operation.evaluate(context)).isEqualTo("3.141592653589793");
		assertThat(((AbstractConstantValueOperation) operation).isCachingForever());

		operation = new StringConstantValueOperation("-3.141592653589793");
		assertThat((String) operation.evaluate(context)).isEqualTo("-3.141592653589793");

		operation = new StringConstantValueOperation("AB.CD-e");
		assertThat((String) operation.evaluate(context)).isEqualTo("AB.CD-e");
	}

}
