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

package io.github.mportilho.mathsentenceparser.operation.value.variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;

public class SequenceVariableValueOperation extends AbstractVariableValueOperation {

	private static final Set<Class<? extends AbstractOperation>> STOP_ON = new HashSet<>(
			Arrays.asList(PreciseSummationOperation.class, PreciseProductOfSequenceOperation.class));

	public SequenceVariableValueOperation(String variableName) {
		super(variableName);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) {
		SequenceVariableValueOperation operation = new SequenceVariableValueOperation(getVariableName());
		operation.value = this.value;
		return operation;
	}

	public void setSequenceIndex(Number newValue) {
		super.setValue(newValue, STOP_ON);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getValue();
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		builder.append(getVariableName());
	}

}
