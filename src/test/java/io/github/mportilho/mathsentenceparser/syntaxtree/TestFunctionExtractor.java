package io.github.mportilho.mathsentenceparser.syntaxtree;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.IntrospectionException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunctionContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.FunctionExtractor;

public class TestFunctionExtractor {

	@Test
	public void testCall() throws IllegalAccessException, IntrospectionException {
		Map<String, Function<Object[], Object>> methods = FunctionExtractor.extractFunctions(new ExampleClass());
		assertThat(methods).hasSize(1);
		assertThat((String) methods.get("toText1").apply(new BigDecimal[] { BigDecimal.ONE })).isEqualTo("1");
	}

	@Test
	public void testDynamicFunction() {
		DynamicFunction function = (context, params) -> 5;
		Function<Object[], Object> dynamicFunction = parameters -> function.call(new DynamicFunctionContext(null, null), parameters);
		OperationContext operationContext = new OperationContext();
		operationContext.addExternalFunctions(Collections.singletonMap("teste", dynamicFunction));

		FunctionOperation operation = new FunctionOperation("teste", null, true);
		assertThat(operation.<BigDecimal>evaluate(operationContext)).isEqualByComparingTo("5");
	}

	public static class ExampleClass {

		public void genericFunction() {
			System.out.println("Should not be picked up");
		}

		public String toText(BigDecimal value) {
			return value.toPlainString();
		}
	}

}
