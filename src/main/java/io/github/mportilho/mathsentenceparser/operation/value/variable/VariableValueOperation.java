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

package io.github.mportilho.mathsentenceparser.operation.value.variable;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.MathSentenceUtils;

public class VariableValueOperation extends AbstractVariableValueOperation {

    private VariableValueProviderContext contextSupplier;

    public VariableValueOperation(String variableName) {
        super(variableName);
    }

    @Override
    protected Object resolve(OperationContext context) {
        if (getValue() instanceof VariableProvider) {
            if (contextSupplier == null) {
                contextSupplier = new VariableValueProviderContext(context.getMathContext(), context.getScale(), isCaching());
            }
            Object result = ((VariableProvider) getValue()).provideValue(contextSupplier);
            if (!contextSupplier.isCaching()) {
                caching(false);
            }
            return MathSentenceUtils.tryConventingKnownTypes(result, context.getMathContext());
        }
        return getValue();
    }

    @Override
    protected AbstractOperation createClone(CloningContext context) {
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
