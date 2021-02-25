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

package io.github.mportilho.mathsentenceparser.sentence;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.MathSentenceOptions;

public class TestLogarithmOperations {

	public void computeMathSentence(String sentence, BigDecimal expectedValue) {
		assertThat(new MathSentence(sentence, new MathSentenceOptions(MathContext.DECIMAL64, 8)).<BigDecimal>compute())
				.isEqualByComparingTo(expectedValue);
	}

	@Test
	public void testBinaryLogarithmOperation() {
		computeMathSentence("lb(5)", BigDecimal.valueOf(2.32192809));
	}

	@Test
	public void testCommonLogarithmOperation() {
		computeMathSentence("log10(11)", BigDecimal.valueOf(1.04139269));
		computeMathSentence("log10(5) / log10(2)", BigDecimal.valueOf(2.32192809));
	}

	@Test
	public void testLogarithmOperation() {
		computeMathSentence("log(3.4322, 50)", BigDecimal.valueOf(3.17224975));
		computeMathSentence("10*((10*(log(3.4322, 50))))", BigDecimal.valueOf(317.22497485));
	}

	@Test
	public void testNaturalLogarithmOperation() {
		computeMathSentence("ln(4)", BigDecimal.valueOf(1.38629436));
		computeMathSentence("ln(5) / ln(2)", BigDecimal.valueOf(2.32192809));
		computeMathSentence("ln(4) + log10(4^2 + |-1|)", BigDecimal.valueOf(2.61674328));
	}

}
