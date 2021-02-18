package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.ParsingContext;

public class EulerNumberConstantValueOperation extends AbstractConstantValueOperation {

	public EulerNumberConstantValueOperation() {
		this("E");
	}

	public EulerNumberConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return BigDecimalMath.e(context.getMathContext());
	}

}
