package io.github.mportilho.mathsentenceparser;

import java.util.Objects;

import org.antlr.v4.runtime.CharStreams;

import io.github.mportilho.mathsentenceparser.syntaxtree.MathematicalSentenceGrammarParser;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class MathSentence {

	private MathSentenceOptions mathSentenceOptions;
	private OperationSyntaxTree operationSyntaxTree;

	private MathSentence() {
		// internal use
	}

	public MathSentence(String sentence) {
		this(sentence, null);
	}

	public MathSentence(String sentence, MathSentenceOptions mathSentenceOptions) {
		Objects.requireNonNull(sentence, "Math sentence text is required");
		this.mathSentenceOptions = mathSentenceOptions != null ? mathSentenceOptions : new MathSentenceOptions();
		operationSyntaxTree = MathematicalSentenceGrammarParser.parseSentence(CharStreams.fromString(sentence), this.mathSentenceOptions);
	}

	@SuppressWarnings("unchecked")
	public <T> T compute() {
		return (T) operationSyntaxTree.compute();
	}

	public MathSentence warmUp() {
		operationSyntaxTree.warmUp();
		return this;
	}

	public void addFunction(String functionName, DynamicFunction function) {
		operationSyntaxTree.addFunction(functionName, function);
	}

	public MathSentence addFunctions(Object functionProvider) {
		operationSyntaxTree.addFunctions(functionProvider);
		return this;
	}

	public MathSentence addVariableValue(String variableName, Object value) {
		operationSyntaxTree.addVariableValue(variableName, value);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAssignedVariable(String variableName) {
		return (T) operationSyntaxTree.getAssignedVariable(variableName);
	}

	public final MathSentence copy() {
		MathSentence mathSentence = new MathSentence();
		mathSentence.mathSentenceOptions = this.mathSentenceOptions;
		mathSentence.operationSyntaxTree = operationSyntaxTree.copy();
		return mathSentence;
	}

	public <T> T visitOperations(OperationVisitor<T> visitor) {
		return operationSyntaxTree.visitOperation(visitor);
	}

	@Override
	public String toString() {
		return operationSyntaxTree.generateOperationRepresentation();
	}

}
