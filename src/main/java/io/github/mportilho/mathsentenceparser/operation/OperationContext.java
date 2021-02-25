/*MIT License

Copyright (c) 2021 Marcelo Portilho

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

package io.github.mportilho.mathsentenceparser.operation;

import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.MathSentenceOptions;

public class OperationContext {

	private final MathContext mathContext;
	private final Integer scale;

	private boolean allowingNull;
	private LocalDateTime currentDateTime;
	private Map<String, Function<Object[], Object>> externalFunctions;

	public OperationContext() {
		this(MathSentenceOptions.DEFAULT_MATH_CONTEXT, null, false);
	}

	public OperationContext(MathContext mathContext, Integer scale, boolean allowingNull) {
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

	public Integer getScale() {
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
