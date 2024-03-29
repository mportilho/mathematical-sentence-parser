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

package io.github.mportilho.mathsentenceparser.operation;

import java.util.HashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class CloningContext {

	private final Map<String, AbstractVariableValueOperation> variables;
	private final Map<String, AssignedVariableOperation> assignedVariables;
	private final Map<AbstractOperation, AbstractOperation> clonedOperationMap;

	public CloningContext() {
		this.variables = new HashMap<>();
		this.assignedVariables = new HashMap<>();
		this.clonedOperationMap = new HashMap<>();
	}

	public Map<AbstractOperation, AbstractOperation> getClonedOperationMap() {
		return clonedOperationMap;
	}

	public final Map<String, AbstractVariableValueOperation> getVariables() {
		return variables;
	}

	public final Map<String, AssignedVariableOperation> getAssignedVariables() {
		return assignedVariables;
	}

}
