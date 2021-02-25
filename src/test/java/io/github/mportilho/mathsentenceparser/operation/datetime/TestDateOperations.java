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

package io.github.mportilho.mathsentenceparser.operation.datetime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericValueOperation;

public class TestDateOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testDateAdditionOperation() {
		DateAdditionOperation operation;

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-04");

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-05-02");

		operation = new DateAdditionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2002-03-02");
	}

	@Test
	public void testDateSetOperation() {
		DateSetOperation operation;

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 25)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-03-02");

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-02");

		operation = new DateSetOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2020)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2020-03-02");
	}

	@Test
	public void testDateSubtractionOperation() {
		DateSubtractionOperation operation;

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.DAY);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-02-29");

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.MONTH);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("2000-01-02");

		operation = new DateSubtractionOperation(new GenericValueOperation(LocalDate.of(2000, 3, 2)), new GenericValueOperation(valueOf(2)),
				DateElementEnum.YEAR);
		assertThat(operation.<LocalDate>evaluate(context)).isEqualTo("1998-03-02");
	}

	@Test
	public void testDateTextualRepresentation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("2000-03-05 = 2000-03-05");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 = 2000-03-05");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 = 2000-03-05");

		mathSentence = new MathSentence("(2000-03-05) = 2000-03-05");
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05) = 2000-03-05");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05) = 2000-03-05");

		mathSentence = new MathSentence("2000-03-05 setDays 3 = 2000-03-05");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 setDays 3 = 2000-03-05");
		assertThat(mathSentence.<Boolean>compute()).isFalse();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 setDays 3 = 2000-03-05");

		mathSentence = new MathSentence("2000-03-05 setDays 3 setYears 2010 = 2010-03-03");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 setDays 3 setYears 2010 = 2010-03-03");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05 setDays 3 setYears 2010 = 2010-03-03");

		mathSentence = new MathSentence("(2000-03-05 setDays 3 setYears 2010) = 2010-03-03");
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05 setDays 3 setYears 2010) = 2010-03-03");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05 setDays 3 setYears 2010) = 2010-03-03");
	}

	@Test
	public void testTimeTextualRepresentation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("13:14:15 = 13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 = 13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 = 13:14:15");

		mathSentence = new MathSentence("(13:14:15) = 13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("(13:14:15) = 13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(13:14:15) = 13:14:15");

		mathSentence = new MathSentence("13:14:15 setHours 3 = 13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 setHours 3 = 13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isFalse();
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 setHours 3 = 13:14:15");

		mathSentence = new MathSentence("13:14:15 setHours 3 setSeconds 20 = 03:14:20");
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 setHours 3 setSeconds 20 = 03:14:20");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("13:14:15 setHours 3 setSeconds 20 = 03:14:20");

		mathSentence = new MathSentence("(13:14:15 setHours 3 setSeconds 20) = 03:14:20");
		assertThat(mathSentence.toString()).isEqualTo("(13:14:15 setHours 3 setSeconds 20) = 03:14:20");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(13:14:15 setHours 3 setSeconds 20) = 03:14:20");
	}

	@Test
	public void testDateTimeTextualRepresentation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("2000-03-05T13:14:15 = 2000-03-05T13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 = 2000-03-05T13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 = 2000-03-05T13:14:15");

		mathSentence = new MathSentence("(2000-03-05T13:14:15) = 2000-03-05T13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05T13:14:15) = 2000-03-05T13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05T13:14:15) = 2000-03-05T13:14:15");

		mathSentence = new MathSentence("2000-03-05T13:14:15 setDays 3 = 2000-03-05T13:14:15");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 setDays 3 = 2000-03-05T13:14:15");
		assertThat(mathSentence.<Boolean>compute()).isFalse();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 setDays 3 = 2000-03-05T13:14:15");

		mathSentence = new MathSentence("2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4 = 2010-03-03T13:18:15");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4 = 2010-03-03T13:18:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4 = 2010-03-03T13:18:15");

		mathSentence = new MathSentence("(2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4) = 2010-03-03T13:18:15");
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4) = 2010-03-03T13:18:15");
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentence.toString()).isEqualTo("(2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4) = 2010-03-03T13:18:15");
	}

}
