package io.github.mportilho.mathsentenceparser.operation.impl;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public class GenericValueOperation extends AbstractOperation {

	private Object value;

	public GenericValueOperation(Object value) {
		this.value = value;
	}

	@Override
	public Object resolve(OperationContext context) {
		return getValue();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return null;
	}

	public Object getValue() {
		return value;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
