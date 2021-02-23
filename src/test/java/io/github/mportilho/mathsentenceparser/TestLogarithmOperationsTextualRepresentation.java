package io.github.mportilho.mathsentenceparser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestLogarithmOperationsTextualRepresentation {

	@Test
	public void testBinaryLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("lb(5)");
		assertThat(mathSentence.toString()).isEqualTo("lb(5)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("lb(5)");
	}

	@Test
	public void testCommonLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("log10(11)");
		assertThat(mathSentence.toString()).isEqualTo("log10(11)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log10(11)");

		mathSentence = new MathSentence("log10(5) / log10(2)");
		assertThat(mathSentence.toString()).isEqualTo("log10(5) / log10(2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log10(5) / log10(2)");
	}

	@Test
	public void testLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("log(3.4322, 50)");
		assertThat(mathSentence.toString()).isEqualTo("log(3.4322, 50)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("log(3.4322, 50)");

		mathSentence = new MathSentence("10*((10*(log(3.4322, 50))))");
		assertThat(mathSentence.toString()).isEqualTo("10 * (10 * (log(3.4322, 50)))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("10 * (10 * (log(3.4322, 50)))");
	}

	@Test
	public void testNaturalLogarithmOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("ln(4)");
		assertThat(mathSentence.toString()).isEqualTo("ln(4)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(4)");

		mathSentence = new MathSentence("ln(5) / ln(2)");
		assertThat(mathSentence.toString()).isEqualTo("ln(5) / ln(2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(5) / ln(2)");

		mathSentence = new MathSentence("ln(4) + log10(4^2 + |-1|)");
		assertThat(mathSentence.toString()).isEqualTo("ln(4) + log10(4^2 + |-1|)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ln(4) + log10(4^2 + |-1|)");
	}

}
