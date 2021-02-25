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
		this.operation = operation;
		if (this.operation != null) {
			this.operation.addParent(this);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		for (Entry<String, AssignedVariableOperation> entry : assignedVariables.entrySet()) {
			entry.getValue().evaluate(context);
		}
		if (operation != null) {
			Object result = operation.evaluate(context);
			if (result instanceof BigDecimal) {
				return ((BigDecimal) result).setScale(context.getScale(), context.getMathContext().getRoundingMode());
			} else {
				return result;
			}
		}
		switch (type) {
		case BOOLEAN:
			return Boolean.FALSE;
		case NUMBER:
			return BigDecimal.ZERO;
		default:
			throw new IllegalStateException(String.format("'%s' is not a valid return type for operations. Only Boolean or Number allowed", type));
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
			((AbstractOperation) entry.getValue().getValue()).generateRepresentation(builder);
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
