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

package io.github.mportilho.mathsentenceparser.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

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
		AbstractConstantValueOperation operation;

		operation = new BooleanConstantValueOperation("true");
		assertThat((Boolean) operation.evaluate(context)).isTrue();
		assertThat(operation.isCachingForever()).isTrue();

		operation = new BooleanConstantValueOperation("false");
		assertThat((Boolean) operation.evaluate(context)).isFalse();
	}

	@Test
	public void testDateConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new DateConstantValueOperation("2001-04-12");
		assertThat((LocalDate) operation.evaluate(context)).isEqualTo(LocalDate.of(2001, 4, 12));
		assertThat(operation.isCachingForever()).isTrue();
	}

	@Test
	public void testTimeConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new TimeConstantValueOperation("04:12:33");
		assertThat((LocalTime) operation.evaluate(context)).isEqualTo(LocalTime.of(4, 12, 33));
		assertThat(operation.isCachingForever()).isTrue();
	}

	@Test
	public void testDateTimeConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new DateTimeConstantValueOperation("2001-04-12T04:12:33");
		assertThat((LocalDateTime) operation.evaluate(context)).isEqualTo(LocalDateTime.of(2001, 4, 12, 4, 12, 33));
		assertThat(operation.isCachingForever()).isTrue();
	}

	@Test
	public void testEulerNumberConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new EulerNumberConstantValueOperation();
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(2.718281828459045));
		assertThat(operation.isCachingForever()).isTrue();
	}

	@Test
	public void testPiNumberConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new PiNumberConstantValueOperation();
		assertThat((BigDecimal) operation.evaluate(context)).isEqualTo(BigDecimal.valueOf(3.141592653589793));
		assertThat(operation.isCachingForever()).isTrue();
	}

	@Test
	public void testPreciseNumberConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new PreciseNumberConstantValueOperation("3.141592653589793");
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualTo(BigDecimal.valueOf(3.141592653589793));
		assertThat(operation.isCachingForever()).isTrue();

		operation = new PreciseNumberConstantValueOperation("-3.141592653589793");
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualTo(BigDecimal.valueOf(-3.141592653589793));

		operation = new PreciseNumberConstantValueOperation("0");
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualTo(BigDecimal.valueOf(0));
	}

	@Test
	public void testStringConstantValueOperation() {
		AbstractConstantValueOperation operation;

		operation = new StringConstantValueOperation("3.141592653589793");
		assertThat((String) operation.evaluate(context)).isEqualTo("3.141592653589793");
		assertThat(operation.isCachingForever()).isTrue();

		operation = new StringConstantValueOperation("-3.141592653589793");
		assertThat((String) operation.evaluate(context)).isEqualTo("-3.141592653589793");

		operation = new StringConstantValueOperation("AB.CD-e");
		assertThat((String) operation.evaluate(context)).isEqualTo("AB.CD-e");
	}

}
