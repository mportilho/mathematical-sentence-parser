package io.github.mportilho.mathsentenceparser.operation;

import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.MathSentenceOptions;

public class OperationContext {

	private final MathContext mathContext;
	private final int scale;

	private boolean allowingNull;
	private LocalDateTime currentDateTime;
	private Map<String, Function<Object[], Object>> externalFunctions;

	public OperationContext() {
		this.mathContext = MathSentenceOptions.DEFAULT_MATH_CONTEXT;
		this.scale = MathSentenceOptions.DEFAULT_SCALE;
		this.allowingNull = false;
	}

	public OperationContext(MathContext mathContext, int scale, boolean allowingNull) {
		this.mathContext = mathContext;
		this.scale = scale;
		this.allowingNull = allowingNull;
	}

	public OperationContext clearContext() {
		this.currentDateTime = null;
		this.allowingNull = false;
		return this;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public int getScale() {
		return scale;
	}

	public void setAllowingNull(boolean allowingNull) {
		this.allowingNull = allowingNull;
	}

	public boolean isAllowingNull() {
		return allowingNull;
	}

	public void addExternalFunctions(Map<String, Function<Object[], Object>> externalFunctions) {
		if (this.externalFunctions == null) {
			this.externalFunctions = new HashMap<>();
		}
		this.externalFunctions.putAll(externalFunctions);
	}

	public Map<String, Function<Object[], Object>> getExternalFunctions() {
		return externalFunctions;
	}

	public LocalDateTime getCurrentDateTime() {
		if (currentDateTime == null) {
			currentDateTime = LocalDateTime.now();
		}
		return currentDateTime;
	}
}
