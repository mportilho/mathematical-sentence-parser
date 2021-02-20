package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.value.variable.SequenceVariableValueOperation;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public class PreciseSummationOperation extends AbstractOperation {

	private AbstractOperation startIndex;
	private AbstractOperation endIndex;
	private AbstractOperation operation;
	private SequenceVariableValueOperation sequenceVariable;

	public PreciseSummationOperation(AbstractOperation startIndex, AbstractOperation endIndex, AbstractOperation operation,
			SequenceVariableValueOperation sequenceVariable) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.operation = operation;
		this.sequenceVariable = sequenceVariable;

		this.startIndex.addParent(this);
		this.endIndex.addParent(this);
		this.operation.addParent(this);
		if (this.sequenceVariable != null) {
			this.sequenceVariable.addParent(this);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		int startIndexResult = startIndex.<BigDecimal>evaluate(context).intValue();
		int endIndexResult = endIndex.<BigDecimal>evaluate(context).intValue();
		BigDecimal result = BigDecimal.ZERO;
		for (int i = startIndexResult; i <= endIndexResult; i++) {
			if (this.sequenceVariable != null) {
				this.sequenceVariable.provideNewValue(BigDecimal.valueOf(i));
			}
			result = result.add(operation.evaluate(context));
		}
		return result;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		return new PreciseSummationOperation(startIndex.copy(context), endIndex.copy(context), operation.copy(context),
				(SequenceVariableValueOperation) sequenceVariable.copy(context));
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public AbstractOperation getStartIndex() {
		return startIndex;
	}

	public AbstractOperation getEndIndex() {
		return endIndex;
	}

	public AbstractOperation getOperation() {
		return operation;
	}

	public SequenceVariableValueOperation getSequenceVariable() {
		return sequenceVariable;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
