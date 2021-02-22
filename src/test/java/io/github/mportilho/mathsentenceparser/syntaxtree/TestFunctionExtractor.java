package io.github.mportilho.mathsentenceparser.syntaxtree;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.IntrospectionException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.syntaxtree.function.FunctionExtractor;

public class TestFunctionExtractor {

	@Test
	public void testCall() throws IllegalAccessException, IntrospectionException {
		Map<String, Function<Object[], Object>> methods = FunctionExtractor.extractFunctions(new ExampleClass());
		assertThat(methods).hasSize(1);
		assertThat((String) methods.get("toText1").apply(new BigDecimal[] { BigDecimal.ONE })).isEqualTo("1");
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
