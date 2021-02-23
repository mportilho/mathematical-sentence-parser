package io.github.mportilho.mathsentenceparser.syntaxtree;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestOtherOperationsTextualRepresentation {

	@Test
	public void testBaseOperationTextRepresentation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("a := 3; b := 5;");
		assertThat(mathSentence.toString()).isEqualTo("a := 3;\nb := 5;");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("a := 3;\nb := 5;");

		mathSentence = new MathSentence("a := 3; b := 5; a + b");
		assertThat(mathSentence.toString()).isEqualTo("a := 3;\nb := 5;\na + b");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("a := 3;\nb := 5;\n3 + 5");

		StringBuilder builder = new StringBuilder();
		builder.append("a := 3 - 2;");
		builder.append("b := 5 * 2;");
		builder.append("a + b");
		mathSentence = new MathSentence(builder.toString());
		assertThat(mathSentence.toString()).isEqualTo("a := 3 - 2;\nb := 5 * 2;\na + b");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("a := 3 - 2;\nb := 5 * 2;\n1 + 10");
	}

	@Test
	public void testDecisionOperationTextRepresentation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("if true then 1 else 0 endif");
		assertThat(mathSentence.toString()).isEqualTo("if true then 1 else 0 endif");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("if true then 1 else 0 endif");

		mathSentence = new MathSentence("if true then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		assertThat(mathSentence.toString()).isEqualTo("if true then 0 elsif false then 1 else if true then 1 else 0 endif endif");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("if true then 0 elsif false then 1 else if true then 1 else 0 endif endif");
	}

	@Test
	public void testFunctionOperationTextRepresentation() {
		MathSentence mathSentence;
		throw new IllegalArgumentException();
	}

}
