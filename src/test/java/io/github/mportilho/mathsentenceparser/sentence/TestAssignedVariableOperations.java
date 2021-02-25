package io.github.mportilho.mathsentenceparser.sentence;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestAssignedVariableOperations {

	public void computeMathSentence(String sentence, BigDecimal expectedValue) {
		assertThat(new MathSentence(sentence).<BigDecimal>compute()).isEqualByComparingTo(expectedValue);
	}

	@Test
	public void testAssignedVariable() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("a * 5");
		computeMathSentence(sb.toString(), valueOf(15));
	}

	@Test
	public void testMultipleAssignedVariables() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := 5;");
		sb.append("a * b");
		computeMathSentence(sb.toString(), valueOf(15));
	}

	@Test
	public void testObtainAssinedVariable() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := 5;");
		MathSentence ms = new MathSentence(sb.toString());

		assertThat(ms.<Boolean>compute()).isFalse();
		assertThat(ms.<BigDecimal>getAssignedVariable("a")).isEqualByComparingTo(valueOf(3));
		assertThat(ms.<BigDecimal>getAssignedVariable("b")).isEqualByComparingTo(valueOf(5));
	}

	@Test
	public void testObtainAssinedVariableNoCache() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("a := 1 + 2;");
		sb.append("b := a + 5;");
		MathSentence ms = new MathSentence(sb.toString());

		assertThat(ms.<BigDecimal>getAssignedVariable("b")).isEqualByComparingTo(valueOf(8));
	}

}
