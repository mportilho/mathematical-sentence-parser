package io.github.mportilho.mathsentenceparser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestLogicSentence {

	public void computeLogicSentence(String sentence, Boolean expectedValue) {
		assertThat(new MathSentence(sentence).<Boolean>compute()).isEqualTo(expectedValue);
	}

	@Test
	public void testAndOperation() {
		computeLogicSentence("true and true and true", Boolean.TRUE);
		computeLogicSentence("true and true and false", Boolean.FALSE);
	}

	@Test
	public void testEqualsOperation() {
		computeLogicSentence("1 = 1", Boolean.TRUE);
		computeLogicSentence("(1 = 1) = false", Boolean.FALSE);

		computeLogicSentence("'teste' = 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 = 2000-03-20", Boolean.TRUE);

	}

	@Test
	public void testGreaterOperation() {
		computeLogicSentence("11 > 3", Boolean.TRUE);
		computeLogicSentence("3 > 3", Boolean.FALSE);

		computeLogicSentence("'teste2' > 'teste1'", Boolean.TRUE);
		computeLogicSentence("2000-03-21 > 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testGreaterOrEqualsOperation() {
		computeLogicSentence("3 >= 3", Boolean.TRUE);
		computeLogicSentence("3 >= 3", Boolean.TRUE);
		computeLogicSentence("2 >= 3", Boolean.FALSE);

		computeLogicSentence("'teste' >= 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 >= 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testLessOperation() {
		computeLogicSentence("1 < 2", Boolean.TRUE);
		computeLogicSentence("1 < 1", Boolean.FALSE);

		computeLogicSentence("'teste1' < 'teste2'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 < 2000-03-22", Boolean.TRUE);
	}

	@Test
	public void testLessOrEqualsOperation() {
		computeLogicSentence("1 <= 2", Boolean.TRUE);
		computeLogicSentence("1 <= 1", Boolean.TRUE);
		computeLogicSentence("1 <= 0", Boolean.FALSE);

		computeLogicSentence("'teste' <= 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 <= 2000-03-20", Boolean.TRUE);
	}

	@Test
	public void testNandOperation() {
		computeLogicSentence("true nand false", Boolean.TRUE);
	}

	@Test
	public void testNegationOperation() {
		computeLogicSentence("!(1 <> 1)", Boolean.TRUE);
		computeLogicSentence("!(1 = 1)", Boolean.FALSE);
		computeLogicSentence("!true", Boolean.FALSE);
	}

	@Test
	public void testNorOperation() {
		computeLogicSentence("false nor false", Boolean.TRUE);
	}

	@Test
	public void testNotEqualsOperation() {
		computeLogicSentence("1 <> 2", Boolean.TRUE);
		computeLogicSentence("1 <> 1", Boolean.FALSE);

		computeLogicSentence("'teste1' <> 'teste'", Boolean.TRUE);
		computeLogicSentence("2000-03-20 <> 2000-03-22", Boolean.TRUE);
	}

	@Test
	public void testOrOperation() {
		computeLogicSentence("true or true", Boolean.TRUE);
		computeLogicSentence("true or false", Boolean.TRUE);
	}

	@Test
	public void testXnorOperation() {
		computeLogicSentence("true xnor true", Boolean.TRUE);
	}

	@Test
	public void testXorOperation() {
		computeLogicSentence("false xor false", Boolean.FALSE);
	}

}
