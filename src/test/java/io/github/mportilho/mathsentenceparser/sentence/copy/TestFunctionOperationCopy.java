package io.github.mportilho.mathsentenceparser.sentence.copy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestFunctionOperationCopy {

	@Test
	public void testFunctionOperationsWithExternalDateMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDate() = currDate");
		mathSentence.addFunctions(new FunctionProviderClass());
		MathSentence mathSentenceCopy1 = mathSentence.copy();
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentenceCopy1.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithExternalTimeMethods_NoCache() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f0.extractedTime() = 02:03:00");
		mathSentence.addFunctions(new FunctionProviderClass());
		MathSentence mathSentenceCopy1 = mathSentence.copy();
		assertThat(mathSentence.<Boolean>compute()).isTrue();
		assertThat(mathSentenceCopy1.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithNumberExternalMethods_MultipleParameters() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.add(3, 4 + 2 - f.extractedNumber())");
		mathSentence.addFunctions(new FunctionProviderClass());
		MathSentence mathSentenceCopy1 = mathSentence.copy();
		assertThat(mathSentence.<BigDecimal>compute()).isEqualByComparingTo(BigDecimal.valueOf(8));
		assertThat(mathSentenceCopy1.<BigDecimal>compute()).isEqualByComparingTo(BigDecimal.valueOf(8));
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
