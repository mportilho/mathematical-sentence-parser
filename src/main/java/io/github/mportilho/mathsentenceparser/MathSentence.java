package io.github.mportilho.mathsentenceparser;

import java.util.Objects;

import org.antlr.v4.runtime.CharStreams;

import io.github.mportilho.mathsentenceparser.syntaxtree.MathematicalSentenceGrammarParser;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;

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
		operationSyntaxTree = MathematicalSentenceGrammarParser.parseSentence(CharStreams.fromString(sentence), this.mathSentenceOptions);
	}

	@SuppressWarnings("unchecked")
	public <T> T compute() {
		return (T) operationSyntaxTree.compute();
	}

	public void addFunction(String functionName, DynamicFunction function) {
		operationSyntaxTree.addFunction(functionName, function);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAssignedVariable(String variableName) {
		return (T) operationSyntaxTree.getAssignedVariable(variableName);
	}

	public final MathSentence copy() {
		MathSentence mathSentence = new MathSentence(sentence, mathSentenceOptions);
		mathSentence.operationSyntaxTree = operationSyntaxTree.copy();
		return mathSentence;
	}

}
