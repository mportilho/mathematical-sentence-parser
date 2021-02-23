package io.github.mportilho.mathsentenceparser.operation;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class BaseOperation extends AbstractOperation {

	private OperationValueType type;
	private Map<String, AssignedVariableOperation> assignedVariables;
	private AbstractOperation operation;

	public BaseOperation(OperationValueType type, AbstractOperation operation, Map<String, AssignedVariableOperation> assignedVariables) {
		this.type = type;
		this.assignedVariables = assignedVariables != null ? assignedVariables : Collections.emptyMap();
//		for (AbstractOperation op : this.assignedVariables.values()) {
//			op.addParent(this);
//		}
		this.operation = operation;
	}

	@Override
	protected Object resolve(OperationContext context) {
		for (AbstractOperation operation : assignedVariables.values()) {
			operation.evaluate(context);
		}
		if (operation != null) {
			return operation.evaluate(context);
		}
		switch (type) {
		case BOOLEAN:
			return Boolean.FALSE;
		case NUMBER:
			return BigDecimal.ZERO;
		default:
			throw new IllegalStateException(String.format("Type '%s' not valid for an empty operation. Only Boolean or Number allowed", type));
		}
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		Map<String, AssignedVariableOperation> newAssignedVariables = null;
		if (nonNull(assignedVariables)) {
			newAssignedVariables = new HashMap<>();
			for (Entry<String, AssignedVariableOperation> entry : assignedVariables.entrySet()) {
				newAssignedVariables.put(entry.getKey(), (AssignedVariableOperation) entry.getValue().copy(context));
			}
		}
		return new BaseOperation(type, operation != null ? operation.copy(context) : null, newAssignedVariables);
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {

		Set<Entry<String, AssignedVariableOperation>> entrySet = assignedVariables.entrySet();
		for (Entry<String, AssignedVariableOperation> entry : entrySet) {
			builder.append(entry.getValue().getVariableName()).append(' ').append(entry.getValue().getOperationToken()).append(' ');
			entry.getValue().getAssignedOperation().generateRepresentation(builder);
			builder.append(';');
			builder.append("\n");
		}
		if (operation != null) {
			operation.generateRepresentation(builder);
		} else {
			builder.deleteCharAt(builder.length() - 1);
		}
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public AbstractOperation getOperation() {
		return operation;
	}

	public Map<String, AssignedVariableOperation> getAssignedVariables() {
		return assignedVariables;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
