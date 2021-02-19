package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.OperationContext;

public class PiNumberConstantValueOperation extends AbstractConstantValueOperation {

	public PiNumberConstantValueOperation() {
		this("pi");
	}

	public PiNumberConstantValueOperation(String value) {
		super(value);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.pi(context.getMathContext());
	}

}
