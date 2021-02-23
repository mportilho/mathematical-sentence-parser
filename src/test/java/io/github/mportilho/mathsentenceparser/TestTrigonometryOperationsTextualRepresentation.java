package io.github.mportilho.mathsentenceparser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestTrigonometryOperationsTextualRepresentation {

	@Test
	public void testDeggreOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("0°");
		assertThat(mathSentence.toString()).isEqualTo("0°");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("0°");

		mathSentence = new MathSentence("360°");
		assertThat(mathSentence.toString()).isEqualTo("360°");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("360°");
	}

	@Test
	public void testSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("sin(90)");
		assertThat(mathSentence.toString()).isEqualTo("sin(90)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sin(90)");

		mathSentence = new MathSentence("sin(90 * pi / 180)");
		assertThat(mathSentence.toString()).isEqualTo("sin(90 * pi / 180)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sin(90 * pi / 180)");
	}

	@Test
	public void testCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("cos(90) * E");
		assertThat(mathSentence.toString()).isEqualTo("cos(90) * E");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("cos(90) * E");
	}

	@Test
	public void testTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("tan(10)");
		assertThat(mathSentence.toString()).isEqualTo("tan(10)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tan(10)");

		mathSentence = new MathSentence("tan(10 * pi / 180)");
		assertThat(mathSentence.toString()).isEqualTo("tan(10 * pi / 180)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tan(10 * pi / 180)");
	}

	@Test
	public void testArcsineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("asin(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("asin(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("asin(0.7)");
	}

	@Test
	public void testArccosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("acos(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("acos(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("acos(0.7)");
	}

	@Test
	public void testArctangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atan(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("atan(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atan(0.7)");
	}

	@Test
	public void testArctangent2Operation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atan2(0.7, 3)");
		assertThat(mathSentence.toString()).isEqualTo("atan2(0.7, 3)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atan2(0.7, 3)");
	}

	@Test
	public void testHyperbolicSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("sinh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("sinh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("sinh(0.7)");
	}

	@Test
	public void testHyperbolicCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("cosh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("cosh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("cosh(0.7)");
	}

	@Test
	public void testHyperbolicTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("tanh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("tanh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("tanh(0.7)");
	}

	@Test
	public void testInverseHyperbolicSineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("asinh(0.7)");
		assertThat(mathSentence.toString()).isEqualTo("asinh(0.7)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("asinh(0.7)");
	}

	@Test
	public void testInverseHyperbolicCosineOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("acosh(50)");
		assertThat(mathSentence.toString()).isEqualTo("acosh(50)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("acosh(50)");
	}

	@Test
	public void testInverseHyperbolicTangentOperation() {
		MathSentence mathSentence;

		mathSentence = new MathSentence("atanh(0.5)");
		assertThat(mathSentence.toString()).isEqualTo("atanh(0.5)");
		mathSentence.compute();
		assertThat(mathSentence.toString()).isEqualTo("atanh(0.5)");
	}

}
