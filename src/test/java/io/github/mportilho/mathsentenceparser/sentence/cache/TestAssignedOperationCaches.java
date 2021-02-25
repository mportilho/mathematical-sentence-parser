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

package io.github.mportilho.mathsentenceparser.sentence.cache;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestAssignedOperationCaches {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testAssignedVariable() {
		StringBuilder sb;
		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("a * 5");

		MathSentence mathSentence;
		mathSentence = new MathSentence(sb.toString());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(7);
	}

	@Test
	public void testMultipleAssignedVariables() {
		StringBuilder sb;
		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := 5;");
		sb.append("a * b");

		MathSentence mathSentence;
		mathSentence = new MathSentence(sb.toString());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(8);
	}

	@Test
	public void testObtainAssinedVariable() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := 5;");

		MathSentence mathSentence;
		mathSentence = new MathSentence(sb.toString());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(7);
	}

	@Test
	public void testObtainAssinedVariableNoCache() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := a + 5;");

		MathSentence mathSentence;
		mathSentence = new MathSentence(sb.toString());
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(8);
	}

}
