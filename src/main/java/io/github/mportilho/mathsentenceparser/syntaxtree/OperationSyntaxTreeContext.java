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

package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class OperationSyntaxTreeContext {

	private Map<String, AbstractVariableValueOperation> variables;
	private Map<String, AssignedVariableOperation> assignedVariables;

	public OperationSyntaxTreeContext() {
	}

	public OperationSyntaxTreeContext(Map<String, AbstractVariableValueOperation> variables,
			Map<String, AssignedVariableOperation> assignedVariables) {
		this.variables = variables;
		this.assignedVariables = assignedVariables;
	}

	public Map<String, AbstractVariableValueOperation> getVariables() {
		if (variables == null) {
			variables = new HashMap<>();
		}
		return variables;
	}

	public Map<String, AssignedVariableOperation> getAssignedVariables() {
		if (assignedVariables == null) {
			assignedVariables = new LinkedHashMap<>();
		}
		return assignedVariables;
	}

}
