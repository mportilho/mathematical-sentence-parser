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
