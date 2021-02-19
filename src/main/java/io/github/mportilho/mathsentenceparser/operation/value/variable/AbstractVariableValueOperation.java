package io.github.mportilho.mathsentenceparser.operation.value.variable;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public abstract class AbstractVariableValueOperation extends AbstractOperation {

	private String variableName;
	private Object providedValue;

	public AbstractVariableValueOperation(String variableName) {
		this.variableName = variableName;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

	public void provideNewValue(Object newValue) {
		if (newValue == null) {
			throw new IllegalArgumentException(String.format("Variable %s was provided with a null value", variableName));
		}
		clearCache();
		this.providedValue = newValue;
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String getVariableName() {
		return variableName;
	}

	public Object getProvidedValue() {
		return providedValue;
	}
}
