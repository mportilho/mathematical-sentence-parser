package io.github.mportilho.mathsentenceparser.parser;

import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class OperationSyntaxTreeContext {

	private Map<String, AbstractVariableValueOperation> providedVariables;
	private Map<String, AbstractOperation> assignedVariables;

	public OperationSyntaxTreeContext() {
	}

	public OperationSyntaxTreeContext(Map<String, AbstractVariableValueOperation> providedVariables,
			Map<String, AbstractOperation> assignedVariables) {
		super();
		this.providedVariables = providedVariables;
		this.assignedVariables = assignedVariables;
	}

	public Map<String, AbstractVariableValueOperation> getProvidedVariables() {
		return providedVariables;
	}

	public Map<String, AbstractOperation> getAssignedVariables() {
		return assignedVariables;
	}

}
