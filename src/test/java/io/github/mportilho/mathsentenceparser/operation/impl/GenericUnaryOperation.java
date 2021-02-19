package io.github.mportilho.mathsentenceparser.operation.impl;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;

public class GenericUnaryOperation extends AbstractUnaryOperator {

	public GenericUnaryOperation(AbstractOperation operand) {
		super(operand, OperatorPosition.WRAPPED);
	}

	@Override
	public Object resolve(OperationContext context) {
		return BigDecimal.ZERO;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
