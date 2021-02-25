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

package io.github.mportilho.mathsentenceparser;

import java.math.MathContext;

import io.github.mportilho.mathsentenceparser.syntaxtree.parser.DefaultOperationSyntaxTreeGenerator;
import io.github.mportilho.mathsentenceparser.syntaxtree.parser.OperationSyntaxTreeGenerator;

public class MathSentenceOptions {

	public static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL64;
	public static final int DEFAULT_SCALE = 8;

	private final MathContext mathContext;
	private final int scale;
	private final OperationSyntaxTreeGenerator operationSyntaxTreeGenerator;

	public MathSentenceOptions() {
		this(DEFAULT_MATH_CONTEXT, DEFAULT_SCALE, null);
	}

	public MathSentenceOptions(MathContext mathContext, int scale) {
		this(mathContext, scale, null);
	}

	public MathSentenceOptions(MathContext mathContext, int scale, OperationSyntaxTreeGenerator operationSyntaxTreeGenerator) {
		this.mathContext = mathContext;
		this.scale = scale;
		this.operationSyntaxTreeGenerator = operationSyntaxTreeGenerator != null ? operationSyntaxTreeGenerator
				: new DefaultOperationSyntaxTreeGenerator();
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public int getScale() {
		return scale;
	}

	public OperationSyntaxTreeGenerator getOperationSyntaxTreeGenerator() {
		return operationSyntaxTreeGenerator;
	}

}
