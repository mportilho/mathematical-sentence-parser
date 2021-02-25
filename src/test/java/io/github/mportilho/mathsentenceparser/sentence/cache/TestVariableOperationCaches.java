package io.github.mportilho.mathsentenceparser.sentence.cache;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestVariableOperationCaches {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testVariableOperationCache() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("a");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.addVariableValue("a", 2);
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(2);

		mathSentence = new MathSentence("b + 3");
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.addVariableValue("b", 2);
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(0);
		mathSentence.compute();
		assertThat(mathSentence.visitOperations(cacheVisitor.reset())).isEqualByComparingTo(4);
	}

}
