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

import java.beans.IntrospectionException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunctionContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.FunctionExtractor;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.WarmUpOperationVisitor;

public class OperationSyntaxTree {

	private final AbstractOperation operation;
	private final OperationSyntaxTreeContext syntaxTreeContext;
	private OperationContext operationContext;
	private DynamicFunctionContext dynamicFunctionContext;

	public OperationSyntaxTree(AbstractOperation operation, OperationSyntaxTreeContext syntaxTreeContext) {
		super();
		this.operation = Objects.requireNonNull(operation, "AbstractOperation object is required");
		this.syntaxTreeContext = syntaxTreeContext == null ? new OperationSyntaxTreeContext() : syntaxTreeContext;
	}

	void setOperationContext(OperationContext operationContext) {
		this.operationContext = operationContext;
		this.dynamicFunctionContext = new DynamicFunctionContext(operationContext.getMathContext(), operationContext.getScale());
	}

	public Object compute() {
		operationContext.clearContext();
		return operation.evaluate(operationContext);
	}

	public void warmUp() {
		boolean originalAllowingNull = operationContext.isAllowingNull();
		try {
			operationContext.setAllowingNull(true);
			visitOperation(new WarmUpOperationVisitor(operationContext));
		} finally {
			operationContext.setAllowingNull(originalAllowingNull);
		}
	}

	public void addVariableValue(String variableName, Object value) {
		try {
			if (value instanceof Integer) {
				syntaxTreeContext.getVariables().get(variableName).setValue(new BigDecimal((Integer) value));
			} else if (value instanceof Long) {
				syntaxTreeContext.getVariables().get(variableName).setValue(BigDecimal.valueOf((Long) value));
			} else if (value instanceof Number && !(value instanceof BigDecimal)) {
				syntaxTreeContext.getVariables().get(variableName).setValue(new BigDecimal(value.toString()));
			} else if (value instanceof Date) {
				syntaxTreeContext.getVariables().get(variableName)
						.setValue(LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()));
			} else {
				syntaxTreeContext.getVariables().get(variableName).setValue(value);
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(
					String.format("Variable name '%s' not found on sentence or the parameter 'value' is null", variableName), e);
		}
	}

	public OperationSyntaxTree copy() {
		CloningContext cloningContext = new CloningContext();
		try {
			AbstractOperation copy = operation.copy(cloningContext);
			OperationSyntaxTreeContext grammarBaseVisitorContext = new OperationSyntaxTreeContext(cloningContext.getVariables(),
					cloningContext.getAssignedVariables());
			OperationSyntaxTree syntaxTree = new OperationSyntaxTree(copy, grammarBaseVisitorContext);
			syntaxTree.operationContext = this.operationContext;
			return syntaxTree;
		} catch (Throwable e) {
			throw new IllegalStateException("Error while coping operation syntax tree", e);
		}
	}

	public void addFunctions(Object functionProvider) {
		try {
			Map<String, Function<Object[], Object>> publicMethods = FunctionExtractor.extractFunctions(functionProvider);
			operationContext.addExternalFunctions(publicMethods);
		} catch (IllegalAccessException | IntrospectionException e) {
			throw new IllegalArgumentException("Error while extracting public methods from provided object", e);
		}
	}

	public void addFunction(String functionName, DynamicFunction function) {
		Function<Object[], Object> dynamicFunction = parameters -> function.call(dynamicFunctionContext, parameters);
		operationContext.addExternalFunctions(Collections.singletonMap(functionName, dynamicFunction));
	}

	public Object getAssignedVariable(String variableName) {
		return syntaxTreeContext.getAssignedVariables().containsKey(variableName)
				? syntaxTreeContext.getAssignedVariables().get(variableName).evaluate(operationContext)
				: null;
	}

	public <T> T visitOperation(OperationVisitor<T> visitor) {
		return operation.accept(visitor);
	}

	public String generateOperationRepresentation() {
		return operation.toString();
	}

}
