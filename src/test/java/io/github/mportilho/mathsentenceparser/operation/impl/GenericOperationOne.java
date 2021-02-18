package io.github.mportilho.mathsentenceparser.operation.impl;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;

public class GenericOperationOne extends AbstractOperation {

	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return new GenericOperationOne();
	}

	@Override
	public Object resolve(ParsingContext context) {
		return BigDecimal.ZERO;
	}

	@Override
	protected String getOperationToken() {
		return null;
	}

}
