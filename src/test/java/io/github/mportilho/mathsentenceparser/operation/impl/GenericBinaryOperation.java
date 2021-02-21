package io.github.mportilho.mathsentenceparser.operation.impl;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class GenericBinaryOperation extends AbstractBinaryOperation {

	public GenericBinaryOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public Object resolve(OperationContext context) {
		return null;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
