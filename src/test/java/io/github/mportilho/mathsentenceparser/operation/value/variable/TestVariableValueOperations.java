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

import io.github.mportilho.mathsentenceparser.MathSentence;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestVariableValueOperations {

    private final OperationContext context = new OperationContext();

    @Test
    public void testProvidedVariableValueOperations() {
        VariableValueOperation operation;

        operation = new VariableValueOperation("A");
        operation.setValue(BigDecimal.ZERO);
        assertThat(operation.getVariableName()).isEqualTo("A");
        assertThat(operation.getValue()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void testVariableValueProvider() {
        VariableValueOperation operation;
        VariableProvider provider = context -> BigDecimal.ONE.add(BigDecimal.ONE);

        operation = new VariableValueOperation("B");
        operation.setValue(provider);
        assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo(BigDecimal.valueOf(2));
    }

    @Test
    public void testNullVariableValue() {
        assertThatThrownBy(() -> new VariableValueOperation("a").setValue(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testVariableValueProviderOnExpression() {
        MathSentence parser = new MathSentence("a * b");
        parser.addVariable("a", 5);
        parser.addVariableProvider("b", context -> {
            context.caching(false);
            int scale = context.getScale() != null ? context.getScale() : 8;
            return new BigDecimal(6, context.getMathContext()).setScale(scale, context.getMathContext().getRoundingMode());
        });
        assertThat(parser.<BigDecimal>compute()).isEqualByComparingTo("30");
    }

}
