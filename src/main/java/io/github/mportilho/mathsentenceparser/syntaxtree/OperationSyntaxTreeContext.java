package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class OperationSyntaxTreeContext {

	private Map<String, AbstractVariableValueOperation> providedVariables;
	private Map<String, AssignedVariableOperation> assignedVariables;

	public OperationSyntaxTreeContext() {
	}

	public OperationSyntaxTreeContext(Map<String, AbstractVariableValueOperation> providedVariables,
			Map<String, AssignedVariableOperation> assignedVariables) {
		this.providedVariables = providedVariables;
		this.assignedVariables = assignedVariables;
	}

	public Map<String, AbstractVariableValueOperation> getProvidedVariables() {
		if (providedVariables == null) {
			providedVariables = new HashMap<>();
		}
		return providedVariables;
	}

	public Map<String, AssignedVariableOperation> getAssignedVariables() {
		if (assignedVariables == null) {
			assignedVariables = new LinkedHashMap<>();
		}
		return assignedVariables;
	}

}
