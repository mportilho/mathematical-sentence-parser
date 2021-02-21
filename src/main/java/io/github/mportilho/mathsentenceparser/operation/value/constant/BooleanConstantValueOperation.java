package io.github.mportilho.mathsentenceparser.operation.value.constant;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class BooleanConstantValueOperation extends AbstractConstantValueOperation {

	public BooleanConstantValueOperation(String value) {
		super(value);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return Boolean.parseBoolean(getValue().toString());
	}

}
