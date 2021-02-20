package io.github.mportilho.mathsentenceparser.operation.precise.math;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseNumberRoundingOperation.RoundingEnum;
import io.github.mportilho.mathsentenceparser.operation.value.variable.SequenceVariableValueOperation;

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
	public void testModuloOperation() {
		PreciseModuloOperation operation;

		operation = new PreciseModuloOperation(new GenericValueOperation(valueOf(4)), new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseModuloOperation(new GenericValueOperation(valueOf(8)), new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseModuloOperation(new GenericValueOperation(valueOf(3)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");

		operation = new PreciseModuloOperation(new GenericValueOperation(valueOf(5)), new GenericValueOperation(valueOf(3)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testModulusOperation() {
		PreciseModulusOperation operation;

		operation = new PreciseModulusOperation(new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");

		operation = new PreciseModulusOperation(new GenericValueOperation(valueOf(-4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
	}

	@Test
	public void testNegativeOperation() {
		PreciseNegativeOperation operation;

		operation = new PreciseNegativeOperation(new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-4");
		assertThat(operation.isNegatingValue()).isTrue();

		operation = new PreciseNegativeOperation(new GenericValueOperation(valueOf(-4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
		assertThat(operation.isNegatingValue()).isTrue();

		operation = new PreciseNegativeOperation(new GenericValueOperation(valueOf(4)));
		operation.negatingValue(false);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
		assertThat(operation.isNegatingValue()).isFalse();

		operation = new PreciseNegativeOperation(new GenericValueOperation(valueOf(-4)));
		operation.negatingValue(false);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-4");
		assertThat(operation.isNegatingValue()).isFalse();
	}

	@Test
	public void testNumberRoundingOperation() {
		PreciseNumberRoundingOperation operation;

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.CEILING);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.36");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.DOWN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.FLOOR);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_DOWN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.354333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_EVEN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.3540)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_EVEN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.355333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_EVEN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.36");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.356333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_EVEN);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.36");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.HALF_UP);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.35");

		operation = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)), new GenericValueOperation(valueOf(2)),
				RoundingEnum.UP);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.36");

		PreciseNumberRoundingOperation operationThrowing = new PreciseNumberRoundingOperation(new GenericValueOperation(valueOf(0.353333)),
				new GenericValueOperation(valueOf(2)), RoundingEnum.UNNECESSARY);
		assertThatThrownBy(() -> operationThrowing.<BigDecimal>evaluate(context)).isInstanceOf(ArithmeticException.class);
	}

	@Test
	public void testPrecisePercentualOperation() {
		PrecisePercentualOperation operation;

		operation = new PrecisePercentualOperation(new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0.04");

		operation = new PrecisePercentualOperation(new GenericValueOperation(valueOf(-4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-0.04");

		operation = new PrecisePercentualOperation(new GenericValueOperation(valueOf(0)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PrecisePercentualOperation(new GenericValueOperation(valueOf(100)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");
	}

	@Test
	public void testPreciseRootOperation() {
		PreciseRootOperation operation;

		operation = new PreciseRootOperation(new GenericValueOperation(valueOf(4)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseRootOperation(new GenericValueOperation(valueOf(144)), new GenericValueOperation(valueOf(2)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("12");

		operation = new PreciseRootOperation(new GenericValueOperation(valueOf(64)), new GenericValueOperation(valueOf(3)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
	}

	@Test
	public void testPreciseSquareRootOperation() {
		PreciseSquareRootOperation operation;

		operation = new PreciseSquareRootOperation(new GenericValueOperation(valueOf(4)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseSquareRootOperation(new GenericValueOperation(valueOf(144)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("12");
	}

	@Test
	public void testSummationOperation() {
		GenericValueOperation startIndex = new GenericValueOperation(valueOf(0));
		GenericValueOperation endIndex = new GenericValueOperation(valueOf(2));
		SequenceVariableValueOperation sequenceVariable;
		AbstractOperation summationOperation;
		AbstractOperation operation;
		///////////////////////

		sequenceVariable = new SequenceVariableValueOperation("S");
		summationOperation = new PreciseExponentialOperation(new GenericValueOperation(valueOf(2)), sequenceVariable);
		operation = new PreciseSummationOperation(startIndex, endIndex, summationOperation, sequenceVariable);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("7");

		sequenceVariable = new SequenceVariableValueOperation("S");
		summationOperation = new PreciseExponentialOperation(new PreciseAdditionOperation(new GenericValueOperation(valueOf(2)), sequenceVariable),
				sequenceVariable);
		operation = new PreciseSummationOperation(startIndex, endIndex, summationOperation, sequenceVariable);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("20");

	}

	@Test
	public void testProductOfSequenceOperation() {
		GenericValueOperation startIndex = new GenericValueOperation(valueOf(0));
		GenericValueOperation endIndex = new GenericValueOperation(valueOf(2));
		SequenceVariableValueOperation sequenceVariable;
		AbstractOperation productOfSequenceOperation;
		AbstractOperation operation;

		sequenceVariable = new SequenceVariableValueOperation("P");
		productOfSequenceOperation = new PreciseExponentialOperation(
				new PreciseAdditionOperation(new GenericValueOperation(valueOf(2)), sequenceVariable), sequenceVariable);
		operation = new PreciseProductOfSequenceOperation(startIndex, endIndex, productOfSequenceOperation, sequenceVariable);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("48");
	}

}
