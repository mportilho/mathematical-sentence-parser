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

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestLogicOperations {

	public void computeLogicSentence(String sentence, Boolean expectedValue) {
		assertThat(new MathSentence(sentence).<Boolean>compute()).isEqualTo(expectedValue);
	}

	@Test
	public void testAndOperation() {
		computeLogicSentence("true and true and true", Boolean.TRUE);
		computeLogicSentence("true and true and false", Boolean.FALSE);
	}

	@Test
	public void testEqualsOperation() {
		computeLogicSentence("1 = 1", Boolean.TRUE);
		computeLogicSentence("(1 = 1) = false", Boolean.FALSE);

		computeLogicSentence("'teste' = 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 = 2000-03-20", Boolean.TRUE);

	}

	@Test
	public void testGreaterOperation() {
		computeLogicSentence("11 > 3", Boolean.TRUE);
		computeLogicSentence("3 > 3", Boolean.FALSE);

		computeLogicSentence("'teste2' > 'teste1'", Boolean.TRUE);
		computeLogicSentence("2000-03-21 > 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testGreaterOrEqualsOperation() {
		computeLogicSentence("3 >= 3", Boolean.TRUE);
		computeLogicSentence("-3 >= 3", Boolean.FALSE);
		computeLogicSentence("2 >= 3", Boolean.FALSE);

		computeLogicSentence("'teste' >= 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 >= 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testLessOperation() {
		computeLogicSentence("1 < 2", Boolean.TRUE);
		computeLogicSentence("1 < 1", Boolean.FALSE);

		computeLogicSentence("'teste1' < 'teste2'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 < 2000-03-22", Boolean.TRUE);
	}

	@Test
	public void testLessOrEqualsOperation() {
		computeLogicSentence("1 <= 2", Boolean.TRUE);
		computeLogicSentence("1 <= 1", Boolean.TRUE);
		computeLogicSentence("1 <= 0", Boolean.FALSE);

		computeLogicSentence("'teste' <= 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 <= 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testNandOperation() {
		computeLogicSentence("true nand false", Boolean.TRUE);
	}

	@Test
	public void testNegationOperation() {
		computeLogicSentence("!(1 <> 1)", Boolean.TRUE);
		computeLogicSentence("!(1 = 1)", Boolean.FALSE);
		computeLogicSentence("!true", Boolean.FALSE);
	}

	@Test
	public void testNorOperation() {
		computeLogicSentence("false nor false", Boolean.TRUE);
	}

	@Test
	public void testNotEqualsOperation() {
		computeLogicSentence("1 <> 2", Boolean.TRUE);
		computeLogicSentence("1 <> 1", Boolean.FALSE);

		computeLogicSentence("'teste1' <> 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 <> 2000-03-22", Boolean.TRUE);
	}

	@Test
	public void testOrOperation() {
		computeLogicSentence("true or true", Boolean.TRUE);
		computeLogicSentence("true or false", Boolean.TRUE);
	}

	@Test
	public void testXnorOperation() {
		computeLogicSentence("true xnor true", Boolean.TRUE);
	}

	@Test
	public void testXorOperation() {
		computeLogicSentence("false xor false", Boolean.FALSE);
	}

}
