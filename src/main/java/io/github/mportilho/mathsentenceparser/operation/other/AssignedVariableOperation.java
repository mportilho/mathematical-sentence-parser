package io.github.mportilho.mathsentenceparser.operation.other;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class AssignedVariableOperation extends AbstractOperation {

	private String variableName;
	private AbstractOperation assignedOperation;

	public AssignedVariableOperation(String variableName, AbstractOperation assignedOperation) {
		this.variableName = variableName;
		this.assignedOperation = assignedOperation;
		this.assignedOperation.addParent(this);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return assignedOperation.evaluate(context);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		AssignedVariableOperation copyOperation = new AssignedVariableOperation(variableName, assignedOperation.copy(context));
		context.getAssignedVariables().put(variableName, copyOperation);
		return copyOperation;
	}
	
	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	protected String getOperationToken() {
		return ":=";
	}

	public String getVariableName() {
		return variableName;
	}

	public AbstractOperation getAssignedOperation() {
		return assignedOperation;
	}

}
