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

package io.github.mportilho.mathsentenceparser.operation.other;

import java.util.ArrayList;
import java.util.List;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class DecisionOperation extends AbstractOperation {

	private List<AbstractOperation> operations;

	public DecisionOperation(List<AbstractOperation> operations) {
		if (operations == null || operations.size() < 2) {
			throw new IllegalStateException("Decision operation must have at least two conditionals (if ... then ... else ... endif)");
		}
		this.operations = operations;
		for (int i = 0; i < operations.size(); i++) {
			this.operations.get(i).addParent(this);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		Boolean condition;
		for (int i = 0; i < operations.size() - 1; i += 2) {
			condition = (Boolean) operations.get(i).evaluate(context);
			if (condition) {
				return operations.get(i + 1).evaluate(context);
			}
		}
		return operations.get(operations.size() - 1).evaluate(context);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		List<AbstractOperation> clonedOperations = new ArrayList<>();
		for (AbstractOperation op : operations) {
			clonedOperations.add(op.copy(context));
		}
		return new DecisionOperation(clonedOperations);
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		int count = operations.size();
		for (int i = 0; i < count - 1; i += 2) {
			builder = i == 0 ? builder.append("if ") : builder.append(" elsif ");
			builder.append(operations.get(i)).append(" then ").append(operations.get(i + 1));
		}
		builder.append(" else ").append(operations.get(operations.size() - 1)).append(" endif");
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public List<AbstractOperation> getOperations() {
		return operations;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
