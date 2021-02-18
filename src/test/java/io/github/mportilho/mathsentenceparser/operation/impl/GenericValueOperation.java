package io.github.mportilho.mathsentenceparser.operation.impl;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractValueOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;

public class GenericValueOperation extends AbstractValueOperation {

	public GenericValueOperation(Object value) {
		super(value);
	}

	@Override
	public Object resolve(ParsingContext context) {
		return getValue();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return null;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
