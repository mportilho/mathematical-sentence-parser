package io.github.mportilho.mathsentenceparser.operation.precise.math;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestBasicOperations {

	ParsingContext context = new ParsingContext();

	@Test
	public void testPreciseAditionOperation() {
		PreciseAditionOperation operation;

		operation = new PreciseAditionOperation(new GenericValueOperation(BigDecimal.ZERO), new GenericValueOperation(BigDecimal.ZERO));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseAditionOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseAditionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseAditionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-2");

		operation = new PreciseAditionOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
	}

	@Test
	public void testPreciseDivisionOperation() {
		PreciseSubtractionOperation operation;

		operation = new PreciseSubtractionOperation(new GenericValueOperation(BigDecimal.ZERO), new GenericValueOperation(BigDecimal.ZERO));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseSubtractionOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseSubtractionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-2");

		operation = new PreciseSubtractionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseSubtractionOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-1");
	}

	@Test
	public void testPreciseMultiplicationOperation() {
		PreciseMultiplicationOperation operation;

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(BigDecimal.ZERO), new GenericValueOperation(BigDecimal.ZERO));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-1");

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testPreciseSubtractionOperation() {
		PreciseDivisionOperation operation;

		PreciseDivisionOperation divisionByZeroOperation = new PreciseDivisionOperation(new GenericValueOperation(BigDecimal.ZERO),
				new GenericValueOperation(BigDecimal.ZERO));
		assertThatThrownBy(() -> divisionByZeroOperation.<BigDecimal>evaluate(context)).isInstanceOf(ArithmeticException.class);

		operation = new PreciseDivisionOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseDivisionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-1");

		operation = new PreciseDivisionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseDivisionOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.5");
	}

}
