package io.github.mportilho.mathsentenceparser.operation.value.variable;

import java.math.MathContext;

public class VariableValueProviderContext {

	private final MathContext mathContext;
	private final Integer scale;
	private boolean caching;

	public VariableValueProviderContext(MathContext mathContext, Integer scale, boolean caching) {
		this.mathContext = mathContext;
		this.scale = scale;
		this.caching = caching;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public Integer getScale() {
		return scale;
	}

	public final void caching(boolean value) {
		this.caching = value;
	}

	public boolean isCaching() {
		return caching;
	}

}
