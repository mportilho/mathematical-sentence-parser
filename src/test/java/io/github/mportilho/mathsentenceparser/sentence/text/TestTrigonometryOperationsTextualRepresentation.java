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

public class TestTrigonometryOperationsTextualRepresentation {

	@Test
	public void testDeggreOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("0°");
		assertThat(mathSentence.toString()).isEqualTo("0°");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("0°");

		mathSentence = new MathSentence("360°");
		assertThat(mathSentence.toString()).isEqualTo("360°");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("360°");
	}

	@Test
	public void testSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("sin(90)");
		assertThat(mathSentence.toString()).isEqualTo("sin(90)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sin(90)");

		mathSentence = new MathSentence("sin(90 * pi / 180)");
		assertThat(mathSentence.toString()).isEqualTo("sin(90 * pi / 180)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sin(90 * pi / 180)");
	}

	@Test
	public void testCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("cos(90) * E");
		assertThat(mathSentence.toString()).isEqualTo("cos(90) * E");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("cos(90) * E");
	}

	@Test
	public void testTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("tan(10)");
		assertThat(mathSentence.toString()).isEqualTo("tan(10)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tan(10)");

		mathSentence = new MathSentence("tan(10 * pi / 180)");
		assertThat(mathSentence.toString()).isEqualTo("tan(10 * pi / 180)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tan(10 * pi / 180)");
	}

	@Test
	public void testArcsineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("asin(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("asin(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("asin(0.7)");
	}

	@Test
	public void testArccosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("acos(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("acos(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("acos(0.7)");
	}

	@Test
	public void testArctangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atan(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("atan(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atan(0.7)");
	}

	@Test
	public void testArctangent2Operation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atan2(0.7, 3)");
		assertThat(mathSentence.toString()).isEqualTo("atan2(0.7, 3)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atan2(0.7, 3)");
	}

	@Test
	public void testHyperbolicSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("sinh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("sinh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sinh(0.7)");
	}

	@Test
	public void testHyperbolicCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("cosh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("cosh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("cosh(0.7)");
	}

	@Test
	public void testHyperbolicTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("tanh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("tanh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tanh(0.7)");
	}

	@Test
	public void testInverseHyperbolicSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("asinh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("asinh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("asinh(0.7)");
	}

	@Test
	public void testInverseHyperbolicCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("acosh(50)");
		assertThat(mathSentence.toString()).isEqualTo("acosh(50)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("acosh(50)");
	}

	@Test
	public void testInverseHyperbolicTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atanh(0.5)");
		assertThat(mathSentence.toString()).isEqualTo("atanh(0.5)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atanh(0.5)");
	}

}
