package io.github.mportilho.mathsentenceparser;

import org.antlr.v4.runtime.CharStreams;

import io.github.mportilho.mathsentenceparser.parser.DefaultMathSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.parser.OperationSyntaxTree;
import io.github.mportilho.mathsentenceparser.parser.ParsingEngine;

public class MathSentence {

	private final String sentence;
	private final OperationContext operationContext;
	private final ParsingEngine parsingEngine;

	private OperationSyntaxTree operationSyntaxTree;

	public MathSentence(String sentence) {
		this(sentence, new OperationContext(), new ParsingEngine(new DefaultMathSentenceParserGrammarBaseVisitor()));
	}

	public MathSentence(String sentence, OperationContext operationContext) {
		this(sentence, operationContext, new ParsingEngine(new DefaultMathSentenceParserGrammarBaseVisitor()));
	}

	public MathSentence(String sentence, OperationContext operationContext, ParsingEngine parsingEngine) {
		this.sentence = sentence;
		this.operationContext = operationContext;
		this.parsingEngine = parsingEngine;
	}

	@SuppressWarnings("unchecked")
	public <T> T compute() {
		if (operationSyntaxTree == null) {
			operationSyntaxTree = parsingEngine.parseSentence(CharStreams.fromString(sentence));
		}
		return (T) operationSyntaxTree.compute(operationContext);
	}

	public final MathSentence copy() {
		MathSentence mathSentence = new MathSentence(sentence, operationContext, parsingEngine);
		mathSentence.operationSyntaxTree = operationSyntaxTree.copy();
		return mathSentence;
	}

}
