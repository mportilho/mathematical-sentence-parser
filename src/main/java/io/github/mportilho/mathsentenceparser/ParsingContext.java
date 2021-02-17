package io.github.mportilho.mathsentenceparser;

import java.math.MathContext;

public class ParsingContext {

	private MathContext mathContext;
	private ParsingOptions options;

	public MathContext getMathContext() {
		return mathContext;
	}

	public ParsingOptions getOptions() {
		return options;
	}
}
