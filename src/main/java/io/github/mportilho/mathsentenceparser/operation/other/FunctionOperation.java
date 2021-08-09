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

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.MathSentenceUtils;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class FunctionOperation extends AbstractOperation {

    private final String functionName;
    private final List<AbstractOperation> parameters;
    private final String functionKey;

    public FunctionOperation(String functionName, List<AbstractOperation> parameters, boolean caching) {
        this.functionName = functionName;
        this.parameters = parameters != null ? parameters : Collections.emptyList();
        this.caching(caching);
        this.functionKey = functionName + this.parameters.size();

        for (int i = 0; i < this.parameters.size(); i++) {
            this.parameters.get(i).addParent(this);
        }
    }

    @Override
    protected Object resolve(OperationContext context) {
        int count = this.parameters.size();
        Object[] args = new Object[count];
        for (int i = 0; i < count; i++) {
            args[i] = this.parameters.get(i).evaluate(context);
        }

        Function<Object[], Object> function;
        try {
            function = context.getExternalFunctions().get(functionKey);
        } catch (NullPointerException e) {
            throw new IllegalStateException("No functions loaded in this math sentence object", e);
        }
        if (function == null) {
            function = context.getExternalFunctions().get(functionName);
        }
        if (function == null) {
            throw new IllegalStateException(String.format("Function '%s' not found", functionName));
        }

        return MathSentenceUtils.tryConventingKnownTypes(function.apply(args), context.getMathContext());
    }

    @Override
    protected AbstractOperation createClone(CloningContext context) {
        List<AbstractOperation> newOperationList = new ArrayList<>();
        for (AbstractOperation param : parameters) {
            newOperationList.add(param.copy(context));
        }
        return new FunctionOperation(functionName, newOperationList, isCaching());
    }

    @Override
    protected void composeTextualRepresentation(StringBuilder builder) {
        if (isCaching()) {
            builder.append("f.");
        } else {
            builder.append("f0.");
        }
        builder.append(functionName).append("(");
        int index = parameters.size();
        for (int i = 0; i < parameters.size(); i++) {
            parameters.get(i).generateRepresentation(builder);
            if (--index != 0) {
                builder.append(", ");
            }
        }
        builder.append(")");
    }

    @Override
    public <T> T accept(OperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public List<AbstractOperation> getParameters() {
        return parameters;
    }

    @Override
    protected String getOperationToken() {
        return "";
    }

}
