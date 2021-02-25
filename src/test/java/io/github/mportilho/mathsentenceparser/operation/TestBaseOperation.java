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

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestBaseOperation {

	private OperationContext context = new OperationContext();

	@Test
	public void testBaseOperation() {
		BaseOperation operation = new BaseOperation(OperationValueType.BOOLEAN, null, null);
		assertThat(operation.<Boolean>evaluate(context)).isFalse();
	}

	@Test
	public void testBaseOperationWithOperationMap() {
		Map<String, AssignedVariableOperation> map = new HashMap<>();
		map.put("one", new AssignedVariableOperation("one", new PreciseNumberConstantValueOperation("1")));
		map.put("two", new AssignedVariableOperation("two", new PreciseNumberConstantValueOperation("2")));

		BaseOperation operation = new BaseOperation(OperationValueType.NUMBER, null, map);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");
	}

	@Test
	public void testCopingBaseOperation() throws Throwable {
		BaseOperation operation = new BaseOperation(OperationValueType.BOOLEAN, null, null);
		BaseOperation copyOperation = (BaseOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<Boolean>evaluate(context)).isFalse();
		assertThat(copyOperation.<Boolean>evaluate(context)).isFalse();
	}

	@Test
	public void testCopingBaseOperationWithOperationMap() throws Throwable {
		Map<String, AssignedVariableOperation> map = new HashMap<>();
		map.put("one", new AssignedVariableOperation("one", new PreciseNumberConstantValueOperation("1")));
		map.put("two", new AssignedVariableOperation("two", new PreciseNumberConstantValueOperation("2")));

		BaseOperation operation = new BaseOperation(OperationValueType.NUMBER, null, map);
		BaseOperation copyOperation = (BaseOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		for (String key : operation.getAssignedVariables().keySet()) {
			assertThat(operation.getAssignedVariables().get(key)).isNotEqualTo(copyOperation.getAssignedVariables().get(key));
		}
	}

}
