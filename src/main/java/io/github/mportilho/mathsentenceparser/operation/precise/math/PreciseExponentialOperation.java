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

package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseExponentialOperation extends AbstractBinaryOperation {

	public PreciseExponentialOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal rightValue = getRightOperand().evaluate(context);
		if (rightValue.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ONE;
		}
		BigDecimal leftValue = getLeftOperand().evaluate(context);
		return BigDecimalMath.pow(leftValue, rightValue, context.getMathContext());
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		getLeftOperand().generateRepresentation(builder);
		builder.append(getOperationToken());
		getRightOperand().generateRepresentation(builder);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) {
		return new PreciseExponentialOperation(getLeftOperand().copy(context), getRightOperand().copy(context));
	}

	@Override
	protected String getOperationToken() {
		return "^";
	}

}
