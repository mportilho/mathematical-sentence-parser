package io.github.mportilho.mathsentenceparser.operation.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

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
			for (AbstractOperation operation : this.parameters) {
				operation.addParent(this);
			}
		}
		if (noCache) {
			this.caching(false);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
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
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public boolean isNoCache() {
		return noCache;
	}

	public List<AbstractOperation> getParameters() {
		return parameters;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
