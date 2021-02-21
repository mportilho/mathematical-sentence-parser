package io.github.mportilho.mathsentenceparser.operation.impl;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

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
