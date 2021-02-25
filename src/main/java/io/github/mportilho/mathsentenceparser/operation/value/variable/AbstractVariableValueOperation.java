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

import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractVariableValueOperation extends AbstractOperation {

	private String variableName;
	protected Object value;

	public AbstractVariableValueOperation(String variableName) {
		this.variableName = variableName;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

	public void setValue(Object newValue) {
		setValue(newValue, null);
	}

	public void setValue(Object newValue, Set<Class<? extends AbstractOperation>> stopOnOperationTypes) {
		if (newValue == null) {
			throw new IllegalArgumentException(String.format("Variable %s was provided with a null value", variableName));
		}
		clearCache(stopOnOperationTypes);
		this.value = newValue;
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		Object value = getCache() != null ? getCache() : getValue();
		value = value != null ? value : getVariableName();
		builder.append(value);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String getVariableName() {
		return variableName;
	}

	public Object getValue() {
		return value;
	}
}
