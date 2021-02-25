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

package io.github.mportilho.mathsentenceparser.sentence.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestFunctionOperationCaches {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testFunctionOperationsWithExternalNumberMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedNumber() + 2");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalStringMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedString() = 'food'");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalDateMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDate() = currDate");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedTime() = 02:03:00");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalDateTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDateTime() = (currDateTime setHours 2 setMinutes 3 setSeconds 0)");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);
	}

	@Test
	public void testFunctionOperationsWithExternalBooleanMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedBoolean()");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(2);
	}

	@Test
	public void testFunctionOperationsWithNumberExternalMethods_MultipleParameters() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.add(3, 4 + 2 - f.extractedNumber())");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(8);
	}

	public static class FunctionProviderClass {

		public BigDecimal extractedNumber() {
			return BigDecimal.ONE;
		}

		public LocalDate extractedDate() {
			return LocalDate.now();
		}

		public LocalTime extractedTime() {
			return LocalTime.of(2, 3, 0);
		}

		public LocalDateTime extractedDateTime() {
			return LocalDateTime.of(LocalDate.now(), LocalTime.of(2, 3, 0));
		}

		public String extractedString() {
			return "food";
		}

		public Boolean extractedBoolean() {
			return Boolean.TRUE;
		}

		public BigDecimal add(BigDecimal a, BigDecimal b) {
			return a.add(b);
		}
	}

}
