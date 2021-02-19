package io.github.mportilho.mathsentenceparser.operation.precise.math;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestComplexOperations {

	OperationContext context = new OperationContext();

	@Test
	public void testExponentialOperation() {
		PreciseExponentialOperation operation;

		operation = new PreciseExponentialOperation(new GenericValueOperation(BigDecimal.ZERO), new GenericValueOperation(BigDecimal.ZERO));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("16");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(-3)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.125");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(-2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.25");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.5");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(0)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");

		operation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(3)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("8");
	}

	@Test
	public void testFactorialOperation() {
		PreciseFactorialOperation operation;

		PreciseFactorialOperation throwsExceptionOperation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(-1)));
		assertThatThrownBy(() -> throwsExceptionOperation.<BigDecimal>evaluate(context)).isInstanceOf(ArithmeticException.class);

		operation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(0)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(3)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("6");

		operation = new PreciseFactorialOperation(new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("24");
	}

	@Test
	public void testSummationOperation() {
		throw new IllegalStateException();
	}

	@Test
	public void testProductOfSequenceOperation() {
		throw new IllegalStateException();
	}

}
