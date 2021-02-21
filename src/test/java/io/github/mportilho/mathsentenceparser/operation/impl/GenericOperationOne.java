package io.github.mportilho.mathsentenceparser.operation.impl;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class GenericOperationOne extends AbstractOperation {

	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return new GenericOperationOne();
	}

	@Override
	public Object resolve(OperationContext context) {
		return BigDecimal.ZERO;
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
