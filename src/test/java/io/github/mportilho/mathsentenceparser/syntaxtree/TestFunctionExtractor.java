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
		Map<String, Function<Object[], Object>> methods;
		
		methods = FunctionExtractor.extractFunctions(new ExampleClass());
		assertThat(methods).hasSize(2);
		assertThat((String) methods.get("toText1").apply(new BigDecimal[] { BigDecimal.ONE })).isEqualTo("1");
		assertThat((BigDecimal) methods.get("staticMethod0").apply(null)).isEqualByComparingTo("0");
		
		methods = FunctionExtractor.extractFunctions(ExampleClass.class);
		assertThat(methods).hasSize(1);
		assertThat((BigDecimal) methods.get("staticMethod0").apply(null)).isEqualByComparingTo("0");
	}

	public static class ExampleClass {

		public void genericFunction() {
			System.out.println("Should not be picked up");
		}

		public String toText(BigDecimal value) {
			return value.toPlainString();
		}

		public static BigDecimal staticMethod() {
			return BigDecimal.ZERO;
		}
	}

}
