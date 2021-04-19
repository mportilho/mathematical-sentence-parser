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

import java.util.Objects;

import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractUnaryOperator extends AbstractOperation {

	private AbstractOperation operand;
	private OperatorPosition operatorPosition;

	public AbstractUnaryOperator(AbstractOperation operand, OperatorPosition operatorPosition) {
		this.operand = operand;
		this.operatorPosition = Objects.requireNonNull(operatorPosition, "Operator token position of unary operation is required");
		this.operand.addParent(this);
	}

	public AbstractOperation getOperand() {
		return operand;
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		switch (operatorPosition) {
		case FUNCTION:
			builder.append(getOperationToken()).append('(');
			getOperand().generateRepresentation(builder);
			builder.append(')');
			break;
		case LEFT:
			builder.append(getOperationToken());
			getOperand().generateRepresentation(builder);
			break;
		case RIGHT:
			getOperand().generateRepresentation(builder);
			builder.append(getOperationToken());
			break;
		case WRAPPED:
			builder.append(getOperationToken());
			getOperand().generateRepresentation(builder);
			builder.append(getOperationToken());
			break;
		default:
			throw new IllegalStateException("Operation position not implemented: " + operatorPosition);

		}
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public enum OperatorPosition {
		LEFT, RIGHT, WRAPPED, FUNCTION
	}
}
