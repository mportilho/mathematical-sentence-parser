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

package io.github.mportilho.mathsentenceparser.sentence.copy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.sentence.cache.CacheCheckVisitor;

public class TestGeneralMathSentenceCopy {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testSimpleCopy() {
		MathSentence original = new MathSentence("(c)(a + b)");
		MathSentence copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);

		original.addVariable("a", 1);
		original.addVariable("b", 2);
		original.addVariable("c", 3);
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);

		copiedSentence.addVariable("a", 1);
		copiedSentence.addVariable("b", 2);
		copiedSentence.addVariable("c", 3);
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);

		original.compute();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(6);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);

		copiedSentence.compute();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(6);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(6);
	}

	@Test
	public void testCacheWithWarmedUpCompilerSimpleMathOperations() {
		MathSentence original = new MathSentence("((a + b) / (c * d)) - f");
		MathSentence copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);

		original.addVariable("a", 3);
		original.addVariable("b", 3);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(3);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(3);

		original.addVariable("c", 1);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(4);

		original.addVariable("f", 2);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(5);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(5);

		original.addVariable("d", 2);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(10);

		copiedSentence.compute();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(10);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(10);
	}

	@Test
	public void testCacheWithOrBooleanOperations() {
		MathSentence original = new MathSentence("if (d = 0 or a > b or 1 = 1) then 1 else 0 endif");
		MathSentence copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		original.addVariable("a", 1);
		original.addVariable("b", 2);
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		original.warmUp();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(9);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(9);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(9);

		original.addVariable("d", 0);
		original.warmUp();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(15);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(9);

		original.compute();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(15);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(9);

		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(15);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(15);
	}

	@Test
	public void testCacheWithAndBooleanExpression() {
		MathSentence original = new MathSentence("if (0 != 0 and a > b and 1 = 1) then 1 else 0 endif");
		original.addVariable("a", 1);
		original.addVariable("b", 2);

		MathSentence copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		original.compute();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(8);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		copiedSentence = original.copy();
		assertThat(original.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(8);
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(8);
	}

	@Test
	public void testCacheWithWarmedUpCompilerSimpleConditionalOperations() {
		MathSentence original = new MathSentence("((<datetime>a < b plusDays 1) or (c or d)) and f");
		MathSentence copiedSentence = original.copy();

		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(1);

		original.addVariable("a", new Date());
		original.addVariable("b", new Date());
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(5);

		original.addVariable("c", false);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(6);

		original.addVariable("f", true);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(7);

		original.addVariable("d", true);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(12);

		copiedSentence.compute();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(12);
	}

	@Test
	public void testCacheWithWarmedUpCompilerSimpleIfElseOperations() {
		MathSentence original = new MathSentence("if a > b then c + d else f endif");
		MathSentence copiedSentence = original.copy();

		original.warmUp();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		original.addVariable("a", 1);
		original.addVariable("c", 4);
		original.addVariable("d", 7);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(4);

		original.addVariable("b", 3);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(6);

		original.addVariable("d", 7);
		original.addVariable("f", 8);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(9);
	}

	@Test
	public void testCacheWithWarmedUpCompilerCaseWhenOperations() {
		MathSentence original = new MathSentence("if a = b then c elsif d then e + f else g endif");
		MathSentence copiedSentence = original.copy();

		original.warmUp();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(0);

		original.addVariable("a", 7);
		original.addVariableProvider("c", context -> 8);
		original.addVariable("g", 8);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(3);

		original.addVariable("b", 7);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(7);

		original.addVariable("b", 5);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(5);

		original.addVariable("e", 7);
		original.addVariable("f", 8);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(8);

		original.addVariable("d", true);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(11);

		original.addVariable("d", false);
		original.addVariable("e", 8);
		original.warmUp();
		copiedSentence = original.copy();
		assertThat(copiedSentence.visitOperations(cacheVisitor.reset())).isEqualTo(11);
	}

	@Test
	public void shouldComputeSameResults() {
		MathSentence original = new MathSentence("1 + 2 * 3 - 4 / 5 ^ 6 mod 7");
		MathSentence copiedSentence = original.copy();
		assertThat(original.<BigDecimal>compute()).isEqualByComparingTo(copiedSentence.<BigDecimal>compute());

		original = new MathSentence("S[1,3](S ^ 2)");
		copiedSentence = original.copy();
		BigDecimal resultCopy = copiedSentence.<BigDecimal>compute();
		BigDecimal resultOriginal = original.<BigDecimal>compute();
		assertThat(resultOriginal).isEqualByComparingTo(resultCopy);
	}

	@Test
	public void shouldCopyWithAllOperations() {
		MathSentence original = new MathSentence("S[1,3](S + 1)");
		MathSentence copiedSentence = original.copy();
		assertThat(original.<BigDecimal>compute()).isEqualByComparingTo(copiedSentence.<BigDecimal>compute());

		original = new MathSentence(
				"1 + 2 * 3 - 4 / 5 ^ 6 mod 7 + S[1,3](S ^ 2) + P[1,3](P + 2 ^ 4) + log(3.4322, 50) + lb(5) + log10(11) + ln(4) + 4! + |-1| + sqrt(16)");
		copiedSentence = original.copy();
		assertThat(original.<BigDecimal>compute()).isEqualByComparingTo(copiedSentence.<BigDecimal>compute());

		original = new MathSentence(
				"up(21.223, 2) + down(21.223, 2) + ceiling(21.223, 2) + floor(21.223, 2) + halfUp(21.223, 2) + halfDown(21.223, 2) + halfEven(21.223, 2)");
		copiedSentence = original.copy();
		assertThat(original.<BigDecimal>compute()).isEqualByComparingTo(copiedSentence.<BigDecimal>compute());

		original = new MathSentence("true and true or false xor false = false <> true and ~((1 > 2) and (3 <= 5))");
		copiedSentence = original.copy();
		assertThat(original.<Boolean>compute()).isEqualTo(copiedSentence.<Boolean>compute());
	}

}
