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

package io.github.mportilho.mathsentenceparser.sentence.warmup;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.sentence.cache.CacheCheckVisitor;

public class TestWarmedUpDecisionOperation {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testDecisionOperationCache() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("if true then 1 else 0 endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(5);

		mathSentence = new MathSentence("if false then 1 else 0 endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(5);

		mathSentence = new MathSentence("if true then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);

		mathSentence = new MathSentence("if false then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.warmUp();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);
	}

}
