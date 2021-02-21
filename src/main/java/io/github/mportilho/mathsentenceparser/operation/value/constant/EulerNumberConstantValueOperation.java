package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class EulerNumberConstantValueOperation extends AbstractConstantValueOperation {

	public EulerNumberConstantValueOperation() {
		this("E");
	}

	public EulerNumberConstantValueOperation(String value) {
		super(value);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return BigDecimalMath.e(context.getMathContext());
	}

}
