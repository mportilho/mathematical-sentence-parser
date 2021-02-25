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

public class TestTrigonometryOperations {

	private static final MathSentenceOptions MATH_OPTION = new MathSentenceOptions(MathContext.DECIMAL64, 8);

	public void computeMathSentence(String sentence, BigDecimal expectedValue) {
		assertThat(new MathSentence(sentence, MATH_OPTION).<BigDecimal>compute()).isEqualByComparingTo(expectedValue);
	}

	@Test
	public void testDeggreOperation() {
		computeMathSentence("0°", new BigDecimal("0"));
		computeMathSentence("1°", new BigDecimal("0.01745329"));
		computeMathSentence("90°", new BigDecimal("1.57079633"));
		computeMathSentence("135°", new BigDecimal("2.35619449"));
		computeMathSentence("180°", new MathSentence("pi", MATH_OPTION).compute());
		computeMathSentence("360°", new MathSentence("2pi", MATH_OPTION).compute());
	}

	@Test
	public void testSineOperation() {
		computeMathSentence("sin(90)", new BigDecimal("0.89399666"));
		computeMathSentence("sin(90 * pi / 180)", new BigDecimal("1"));
	}

	@Test
	public void testCosineOperation() {
		computeMathSentence("cos(90)", new BigDecimal("-0.44807362"));
	}

	@Test
	public void testTangentOperation() {
		computeMathSentence("tan(10)", new BigDecimal("0.64836083"));
		computeMathSentence("tan(10 * pi / 180)", new BigDecimal("0.17632698"));
	}

	@Test
	public void testArcsineOperation() {
		computeMathSentence("asin(0.7)", new BigDecimal("0.77539750"));
	}

	@Test
	public void testArccosineOperation() {
		computeMathSentence("acos(0.7)", new BigDecimal("0.79539883"));
	}

	@Test
	public void testArctangentOperation() {
		computeMathSentence("atan(0.7)", new BigDecimal("0.61072596"));
	}

	@Test
	public void testArctangent2Operation() {
		computeMathSentence("atan2(0.7, 3)", new BigDecimal("0.22923193"));
	}

	@Test
	public void testHyperbolicSineOperation() {
		computeMathSentence("sinh(0.7)", new BigDecimal("0.75858370"));
	}

	@Test
	public void testHyperbolicCosineOperation() {
		computeMathSentence("cosh(0.7)", new BigDecimal("1.25516901"));
	}

	@Test
	public void testHyperbolicTangentOperation() {
		computeMathSentence("tanh(0.7)", new BigDecimal("0.60436778"));
	}

	@Test
	public void testInverseHyperbolicSineOperation() {
		computeMathSentence("asinh(0.7)", new BigDecimal("0.65266657"));
	}

	@Test
	public void testInverseHyperbolicCosineOperation() {
		computeMathSentence("acosh(50)", new BigDecimal("4.60507017"));
	}

	@Test
	public void testInverseHyperbolicTangentOperation() {
		computeMathSentence("atanh(0.5)", new BigDecimal("0.54930614"));
	}

}
