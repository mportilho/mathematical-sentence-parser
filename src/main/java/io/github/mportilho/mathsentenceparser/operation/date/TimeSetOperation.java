package io.github.mportilho.mathsentenceparser.operation.date;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class TimeSetOperation extends AbstractDateTimeReader {

	public TimeSetOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super(leftOperand, rightOperand, dateElement);

	}

	@Override
	protected Object resolve(OperationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getOperationToken() {
		// TODO Auto-generated method stub
		return null;
	}

}