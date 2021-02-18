package io.github.mportilho.mathsentenceparser.operation.value.constant;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class BooleanConstantValueOperation extends AbstractConstantValueOperation {

	public BooleanConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return Boolean.parseBoolean(getValue().toString());
	}

}
