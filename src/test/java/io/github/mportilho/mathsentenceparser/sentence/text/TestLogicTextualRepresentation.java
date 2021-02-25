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

public class TestLogicTextualRepresentation {

	@Test
	public void testAndOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("true and true and true");
		assertThat(mathSentence.toString()).isEqualTo("true and true and true");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true and true and true");

		mathSentence = new MathSentence("true and true and false");
		assertThat(mathSentence.toString()).isEqualTo("true and true and false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true and true and false");
	}

	@Test
	public void testEqualsOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 = 1");
		assertThat(mathSentence.toString()).isEqualTo("1 = 1");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 = 1");

		mathSentence = new MathSentence("(1 = 1) = false");
		assertThat(mathSentence.toString()).isEqualTo("(1 = 1) = false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("(1 = 1) = false");

		mathSentence = new MathSentence("'teste' = 'teste'");
		assertThat(mathSentence.toString()).isEqualTo("'teste' = 'teste'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste' = 'teste'");

		mathSentence = new MathSentence("2000-03-20 = 2000-03-20");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 = 2000-03-20");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 = 2000-03-20");
	}

	@Test
	public void testGreaterOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("11 > 3");
		assertThat(mathSentence.toString()).isEqualTo("11 > 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("11 > 3");

		mathSentence = new MathSentence("3 > 3");
		assertThat(mathSentence.toString()).isEqualTo("3 > 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3 > 3");

		mathSentence = new MathSentence("'teste2' > 'teste1'");
		assertThat(mathSentence.toString()).isEqualTo("'teste2' > 'teste1'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste2' > 'teste1'");

		mathSentence = new MathSentence("2000-03-21 > 2000-03-20");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-21 > 2000-03-20");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-21 > 2000-03-20");
	}

	@Test
	public void testGreaterOrEqualsOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("3 >= 3");
		assertThat(mathSentence.toString()).isEqualTo("3 >= 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3 >= 3");

		mathSentence = new MathSentence("3 >= 3");
		assertThat(mathSentence.toString()).isEqualTo("3 >= 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3 >= 3");

		mathSentence = new MathSentence("2 >= 3");
		assertThat(mathSentence.toString()).isEqualTo("2 >= 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2 >= 3");

		mathSentence = new MathSentence("'teste' >= 'teste'");
		assertThat(mathSentence.toString()).isEqualTo("'teste' >= 'teste'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste' >= 'teste'");

		mathSentence = new MathSentence("2000-03-20 >= 2000-03-20");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 >= 2000-03-20");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 >= 2000-03-20");
	}

	@Test
	public void testLessOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 < 2");
		assertThat(mathSentence.toString()).isEqualTo("1 < 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 < 2");

		mathSentence = new MathSentence("1 < 1");
		assertThat(mathSentence.toString()).isEqualTo("1 < 1");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 < 1");

		mathSentence = new MathSentence("'teste1' < 'teste2'");
		assertThat(mathSentence.toString()).isEqualTo("'teste1' < 'teste2'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste1' < 'teste2'");

		mathSentence = new MathSentence("2000-03-20 < 2000-03-22");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 < 2000-03-22");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 < 2000-03-22");
	}

	@Test
	public void testLessOrEqualsOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 <= 2");
		assertThat(mathSentence.toString()).isEqualTo("1 <= 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 <= 2");

		mathSentence = new MathSentence("1 <= 1");
		assertThat(mathSentence.toString()).isEqualTo("1 <= 1");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 <= 1");

		mathSentence = new MathSentence("1 <= 0");
		assertThat(mathSentence.toString()).isEqualTo("1 <= 0");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 <= 0");

		mathSentence = new MathSentence("'teste' <= 'teste'");
		assertThat(mathSentence.toString()).isEqualTo("'teste' <= 'teste'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste' <= 'teste'");

		mathSentence = new MathSentence("2000-03-20 <= 2000-03-20");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 <= 2000-03-20");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 <= 2000-03-20");
	}

	@Test
	public void testNandOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("true nand false");
		assertThat(mathSentence.toString()).isEqualTo("true nand false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true nand false");
	}

	@Test
	public void testNegationOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("!(1 <> 1)");
		assertThat(mathSentence.toString()).isEqualTo("!(1 <> 1)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("!(1 <> 1)");

		mathSentence = new MathSentence("!(1 = 1)");
		assertThat(mathSentence.toString()).isEqualTo("!(1 = 1)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("!(1 = 1)");

		mathSentence = new MathSentence("!true");
		assertThat(mathSentence.toString()).isEqualTo("!true");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("!true");
	}

	@Test
	public void testNorOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("false nor false");
		assertThat(mathSentence.toString()).isEqualTo("false nor false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("false nor false");
	}

	@Test
	public void testNotEqualsOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 <> 2");
		assertThat(mathSentence.toString()).isEqualTo("1 <> 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 <> 2");

		mathSentence = new MathSentence("1 <> 1");
		assertThat(mathSentence.toString()).isEqualTo("1 <> 1");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 <> 1");

		mathSentence = new MathSentence("'teste1' <> 'teste'");
		assertThat(mathSentence.toString()).isEqualTo("'teste1' <> 'teste'");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("'teste1' <> 'teste'");

		mathSentence = new MathSentence("2000-03-20 <> 2000-03-22");
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 <> 2000-03-22");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2000-03-20 <> 2000-03-22");
	}

	@Test
	public void testOrOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("true or true");
		assertThat(mathSentence.toString()).isEqualTo("true or true");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true or true");

		mathSentence = new MathSentence("true or false");
		assertThat(mathSentence.toString()).isEqualTo("true or false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true or false");
	}

	@Test
	public void testXnorOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("true xnor true");
		assertThat(mathSentence.toString()).isEqualTo("true xnor true");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("true xnor true");
	}

	@Test
	public void testXorOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("false xor false");
		assertThat(mathSentence.toString()).isEqualTo("false xor false");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("false xor false");
	}

}
