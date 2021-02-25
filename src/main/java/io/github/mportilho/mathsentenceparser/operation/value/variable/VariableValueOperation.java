package io.github.mportilho.mathsentenceparser.operation.value.variable;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class VariableValueOperation extends AbstractVariableValueOperation {

	private VariableValueProviderContext contextSupplier;

	public VariableValueOperation(String variableName) {
		super(variableName);
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (getValue() instanceof VariableValueProvider) {
			if (contextSupplier == null) {
				contextSupplier = new VariableValueProviderContext(context.getMathContext(), context.getScale(), isCaching());
			}
			Object result = ((VariableValueProvider) getValue()).provideValue(contextSupplier);
			if (!contextSupplier.isCaching()) {
				caching(false);
			}
			return result;
		}
		return getValue();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		VariableValueOperation copiedOperation = new VariableValueOperation(getVariableName());
		copiedOperation.value = this.value;
		if (contextSupplier != null) {
			copiedOperation.contextSupplier = new VariableValueProviderContext(contextSupplier.getMathContext(), contextSupplier.getScale(),
					contextSupplier.isCaching());
		}
		context.getVariables().put(getVariableName(), copiedOperation);
		return copiedOperation;
	}

}
