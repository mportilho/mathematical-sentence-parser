package io.github.mportilho.mathsentenceparser.sentence.cache;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestDecisionOperationCaches {

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
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);

		mathSentence = new MathSentence("if false then 1 else 0 endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);

		mathSentence = new MathSentence("if true then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);

		mathSentence = new MathSentence("if false then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(7);
	}

}
