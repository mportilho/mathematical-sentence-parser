package io.github.mportilho.mathsentenceparser.syntaxtree.visitor;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.datetime.AbstractDateTimeOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.JsonPathOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public interface OperationVisitor<T> {

	public T visit(BaseOperation operation);

	public T visit(AbstractUnaryOperator operation);

	public T visit(AbstractBinaryOperation operation);

	public T visit(AbstractDateTimeOperation operation);

	public T visit(DecisionOperation operation);

	public T visit(FunctionOperation operation);

	public T visit(JsonPathOperation operation);

	public T visit(PreciseSummationOperation operation);

	public T visit(PreciseProductOfSequenceOperation operation);

	public T visit(AbstractConstantValueOperation operation);

	public T visit(AbstractVariableValueOperation operation);

}
