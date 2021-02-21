package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class PreciseNumberConstantValueOperation extends AbstractConstantValueOperation {

	public PreciseNumberConstantValueOperation(String value) {
		super(value);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.toBigDecimal(getValue().toString(), context.getMathContext());
	}
	
	public void transformToPositiveValue() {
		if (getValue() != null && getValue().startsWith("-")) {
			setValue(getValue().substring(1));
		}
	}

}
