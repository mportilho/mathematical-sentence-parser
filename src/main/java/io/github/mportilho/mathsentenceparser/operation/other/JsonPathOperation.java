package io.github.mportilho.mathsentenceparser.operation.other;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class JsonPathOperation extends AbstractOperation {

	public JsonPathOperation() {
	}

	@Override
	protected Object resolve(OperationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	protected String getOperationToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
