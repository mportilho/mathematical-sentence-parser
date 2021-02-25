/*MIT License

Copyright (c) 2021 Marcelo Portilho

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

package io.github.mportilho.mathsentenceparser.operation.precise.math;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestBasicMathOperations {

	OperationContext context = new OperationContext();

	@Test
	public void testPreciseAditionOperation() {
		PreciseAdditionOperation operation;

		operation = new PreciseAdditionOperation(new GenericValueOperation(BigDecimal.ZERO), new GenericValueOperation(BigDecimal.ZERO));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseAdditionOperation(new GenericValueOperation(BigDecimal.ONE), new GenericValueOperation(BigDecimal.ONE));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");

		operation = new PreciseAdditionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		operation = new PreciseAdditionOperation(new GenericValueOperation(valueOf(-1)), new GenericValueOperation(valueOf(-1)));
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("-2");

		operation = new PreciseAdditionOperation(new GenericValueOperation(valueOf(1)), new GenericValueOperation(valueOf(2)));
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
	public void testPreciseImplicitMultiplicationOperation() {
		PreciseMultiplicationOperation operation;

		operation = new PreciseMultiplicationOperation(new GenericValueOperation(valueOf(2)), new GenericValueOperation(valueOf(3)), true);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("6");
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
