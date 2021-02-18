package io.github.mportilho.mathsentenceparser.operation;

import java.util.HashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class CloningContext {

	private Map<String, AbstractVariableValueOperation> userVariables;
	private Map<String, AbstractOperation> assignedVariables;
	private Map<AbstractOperation, AbstractOperation> readerMap;

	public CloningContext() {
		this.userVariables = new HashMap<>();
		this.assignedVariables = new HashMap<>();
		this.readerMap = new HashMap<>();
	}

	public Map<AbstractOperation, AbstractOperation> getReaderMap() {
		return readerMap;
	}

	public final Map<String, AbstractVariableValueOperation> getUserVariables() {
		return userVariables;
	}

	public final Map<String, AbstractOperation> getAssignedVariables() {
		return assignedVariables;
	}

}
