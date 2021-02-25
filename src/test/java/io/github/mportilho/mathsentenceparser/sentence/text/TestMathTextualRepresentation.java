package io.github.mportilho.mathsentenceparser.sentence.text;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestMathTextualRepresentation {

	@Test
	public void testAdditionOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 + 2");
		assertThat(mathSentence.toString()).isEqualTo("1 + 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 + 2");

		mathSentence = new MathSentence("1 + 2 + 3");
		assertThat(mathSentence.toString()).isEqualTo("1 + 2 + 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 + 2 + 3");

	}

	@Test
	public void testDivisionOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("6 / 2");
		assertThat(mathSentence.toString()).isEqualTo("6 / 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("6 / 2");

		mathSentence = new MathSentence("-6 / 3");
		assertThat(mathSentence.toString()).isEqualTo("-6 / 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("-6 / 3");
	}

	@Test
	public void testExponentialOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("3^3");
		assertThat(mathSentence.toString()).isEqualTo("3^3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3^3");

		mathSentence = new MathSentence("2 ^ 16");
		assertThat(mathSentence.toString()).isEqualTo("2^16");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2^16");
	}

	@Test
	public void testFactorialOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("3!");
		assertThat(mathSentence.toString()).isEqualTo("3!");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3!");

		mathSentence = new MathSentence("5!!");
		assertThat(mathSentence.toString()).isEqualTo("5!!");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("5!!");
	}

	@Test
	public void testModuloOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("3 mod 2");
		assertThat(mathSentence.toString()).isEqualTo("3 mod 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3 mod 2");
	}

	@Test
	public void testModulusOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("|7|");
		assertThat(mathSentence.toString()).isEqualTo("|7|");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("|7|");

		mathSentence = new MathSentence("|-7|");
		assertThat(mathSentence.toString()).isEqualTo("|-7|");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("|-7|");
	}

	@Test
	public void testMultiplicationOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("4 * 4");
		assertThat(mathSentence.toString()).isEqualTo("4 * 4");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("4 * 4");

		mathSentence = new MathSentence("-7 * 7");
		assertThat(mathSentence.toString()).isEqualTo("-7 * 7");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("-7 * 7");

		mathSentence = new MathSentence("7(7 + 2)");
		assertThat(mathSentence.toString()).isEqualTo("7(7 + 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("7(7 + 2)");

		mathSentence = new MathSentence("-7(7 + 2)");
		assertThat(mathSentence.toString()).isEqualTo("-7(7 + 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("-7(7 + 2)");

		mathSentence = new MathSentence("7a");
		assertThat(mathSentence.toString()).isEqualTo("7a");
		mathSentence.addVariableValue("a", 5);
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("7(5)");
	}

	@Test
	public void testNumberRoundingOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("up(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("up(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("up(3.555, 2)");

		mathSentence = new MathSentence("down(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("down(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("down(3.555, 2)");

		mathSentence = new MathSentence("ceiling(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("ceiling(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("ceiling(3.555, 2)");

		mathSentence = new MathSentence("floor(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("floor(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("floor(3.555, 2)");

		mathSentence = new MathSentence("halfUp(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("halfUp(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("halfUp(3.555, 2)");

		mathSentence = new MathSentence("halfDown(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("halfDown(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("halfDown(3.555, 2)");

		mathSentence = new MathSentence("halfEven(3.545, 2)");
		assertThat(mathSentence.toString()).isEqualTo("halfEven(3.545, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("halfEven(3.545, 2)");

		mathSentence = new MathSentence("halfEven(3.555, 2)");
		assertThat(mathSentence.toString()).isEqualTo("halfEven(3.555, 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("halfEven(3.555, 2)");
	}

	@Test
	public void testPercentualOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("3%");
		assertThat(mathSentence.toString()).isEqualTo("3%");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3%");

		mathSentence = new MathSentence("100%");
		assertThat(mathSentence.toString()).isEqualTo("100%");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("100%");
	}

	@Test
	public void testProductOfSequenceOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("P[1,3](P + 2)");
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + 2)");

		mathSentence = new MathSentence("P[1,3](P + P[1,3](P + 2))");
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + P[1,3](P + 2))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + P[1,3](P + 2))");

		mathSentence = new MathSentence("P[1,3](P + P + P[1,3](P + 2))");
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + P + P[1,3](P + 2))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("P[1,3](P + P + P[1,3](P + 2))");
	}

	@Test
	public void testRootOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("2 root 4");
		assertThat(mathSentence.toString()).isEqualTo("2√(4)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2√(4)");

		mathSentence = new MathSentence("3 root 27");
		assertThat(mathSentence.toString()).isEqualTo("3√(27)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("3√(27)");
	}

	@Test
	public void testSquareRootOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("sqrt(144)");
		assertThat(mathSentence.toString()).isEqualTo("2√(144)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2√(144)");

		mathSentence = new MathSentence("sqrt(9)");
		assertThat(mathSentence.toString()).isEqualTo("2√(9)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("2√(9)");
	}

	@Test
	public void testSubtractionOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("1 - 2");
		assertThat(mathSentence.toString()).isEqualTo("1 - 2");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 - 2");

		mathSentence = new MathSentence("1 - 2 - 3");
		assertThat(mathSentence.toString()).isEqualTo("1 - 2 - 3");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("1 - 2 - 3");
	}

	@Test
	public void testSummationOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("S[1,3](S + 2)");
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + 2)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + 2)");

		mathSentence = new MathSentence("S[1,3](S + S[1,3](S + 2))");
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + S[1,3](S + 2))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + S[1,3](S + 2))");

		mathSentence = new MathSentence("S[1,3](S + S + S[1,3](S + 2))");
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + S + S[1,3](S + 2))");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("S[1,3](S + S + S[1,3](S + 2))");
	}

}
