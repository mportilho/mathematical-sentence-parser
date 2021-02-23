package io.github.mportilho.mathsentenceparser.operation.others;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunctionContext;

public class TestFunctionOperations {

	@Test
	public void testDynamicFunction() {
		DynamicFunction function = (context, params) -> 5;
		Function<Object[], Object> dynamicFunction = parameters -> function.call(new DynamicFunctionContext(null, null), parameters);
		OperationContext operationContext = new OperationContext();
		operationContext.addExternalFunctions(Collections.singletonMap("teste", dynamicFunction));

		FunctionOperation operation = new FunctionOperation("teste", null, true);
		assertThat(operation.<BigDecimal>evaluate(operationContext)).isEqualByComparingTo("5");
	}

	@Test
	public void testFunctionOperationsWithExternalNumberMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedNumber() + 2");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<BigDecimal>compute()).isEqualByComparingTo("3");
	}

	@Test
	public void testFunctionOperationsWithExternalStringMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedString() = 'food'");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithExternalDateMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDate() = currDate");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithExternalTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedTime() = 02:03:00");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithExternalDateTimeMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedDateTime() = (currDateTime setHours 2 setMinutes 3 setSeconds 0)");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<Boolean>compute()).isFalse();
	}

	@Test
	public void testFunctionOperationsWithExternalBooleanMethods() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.extractedBoolean()");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<Boolean>compute()).isTrue();
	}

	@Test
	public void testFunctionOperationsWithNumberExternalMethods_MultipleParameters() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("f.add(3, 4 + 2 - f.extractedNumber())");
		mathSentence.addFunctions(new FunctionProviderClass());
		assertThat(mathSentence.<BigDecimal>compute()).isEqualByComparingTo(BigDecimal.valueOf(8));
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
