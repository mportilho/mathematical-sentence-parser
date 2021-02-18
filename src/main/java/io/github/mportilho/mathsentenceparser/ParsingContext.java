package io.github.mportilho.mathsentenceparser;

import java.math.MathContext;

public class ParsingContext {

	private final MathContext mathContext;
	private final int scale;
	private final ParsingOptions options;

	public ParsingContext() {
		this(MathContext.DECIMAL64, new ParsingOptions());
	}

	public ParsingContext(MathContext mathContext, int scale, ParsingOptions options) {
		this.mathContext = mathContext;
		this.scale = scale;
		this.options = options;
	}

	public ParsingContext(MathContext mathContext, ParsingOptions options) {
		this.mathContext = mathContext;
		this.scale = 8;
		this.options = options;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public int getScale() {
		return scale;
	}

	public ParsingOptions getOptions() {
		return options;
	}
}
