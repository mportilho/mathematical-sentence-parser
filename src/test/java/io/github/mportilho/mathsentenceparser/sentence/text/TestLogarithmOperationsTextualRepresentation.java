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

package io.github.mportilho.mathsentenceparser.sentence.text;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestLogarithmOperationsTextualRepresentation {

	@Test
	public void testBinaryLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("lb(5)");
		assertThat(mathSentence.toString()).isEqualTo("lb(5)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("lb(5)");
	}

	@Test
	public void testCommonLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("log10(11)");
		assertThat(mathSentence.toString()).isEqualTo("log10(11)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log10(11)");

		mathSentence = new MathSentence("log10(5) / log10(2)");
		assertThat(mathSentence.toString()).isEqualTo("log10(5) / log10(2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log10(5) / log10(2)");
	}

	@Test
	public void testLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("log(3.4322, 50)");
		assertThat(mathSentence.toString()).isEqualTo("log(3.4322, 50)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log(3.4322, 50)");

		mathSentence = new MathSentence("10*((10*(log(3.4322, 50))))");
		assertThat(mathSentence.toString()).isEqualTo("10 * (10 * (log(3.4322, 50)))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("10 * (10 * (log(3.4322, 50)))");
	}

	@Test
	public void testNaturalLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("ln(4)");
		assertThat(mathSentence.toString()).isEqualTo("ln(4)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(4)");

		mathSentence = new MathSentence("ln(5) / ln(2)");
		assertThat(mathSentence.toString()).isEqualTo("ln(5) / ln(2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(5) / ln(2)");

		mathSentence = new MathSentence("ln(4) + log10(4^2 + |-1|)");
		assertThat(mathSentence.toString()).isEqualTo("ln(4) + log10(4^2 + |-1|)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(4) + log10(4^2 + |-1|)");
	}

}
