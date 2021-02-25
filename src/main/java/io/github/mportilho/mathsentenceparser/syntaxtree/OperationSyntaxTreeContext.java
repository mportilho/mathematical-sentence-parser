package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class OperationSyntaxTreeContext {

	private Map<String, AbstractVariableValueOperation> variables;
	private Map<String, AssignedVariableOperation> assignedVariables;

	public OperationSyntaxTreeContext() {
	}

	public OperationSyntaxTreeContext(Map<String, AbstractVariableValueOperation> variables,
			Map<String, AssignedVariableOperation> assignedVariables) {
		this.variables = variables;
		this.assignedVariables = assignedVariables;
	}

	public Map<String, AbstractVariableValueOperation> getVariables() {
		if (variables == null) {
			variables = new HashMap<>();
		}
		return variables;
	}

	public Map<String, AssignedVariableOperation> getAssignedVariables() {
		if (assignedVariables == null) {
			assignedVariables = new LinkedHashMap<>();
		}
		return assignedVariables;
	}

}
