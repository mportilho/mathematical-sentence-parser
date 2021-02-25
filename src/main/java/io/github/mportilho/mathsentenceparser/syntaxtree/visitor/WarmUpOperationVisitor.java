/*MIT License

Copyright (c) 2021 Marcelo Portilho

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

package io.github.mportilho.mathsentenceparser.syntaxtree.visitor;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.datetime.AbstractDateTimeOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.JsonPathOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class WarmUpOperationVisitor implements OperationVisitor<Object> {

	private OperationContext context;

	public WarmUpOperationVisitor(OperationContext context) {
		this.context = context;
	}

	@Override
	public Object visit(BaseOperation operation) {
		if (nonNull(operation.getAssignedVariables())) {
			for (AbstractOperation abstractoperation : operation.getAssignedVariables().values()) {
				abstractoperation.accept(this);
			}
		}
		if (operation.getOperation() != null) {
			Object object = operation.getOperation().accept(this);
			return object != null ? operation.evaluate(context) : null;
		}
		return null;
	}

	@Override
	public Object visit(AbstractUnaryOperator operation) {
		Object result = operation.getOperand().accept(this);
		return isNull(result) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractBinaryOperation operation) {
		Object left = operation.getLeftOperand().accept(this);
		Object right = operation.getRightOperand().accept(this);
		return (isNull(left) || isNull(right)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractDateTimeOperation operation) {
		Object left = operation.getLeftOperand().accept(this);
		Object right = operation.getRightOperand().accept(this);
		return (isNull(left) || isNull(right)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(DecisionOperation operation) {
		for (AbstractOperation abstractoperation : operation.getOperations()) {
			abstractoperation.accept(this);
		}
		try {
			return operation.evaluate(context);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object visit(FunctionOperation operation) {
		boolean hasNull = false;
		for (AbstractOperation exp : operation.getParameters()) {
			if (exp.accept(this) == null) {
				hasNull = true;
			}
		}
		return hasNull ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(JsonPathOperation operation) {
//		Object result = operation.getVariable().accept(this);
//		return isNull(result) ? null : operation.evaluate(context);
		return null;
	}

	@Override
	public Object visit(PreciseSummationOperation operation) {
		operation.getStartIndex().accept(this);
		operation.getEndIndex().accept(this);
		operation.getOperation().accept(this);
		try {
			return operation.evaluate(context);
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public Object visit(PreciseProductOfSequenceOperation operation) {
		operation.getStartIndex().accept(this);
		operation.getEndIndex().accept(this);
		operation.getOperation().accept(this);
		try {
			return operation.evaluate(context);
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public Object visit(AbstractConstantValueOperation operation) {
		return operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractVariableValueOperation operation) {
		return operation.evaluate(context);
	}

}
