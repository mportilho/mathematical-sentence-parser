package io.github.mportilho.mathsentenceparser;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TestMathSentence {

	@Test
	public void testMathSentence() {
		MathSentence compiler = new MathSentence("2 + 3");
		assertThat(compiler.<BigDecimal>compute()).isEqualByComparingTo("5");
	}

}
