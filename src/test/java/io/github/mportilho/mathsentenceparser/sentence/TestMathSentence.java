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

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestMathSentence {

	public void computeMathSentence(String sentence, BigDecimal expectedValue) {
		assertThat(new MathSentence(sentence).<BigDecimal>compute()).isEqualByComparingTo(expectedValue);
	}

	@Test
	public void testAdditionOperation() {
		computeMathSentence("1 + 2", valueOf(3));
		computeMathSentence("1 + 2 + 3", valueOf(6));
	}

	@Test
	public void testDivisionOperation() {
		computeMathSentence("6 / 2", valueOf(3));
		computeMathSentence("-6 / 3", valueOf(-2));
	}

	@Test
	public void testExponentialOperation() {
		computeMathSentence("3^3", valueOf(27));
		computeMathSentence("2^16", valueOf(65536));
	}

	@Test
	public void testFactorialOperation() {
		computeMathSentence("3!", valueOf(6));
		computeMathSentence("3!!", BigDecimalMath.factorial(6));
	}

	@Test
	public void testModuloOperation() {
		computeMathSentence("3 mod 2", valueOf(1));
		computeMathSentence("6 mod 2", valueOf(0));
	}

	@Test
	public void testModulusOperation() {
		computeMathSentence("|7|", valueOf(7));
		computeMathSentence("|-7|", valueOf(7));
	}

	@Test
	public void testMultiplicationOperation() {
		computeMathSentence("4 * 4", valueOf(16));
		computeMathSentence("-7 * 7", valueOf(-49));
		
		computeMathSentence("7(7 + 2)", valueOf(63));
		computeMathSentence("-7(7 + 2)", valueOf(-63));
	}

	@Test
	public void testNumberRoundingOperation() {
		computeMathSentence("up(3.555, 2)", valueOf(3.56));
		computeMathSentence("down(3.555, 2)", valueOf(3.55));
		computeMathSentence("ceiling(3.555, 2)", valueOf(3.56));
		computeMathSentence("floor(3.555, 2)", valueOf(3.55));
		computeMathSentence("halfUp(3.555, 2)", valueOf(3.56));
		computeMathSentence("halfDown(3.555, 2)", valueOf(3.55));
		computeMathSentence("halfEven(3.545, 2)", valueOf(3.54));
		computeMathSentence("halfEven(3.555, 2)", valueOf(3.56));
		assertThatThrownBy(() -> computeMathSentence("unnecessary(3.555, 2)", valueOf(3.56))).isInstanceOf(ArithmeticException.class);
	}

	@Test
	public void testPercentualOperation() {
		computeMathSentence("3%", valueOf(0.03));
		computeMathSentence("100%", valueOf(1));
	}

	@Test
	public void testProductOfSequenceOperation() {
		computeMathSentence("P[1,3](P + 2)", valueOf(60));
		computeMathSentence("P[1,3](P + P[1,3](P + 2))", valueOf(238266));
		computeMathSentence("P[1,3](P + P + P[1,3](P + 2))", valueOf(261888));
	}

	@Test
	public void testRootOperation() {
		computeMathSentence("2 root 4", valueOf(2));
		computeMathSentence("3 root 27", valueOf(3));
	}

	@Test
	public void testSquareRootOperation() {
		computeMathSentence("sqrt(144)", valueOf(12));
		computeMathSentence("sqrt(9)", valueOf(3));
	}

	@Test
	public void testSubtractionOperation() {
		computeMathSentence("1 - 2", valueOf(-1));
		computeMathSentence("1 - 2 - 3", valueOf(-4));
	}

	@Test
	public void testSummationOperation() {
		computeMathSentence("S[1,3](S + 2)", valueOf(12));
		computeMathSentence("S[1,3](S + S[1,3](S + 2))", valueOf(42));
		computeMathSentence("S[1,3](S + S + S[1,3](S + 2))", valueOf(48));
	}

}
