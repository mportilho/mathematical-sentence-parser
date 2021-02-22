package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.util.HashMap;
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
		this.providedVariables = providedVariables;
		this.assignedVariables = assignedVariables;
	}

	public Map<String, AbstractVariableValueOperation> getProvidedVariables() {
		if (providedVariables == null) {
			providedVariables = new HashMap<>();
		}
		return providedVariables;
	}

	public Map<String, AbstractOperation> getAssignedVariables() {
		if (assignedVariables == null) {
			assignedVariables = new HashMap<>();
		}
		return assignedVariables;
	}

}
