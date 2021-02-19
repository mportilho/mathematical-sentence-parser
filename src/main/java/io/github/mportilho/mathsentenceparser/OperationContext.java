package io.github.mportilho.mathsentenceparser;

import java.math.MathContext;
import java.time.LocalDateTime;

public class OperationContext {

	private final MathContext mathContext;
	private final int scale;
	private final ParsingOptions options;

	private LocalDateTime currentDateTime;

	public OperationContext() {
		this(MathContext.DECIMAL64, new ParsingOptions());
	}

	public OperationContext(MathContext mathContext, int scale, ParsingOptions options) {
		this.mathContext = mathContext;
		this.scale = scale;
		this.options = options;
	}

	public OperationContext(MathContext mathContext, ParsingOptions options) {
		this.mathContext = mathContext;
		this.scale = 8;
		this.options = options;
	}

	public OperationContext clearContext() {
		this.currentDateTime = null;
		return this;
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

	public LocalDateTime getCurrentDateTime() {
		if (currentDateTime == null) {
			currentDateTime = LocalDateTime.now();
		}
		return currentDateTime;
	}
}
