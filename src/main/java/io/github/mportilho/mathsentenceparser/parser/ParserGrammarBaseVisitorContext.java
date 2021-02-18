package io.github.mportilho.mathsentenceparser.parser;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.SequenceVariableValueOperation;

public class ParserGrammarBaseVisitorContext {

	private Map<String, AbstractVariableValueOperation> providedVariables;
	private Map<String, AbstractOperation> assignedVariables;
	private Stack<List<SequenceVariableValueOperation>> sequenceVariableStack;
	private List<FunctionOperation> functionOperations;

	public Map<String, AbstractVariableValueOperation> getProvidedVariables() {
		return providedVariables;
	}

	public Map<String, AbstractOperation> getAssignedVariables() {
		return assignedVariables;
	}

	public Stack<List<SequenceVariableValueOperation>> getSequenceVariableStack() {
		return sequenceVariableStack;
	}

	public List<FunctionOperation> getFunctionOperations() {
		return functionOperations;
	}

}
