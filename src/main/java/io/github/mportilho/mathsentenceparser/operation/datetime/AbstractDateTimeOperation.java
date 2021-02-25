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

package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractDateTimeOperation extends AbstractOperation {

	private AbstractOperation leftOperand;
	private AbstractOperation rightOperand;
	private DateElementEnum dateElement;

	public AbstractDateTimeOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.leftOperand.addParent(this);
		this.rightOperand.addParent(this);
		this.dateElement = dateElement;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		CallSite callSite = cacheCopingFunction(getClass(), clazz -> {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodType factoryMethodType = MethodType.methodType(DateFunction.class);
			MethodType functionMethodType = MethodType.methodType(void.class, AbstractOperation.class, AbstractOperation.class,
					DateElementEnum.class);
			MethodHandle implementationMethodHandle = lookup.findConstructor(clazz, functionMethodType);
			return LambdaMetafactory.metafactory( //
					lookup, //
					"apply", //
					factoryMethodType, //
					functionMethodType.generic(), //
					implementationMethodHandle, //
					implementationMethodHandle.type());
		});
		return ((DateFunction<AbstractOperation, AbstractOperation, DateElementEnum, AbstractOperation>) callSite.getTarget().invokeExact())
				.apply(leftOperand.copy(context), rightOperand.copy(context), dateElement);
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		getLeftOperand().generateRepresentation(builder);
		builder.append(' ');
		builder.append(getOperationToken());
		builder.append(dateElement.toString()).append(' ');
		getRightOperand().generateRepresentation(builder);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public final AbstractOperation getLeftOperand() {
		return leftOperand;
	}

	public final AbstractOperation getRightOperand() {
		return rightOperand;
	}

	public final DateElementEnum getDateElement() {
		return dateElement;
	}

	static interface DateFunction<P1, P2, T, R> {
		R apply(P1 p1, P2 p2, T t);
	}

}
