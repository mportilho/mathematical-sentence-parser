package io.github.mportilho.mathsentenceparser.syntaxtree.function;

import java.math.MathContext;

public class DynamicFunctionContext {

	private final MathContext mathContext;
	private final Integer scale;

	public DynamicFunctionContext(MathContext mathContext, Integer scale) {
		super();
		this.mathContext = mathContext;
		this.scale = scale;
	}

	public final MathContext getMathContext() {
		return mathContext;
	}

	public final Integer getScale() {
		return scale;
	}

}
