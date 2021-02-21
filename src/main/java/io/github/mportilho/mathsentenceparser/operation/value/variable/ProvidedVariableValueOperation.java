package io.github.mportilho.mathsentenceparser.operation.value.variable;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class ProvidedVariableValueOperation extends AbstractVariableValueOperation {

	private VariableValueProviderContext contextSupplier;

	public ProvidedVariableValueOperation(String variableName) {
		super(variableName);
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (getProvidedValue() instanceof VariableValueProvider) {
			if (contextSupplier == null) {
				contextSupplier = new VariableValueProviderContext(context.getMathContext(), context.getScale(), isCaching());
			}
			Object result = ((VariableValueProvider) getProvidedValue()).provideValue(contextSupplier);
			if (!contextSupplier.isCaching()) {
				caching(false);
			}
			return result;
		}
		return getProvidedValue();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		ProvidedVariableValueOperation operation = new ProvidedVariableValueOperation(getVariableName());
		operation.providedValue = this.providedValue;
		if (contextSupplier != null) {
			operation.contextSupplier = new VariableValueProviderContext(contextSupplier.getMathContext(), contextSupplier.getScale(),
					contextSupplier.isCaching());
		}
		return operation;
	}

}
