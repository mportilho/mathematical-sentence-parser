package io.github.mportilho.mathsentenceparser.operation.precise.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestLogarithmOperations {

	public void computeMathSentence(String sentence, BigDecimal expectedValue) {
		assertThat(new MathSentence(sentence).<BigDecimal>compute()).isEqualByComparingTo(expectedValue);
	}

	@Test
	public void testBinaryLogarithmOperation() {
		computeMathSentence("lb(5)", BigDecimal.valueOf(2.32192809));
	}

	@Test
	public void testCommonLogarithmOperation() {
		computeMathSentence("log10(11)", BigDecimal.valueOf(1.04139269));
		computeMathSentence("log10(5) / log10(2)", BigDecimal.valueOf(2.32192809));
	}

	@Test
	public void testLogarithmOperation() {
		computeMathSentence("log(3.4322, 50)", BigDecimal.valueOf(3.17224975));
		computeMathSentence("10*((10*(log(3.4322, 50))))", BigDecimal.valueOf(317.22497485));
	}

	@Test
	public void testNaturalLogarithmOperation() {
		computeMathSentence("ln(4)", BigDecimal.valueOf(1.38629436));
		computeMathSentence("ln(5) / ln(2)", BigDecimal.valueOf(2.32192809));
		computeMathSentence("ln(4) + log10(4^2 + |-1|)", BigDecimal.valueOf(2.61674328));
	}

}
