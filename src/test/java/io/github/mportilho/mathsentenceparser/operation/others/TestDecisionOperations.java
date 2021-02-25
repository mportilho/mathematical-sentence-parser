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

package io.github.mportilho.mathsentenceparser.operation.others;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.BooleanConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestDecisionOperations {

	private OperationContext context = new OperationContext();
	private AbstractOperation trueOperation;
	private AbstractOperation falseOperation;

	private AbstractOperation number_1;
	private AbstractOperation number_2;
	private AbstractOperation number_3;
	private AbstractOperation number_4;

	@BeforeEach
	public void beforeEach() {
		trueOperation = new BooleanConstantValueOperation("true");
		falseOperation = new BooleanConstantValueOperation("false");

		number_1 = new PreciseNumberConstantValueOperation("1");
		number_2 = new PreciseNumberConstantValueOperation("2");
		number_3 = new PreciseNumberConstantValueOperation("3");
		number_4 = new PreciseNumberConstantValueOperation("4");
	}

	@Test
	public void testSimpleTrueDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(trueOperation);
		ops.add(number_2);
		ops.add(number_3);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testSimpleFalseDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(number_3);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
	}

	@Test
	public void testChainedToFirstValueDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(trueOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");
	}

	@Test
	public void testChainedToSecondValueDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(trueOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testChainedToThirdValueDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(trueOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
	}

	@Test
	public void testChainedToDefaultValueDecisions() {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
	}

	@Test
	public void testCopingSimpleTrueDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(trueOperation);
		ops.add(number_2);
		ops.add(number_3);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testCopingSimpleFalseDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(number_3);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
	}

	@Test
	public void testCopingChainedToFirstValueDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(trueOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("1");
	}

	@Test
	public void testCopingChainedToSecondValueDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(trueOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
	}

	@Test
	public void testCopingChainedToThirdValueDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(trueOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("3");
	}

	@Test
	public void testCopingChainedToDefaultValueDecisions() throws Throwable {
		List<AbstractOperation> ops = new ArrayList<>();
		ops.add(falseOperation);
		ops.add(number_1);
		ops.add(falseOperation);
		ops.add(number_2);
		ops.add(falseOperation);
		ops.add(number_3);
		ops.add(number_4);

		DecisionOperation decisionOperation = new DecisionOperation(ops);
		DecisionOperation copyOperation = (DecisionOperation) decisionOperation.copy(new CloningContext());
		assertThat(decisionOperation).isNotEqualTo(copyOperation);
		for (int i = 0; i < decisionOperation.getOperations().size(); i++) {
			assertThat(decisionOperation.getOperations().get(i)).isNotEqualTo(copyOperation.getOperations().get(i));
		}
		assertThat(decisionOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("4");
	}

}
