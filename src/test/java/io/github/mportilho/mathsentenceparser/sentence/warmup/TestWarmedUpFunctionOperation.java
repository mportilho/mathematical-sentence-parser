package io.github.mportilho.mathsentenceparser.sentence.warmup;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.sentence.cache.CacheCheckVisitor;

public class TestWarmedUpFunctionOperation {

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
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalStringMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedString() = 'food'");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalDateMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDate() = currDate");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedTime() = 02:03:00");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

	@Test
	public void testFunctionOperationsWithExternalDateTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDateTime() = (currDateTime setHours 2 setMinutes 3 setSeconds 0)");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);
	}

	@Test
	public void testFunctionOperationsWithExternalBooleanMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedBoolean()");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(2);
	}

	@Test
	public void testFunctionOperationsWithNumberExternalMethods_MultipleParameters() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.add(3, 4 + 2 - f.extractedNumber())");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
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
