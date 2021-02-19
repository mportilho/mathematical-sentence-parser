package io.github.mportilho.mathsentenceparser.operation;

import java.util.HashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class CloningContext {

	private Map<String, AbstractVariableValueOperation> providedVariables;
	private Map<String, AbstractOperation> assignedVariables;
	private Map<AbstractOperation, AbstractOperation> clonedOperationMap;

	public CloningContext() {
		this.providedVariables = new HashMap<>();
		this.assignedVariables = new HashMap<>();
		this.clonedOperationMap = new HashMap<>();
	}

	public Map<AbstractOperation, AbstractOperation> getClonedOperationMap() {
		return clonedOperationMap;
	}

	public final Map<String, AbstractVariableValueOperation> getProvidedVariables() {
		return providedVariables;
	}

	public final Map<String, AbstractOperation> getAssignedVariables() {
		return assignedVariables;
	}

}
