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

package io.github.mportilho.mathsentenceparser.operation.value.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

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
