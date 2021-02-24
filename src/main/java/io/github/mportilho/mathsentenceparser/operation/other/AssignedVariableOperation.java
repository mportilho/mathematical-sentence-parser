package io.github.mportilho.mathsentenceparser.operation.other;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class AssignedVariableOperation extends AbstractVariableValueOperation<AbstractOperation> {

	public AssignedVariableOperation(String variableName, AbstractOperation assignedOperation) {
		super(variableName);
		this.providedValue = assignedOperation;
		assignedOperation.addParent(this);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getProvidedValue().evaluate(context);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		AssignedVariableOperation copyOperation = new AssignedVariableOperation(getVariableName(), getProvidedValue().copy(context));
		context.getAssignedVariables().put(getVariableName(), copyOperation);
		return copyOperation;
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		builder.append(getCache() != null ? getCache() : getVariableName());
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getOperationToken() {
		return ":=";
	}

}
