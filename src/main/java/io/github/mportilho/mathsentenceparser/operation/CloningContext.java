package io.github.mportilho.mathsentenceparser.operation;

import java.util.HashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class CloningContext {

	private Map<String, AbstractVariableValueOperation> variables;
	private Map<String, AssignedVariableOperation> assignedVariables;
	private Map<AbstractOperation, AbstractOperation> clonedOperationMap;

	public CloningContext() {
		this.variables = new HashMap<>();
		this.assignedVariables = new HashMap<>();
		this.clonedOperationMap = new HashMap<>();
	}

	public Map<AbstractOperation, AbstractOperation> getClonedOperationMap() {
		return clonedOperationMap;
	}

	public final Map<String, AbstractVariableValueOperation> getVariables() {
		return variables;
	}

	public final Map<String, AssignedVariableOperation> getAssignedVariables() {
		return assignedVariables;
	}

}
