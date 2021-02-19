package io.github.mportilho.mathsentenceparser.operation.impl;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;

public class GenericValueOperation extends AbstractConstantValueOperation {

	private Object currentValue;

	public GenericValueOperation(Object value) {
		super(value.toString());
	}

	@Override
	public Object resolve(OperationContext context) {
		return getValue();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return null;
	}

	public Object getCurrentValue() {
		return currentValue;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
