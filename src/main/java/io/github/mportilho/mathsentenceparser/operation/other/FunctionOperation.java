package io.github.mportilho.mathsentenceparser.operation.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;

public class FunctionOperation extends AbstractOperation {

	private String functionName;
	private List<AbstractOperation> parameters;
	private boolean noCache = false;

	public FunctionOperation(String functionName, List<AbstractOperation> parameters, boolean noCache) {
		this.functionName = functionName;
		this.parameters = parameters;
		this.noCache = noCache;

		if (parameters == null || parameters.isEmpty()) {
			this.parameters = Collections.emptyList();
		} else {
			for (AbstractOperation reader : this.parameters) {
				reader.addParent(this);
			}
		}
		if (noCache) {
			this.caching(false);
		}
	}

	@Override
	protected Object resolve(ParsingContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		List<AbstractOperation> newOperationList = new ArrayList<>();
		for (AbstractOperation param : parameters) {
			newOperationList.add(param.copy(context));
		}
		return new FunctionOperation(this.functionName, newOperationList, this.noCache);
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
