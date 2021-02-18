package io.github.mportilho.mathsentenceparser.operation.value.constant;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class StringConstantValueOperation extends AbstractConstantValueOperation {

	public StringConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return getValue().toString();
	}

}
