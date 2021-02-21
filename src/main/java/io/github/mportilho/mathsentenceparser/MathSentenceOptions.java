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
