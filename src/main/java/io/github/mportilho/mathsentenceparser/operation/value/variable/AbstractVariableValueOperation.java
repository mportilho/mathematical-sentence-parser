package io.github.mportilho.mathsentenceparser.operation.value.variable;

import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractVariableValueOperation extends AbstractOperation {

	private String variableName;
	protected Object value;

	public AbstractVariableValueOperation(String variableName) {
		this.variableName = variableName;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

	public void setValue(Object newValue) {
		setValue(newValue, null);
	}

	public void setValue(Object newValue, Set<Class<? extends AbstractOperation>> stopOnOperationTypes) {
		if (newValue == null) {
			throw new IllegalArgumentException(String.format("Variable %s was provided with a null value", variableName));
		}
		clearCache(stopOnOperationTypes);
		this.value = newValue;
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		Object value = getCache() != null ? getCache() : getValue();
		value = value != null ? value : getVariableName();
		builder.append(value);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String getVariableName() {
		return variableName;
	}

	public Object getValue() {
		return value;
	}
}
