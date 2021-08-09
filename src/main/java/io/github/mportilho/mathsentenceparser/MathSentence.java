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

package io.github.mportilho.mathsentenceparser;

import io.github.mportilho.mathsentenceparser.operation.value.variable.VariableProvider;
import io.github.mportilho.mathsentenceparser.syntaxtree.MathematicalSentenceGrammarParser;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;
import org.antlr.v4.runtime.CharStreams;

import java.util.Map;
import java.util.Objects;

public class MathSentence {

    private MathSentenceOptions mathSentenceOptions;
    private OperationSyntaxTree operationSyntaxTree;

    private MathSentence() {
        // internal use
    }

    public MathSentence(String sentence) {
        this(sentence, null);
    }

    public MathSentence(String sentence, MathSentenceOptions mathSentenceOptions) {
        Objects.requireNonNull(sentence, "Math sentence text is required");
        this.mathSentenceOptions = mathSentenceOptions != null ? mathSentenceOptions : new MathSentenceOptions();
        operationSyntaxTree = MathematicalSentenceGrammarParser.parseSentence(CharStreams.fromString(sentence), this.mathSentenceOptions);
        if (this.mathSentenceOptions.getFunctionPool() != null && !this.mathSentenceOptions.getFunctionPool().isEmpty()) {
            this.operationSyntaxTree.addFunction(this.mathSentenceOptions.getFunctionPool());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T compute() {
        return (T) operationSyntaxTree.compute();
    }

    public MathSentence warmUp() {
        operationSyntaxTree.warmUp();
        return this;
    }

    public void addFunction(String functionName, DynamicFunction function) {
        operationSyntaxTree.addFunction(functionName, function);
    }

    public MathSentence addFunctions(Object functionProvider) {
        operationSyntaxTree.addFunctions(functionProvider);
        return this;
    }

    public MathSentence addVariable(String variableName, Object value) {
        operationSyntaxTree.addVariableValue(variableName, value);
        return this;
    }

    public MathSentence addVariableProvider(String variableName, VariableProvider provider) {
        return addVariable(variableName, provider);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAssignedVariable(String variableName) {
        return (T) operationSyntaxTree.getAssignedVariable(variableName);
    }

    public Map<String, Object> listAssignedVariables() {
        return operationSyntaxTree.listAssignedVariables();
    }

    public Map<String, Object> listVariables() {
        return operationSyntaxTree.listVariables();
    }

    public final MathSentence copy() {
        MathSentence mathSentence = new MathSentence();
        mathSentence.mathSentenceOptions = this.mathSentenceOptions;
        mathSentence.operationSyntaxTree = operationSyntaxTree.copy();
        return mathSentence;
    }

    public <T> T visitOperations(OperationVisitor<T> visitor) {
        return operationSyntaxTree.visitOperation(visitor);
    }

    @Override
    public String toString() {
        return operationSyntaxTree.generateOperationRepresentation();
    }

}
