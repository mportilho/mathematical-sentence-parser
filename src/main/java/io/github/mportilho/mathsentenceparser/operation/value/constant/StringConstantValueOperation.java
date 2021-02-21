package io.github.mportilho.mathsentenceparser.operation.value.constant;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class StringConstantValueOperation extends AbstractConstantValueOperation {

	public StringConstantValueOperation(String value) {
		super(value);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getValue().toString();
	}

}
