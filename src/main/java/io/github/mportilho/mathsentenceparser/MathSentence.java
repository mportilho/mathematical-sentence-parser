package io.github.mportilho.mathsentenceparser;

import java.util.Objects;

import org.antlr.v4.runtime.CharStreams;

import io.github.mportilho.mathsentenceparser.syntaxtree.MathematicalSentenceGrammarParser;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;

public class MathSentence {

	private final String sentence;
	private MathSentenceOptions mathSentenceOptions;
	private OperationSyntaxTree operationSyntaxTree;

	public MathSentence(String sentence) {
		this(sentence, null);
	}

	public MathSentence(String sentence, MathSentenceOptions mathSentenceOptions) {
		this.sentence = Objects.requireNonNull(sentence, "Math sentence text is required");
		this.mathSentenceOptions = mathSentenceOptions != null ? mathSentenceOptions : new MathSentenceOptions();
	}

	private void createOperationSyntaxTree() {
		if (operationSyntaxTree == null) {
			operationSyntaxTree = MathematicalSentenceGrammarParser.parseSentence(CharStreams.fromString(sentence), mathSentenceOptions);
			mathSentenceOptions = null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T compute() {
		createOperationSyntaxTree();
		return (T) operationSyntaxTree.compute();
	}

	public final MathSentence copy() {
		MathSentence mathSentence = new MathSentence(sentence, mathSentenceOptions);
		mathSentence.operationSyntaxTree = operationSyntaxTree.copy();
		return mathSentence;
	}

}
