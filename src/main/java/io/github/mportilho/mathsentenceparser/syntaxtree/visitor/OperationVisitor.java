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
