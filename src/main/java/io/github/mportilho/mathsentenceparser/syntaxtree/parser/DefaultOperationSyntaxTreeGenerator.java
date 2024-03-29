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

package io.github.mportilho.mathsentenceparser.syntaxtree.parser;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarLexer;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.*;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.datetime.*;
import io.github.mportilho.mathsentenceparser.operation.logic.*;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.*;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseNumberRoundingOperation.RoundingEnum;
import io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm.BinaryLogarithmOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm.CommonLogarithmOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm.LogarithmOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.logarithm.NaturalLogarithmOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.trigonometry.*;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.operation.value.constant.*;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.SequenceVariableValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.VariableValueOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTreeContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

public class DefaultOperationSyntaxTreeGenerator extends MathematicalSentenceParserGrammarBaseVisitor<AbstractOperation>
        implements OperationSyntaxTreeGenerator {

    private final OperationSyntaxTreeContext parserContext;
    private List<FunctionOperation> functionOperations;
    private Stack<List<SequenceVariableValueOperation>> sequenceVariableStack;

    public DefaultOperationSyntaxTreeGenerator() {
        this.parserContext = new OperationSyntaxTreeContext();
    }

    @Override
    public OperationSyntaxTree createOperationSyntaxTree(StartContext startContext) {
        return new OperationSyntaxTree(visit(startContext), parserContext);
    }

    @Override
    public AbstractOperation visitErrorNode(ErrorNode node) {
        return super.visitErrorNode(node);
    }

    @Override
    public AbstractOperation visitMathStart(MathStartContext ctx) {
        for (AssignmentExpressionContext expressionContext : ctx.assignmentExpression()) {
            AssignedVariableOperation operator = (AssignedVariableOperation) expressionContext.accept(this);
            parserContext.getAssignedVariables().put(operator.getVariableName(), operator);
        }
        AbstractOperation mathOperation = ctx.mathExpression() != null ? ctx.mathExpression().accept(this) : null;
        return new BaseOperation(OperationValueType.NUMBER, mathOperation, parserContext.getAssignedVariables());
    }

    @Override
    public AbstractOperation visitLogicalStart(LogicalStartContext ctx) {
        for (AssignmentExpressionContext expressionContext : ctx.assignmentExpression()) {
            AssignedVariableOperation operator = (AssignedVariableOperation) expressionContext.accept(this);
            parserContext.getAssignedVariables().put(operator.getVariableName(), operator);
        }
        AbstractOperation logicalOperation = ctx.logicalExpression() != null ? ctx.logicalExpression().accept(this) : null;
        return new BaseOperation(OperationValueType.BOOLEAN, logicalOperation, parserContext.getAssignedVariables());
    }

    @Override
    public AbstractOperation visitAssignOperation(AssignOperationContext ctx) {
        AbstractOperation abstractOperation = ctx.allEntityTypes().accept(this);
        return new AssignedVariableOperation(ctx.IDENTIFIER().getText(), abstractOperation);
    }

    @Override
    public AbstractOperation visitDateTimeExpression(DateTimeExpressionContext ctx) {
        if (nonNull(ctx.comparisonOperator().GT())) {
            return new GreaterOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().GE())) {
            return new GreaterOrEqualsOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LT())) {
            return new LessOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LE())) {
            return new LessOrEqualsOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().EQ())) {
            return new EqualsOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().NEQ())) {
            return new NotEqualsOperation(ctx.dateTimeOperation(0).accept(this), ctx.dateTimeOperation(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitStringExpression(StringExpressionContext ctx) {
        if (nonNull(ctx.comparisonOperator().GT())) {
            return new GreaterOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().GE())) {
            return new GreaterOrEqualsOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LT())) {
            return new LessOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LE())) {
            return new LessOrEqualsOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().EQ())) {
            return new EqualsOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().NEQ())) {
            return new NotEqualsOperation(ctx.stringEntity(0).accept(this), ctx.stringEntity(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operação não implementada: %s", ctx.getText()));
    }

    @Override
    public AbstractOperation visitLogicExpression(LogicExpressionContext ctx) {
        if (nonNull(ctx.logicalOperator().AND())) {
            return new AndOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().OR())) {
            return new OrOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().XOR())) {
            return new XorOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().XNOR())) {
            return new XnorOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().NOR())) {
            return new NorOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().NAND())) {
            return new NandOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().EQ())) {
            return new EqualsOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        } else if (nonNull(ctx.logicalOperator().NEQ())) {
            return new NotEqualsOperation(ctx.logicalExpression(0).accept(this), ctx.logicalExpression(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitDateExpression(DateExpressionContext ctx) {
        if (nonNull(ctx.comparisonOperator().GT())) {
            return new GreaterOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().GE())) {
            return new GreaterOrEqualsOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LT())) {
            return new LessOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LE())) {
            return new LessOrEqualsOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().EQ())) {
            return new EqualsOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().NEQ())) {
            return new NotEqualsOperation(ctx.dateOperation(0).accept(this), ctx.dateOperation(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitNotExpression(NotExpressionContext ctx) {
        return new NegationOperation(ctx.logicalExpression().accept(this));
    }

    @Override
    public AbstractOperation visitComparisonMathExpression(ComparisonMathExpressionContext ctx) {
        if (nonNull(ctx.comparisonOperator().GT())) {
            return new GreaterOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().GE())) {
            return new GreaterOrEqualsOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LT())) {
            return new LessOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LE())) {
            return new LessOrEqualsOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().EQ())) {
            return new EqualsOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().NEQ())) {
            return new NotEqualsOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitTimeExpression(TimeExpressionContext ctx) {
        if (nonNull(ctx.comparisonOperator().GT())) {
            return new GreaterOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().GE())) {
            return new GreaterOrEqualsOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LT())) {
            return new LessOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().LE())) {
            return new LessOrEqualsOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().EQ())) {
            return new EqualsOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        } else if (nonNull(ctx.comparisonOperator().NEQ())) {
            return new NotEqualsOperation(ctx.timeOperation(0).accept(this), ctx.timeOperation(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitLogicalParenthesis(LogicalParenthesisContext ctx) {
        AbstractOperation operation = ctx.logicalExpression().accept(this);
        operation.applyingParenthesis();
        return operation;
    }

    @Override
    public AbstractOperation visitInverseHyperbolicSineExpression(InverseHyperbolicSineExpressionContext ctx) {
        return new InverseHyperbolicSineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitSequenceExpression(SequenceExpressionContext ctx) {
        if (sequenceVariableStack == null) {
            sequenceVariableStack = new Stack<>();
        }
        return super.visitSequenceExpression(ctx);
    }

    @Override
    public AbstractOperation visitHyperbolicSineExpression(HyperbolicSineExpressionContext ctx) {
        return new HyperbolicSineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitInverseHyperbolicCosineExpression(InverseHyperbolicCosineExpressionContext ctx) {
        return new InverseHyperbolicCosineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitModulusExpression(ModulusExpressionContext ctx) {
        return new PreciseModulusOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitMathParenthesis(MathParenthesisContext ctx) {
        AbstractOperation operation = ctx.mathExpression().accept(this);
        operation.applyingParenthesis();
        return operation;
    }

    @Override
    public AbstractOperation visitTangentExpression(TangentExpressionContext ctx) {
        return new TangentOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitArctangent2Expression(Arctangent2ExpressionContext ctx) {
        return new Arctangent2Operation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
    }

    @Override
    public AbstractOperation visitSquareRootExpression(SquareRootExpressionContext ctx) {
        return new PreciseSquareRootOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitRootExpression(RootExpressionContext ctx) {
        return new PreciseRootOperation(ctx.mathExpression(1).accept(this), ctx.mathExpression(0).accept(this));
    }

    @Override
    public AbstractOperation visitHyperbolicTangentExpression(HyperbolicTangentExpressionContext ctx) {
        return new HyperbolicTangentOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitHyperbolicCosineExpression(HyperbolicCosineExpressionContext ctx) {
        return new HyperbolicCosineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitImplicitMultiplicationExpression(ImplicitMultiplicationExpressionContext ctx) {
        AbstractOperation leftOperation = ctx.mathExpression(0).accept(this);
        AbstractOperation rightOperation = ctx.mathExpression(1).accept(this);

        if (rightOperation instanceof PreciseNegativeOperation) {
            ((PreciseNegativeOperation) rightOperation).negatingValue(false);
            return new PreciseSubtractionOperation(leftOperation, rightOperation);
        } else if ((rightOperation instanceof PreciseNumberConstantValueOperation
                && ((PreciseNumberConstantValueOperation) rightOperation).getValue().startsWith("-"))) {
            ((PreciseNumberConstantValueOperation) rightOperation).transformToPositiveValue();
            return new PreciseSubtractionOperation(leftOperation, rightOperation);
        }
        return new PreciseMultiplicationOperation(leftOperation, rightOperation, true);
    }

    @Override
    public AbstractOperation visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
        if (ctx.MULT() != null) {
            return new PreciseMultiplicationOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.DIV())) {
            return new PreciseDivisionOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.MODULO())) {
            return new PreciseModuloOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitFactorialExpression(FactorialExpressionContext ctx) {
        return new PreciseFactorialOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitNegateMathParenthesis(NegateMathParenthesisContext ctx) {
        AbstractOperation operation = ctx.mathExpression().accept(this);
        operation.applyingParenthesis();
        return new PreciseNegativeOperation(operation);
    }

    @Override
    public AbstractOperation visitPercentExpression(PercentExpressionContext ctx) {
        return new PrecisePercentualOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitSineExpression(SineExpressionContext ctx) {
        return new SineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitCosineExpression(CosineExpressionContext ctx) {
        return new CosineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitArctangentExpression(ArctangentExpressionContext ctx) {
        return new ArctangentOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitSumExpression(SumExpressionContext ctx) {
        if (ctx.PLUS() != null) {
            return new PreciseAdditionOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        } else if (nonNull(ctx.MINUS())) {
            return new PreciseSubtractionOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitInverseHyperbolicTangentExpression(InverseHyperbolicTangentExpressionContext ctx) {
        return new InverseHyperbolicTangentOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitDegreeExpression(DegreeExpressionContext ctx) {
        return new DeggreOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitExponentiationExpression(ExponentiationExpressionContext ctx) {
        return new PreciseExponentialOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
    }

    @Override
    public AbstractOperation visitArccosineExpression(ArccosineExpressionContext ctx) {
        return new ArccosineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitArcsineExpression(ArcsineExpressionContext ctx) {
        return new ArcsineOperation(ctx.mathExpression().accept(this));
    }

    @Override
    public AbstractOperation visitFixedLogarithm(FixedLogarithmContext ctx) {
        if (nonNull(ctx.BINARY_LOGARITHM())) {
            return new BinaryLogarithmOperation(ctx.mathExpression().accept(this));
        } else if (nonNull(ctx.COMMOM_LOGARITHM())) {
            return new CommonLogarithmOperation(ctx.mathExpression().accept(this));
        } else if (nonNull(ctx.NATURAL_LOGARITHM())) {
            return new NaturalLogarithmOperation(ctx.mathExpression().accept(this));
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitVariableLogarithm(VariableLogarithmContext ctx) {
        return new LogarithmOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
    }

    @Override
    public AbstractOperation visitRoundingFunction(RoundingFunctionContext ctx) {
        if (ctx.R_HALF_EVEN() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.HALF_EVEN);
        } else if (ctx.R_DOWN() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.DOWN);
        } else if (ctx.R_CEILING() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.CEILING);
        } else if (ctx.R_FLOOR() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.FLOOR);
        } else if (ctx.R_HALF_UP() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.HALF_UP);
        } else if (ctx.R_UP() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.UP);
        } else if (ctx.R_HALF_DOWN() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this), RoundingEnum.HALF_DOWN);
        } else if (ctx.R_UNNECESSARY() != null) {
            return new PreciseNumberRoundingOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this),
                    RoundingEnum.UNNECESSARY);
        } else {
            throw new IllegalStateException("No rounding method defined for operation " + ctx.getText());
        }
    }

    @Override
    public AbstractOperation visitSequenceFunction(SequenceFunctionContext ctx) {
        if (sequenceVariableStack == null) {
            sequenceVariableStack = new Stack<>();
        }
        sequenceVariableStack.add(new ArrayList<>());

        AbstractOperation startIndexOperation = ctx.mathExpression(0).accept(this);
        AbstractOperation endIndexOperation = ctx.mathExpression(1).accept(this);
        AbstractOperation mathExpression = ctx.mathExpression(2).accept(this);

        List<SequenceVariableValueOperation> sequenceVariableContainer = sequenceVariableStack.pop();
        SequenceVariableValueOperation sequenceVariable = sequenceVariableContainer.isEmpty() ? null : sequenceVariableContainer.get(0);
        if (ctx.SUMMATION() != null) {
            return new PreciseSummationOperation(startIndexOperation, endIndexOperation, mathExpression, sequenceVariable);
        } else if (ctx.PRODUCT_SEQUENCE() != null) {
            return new PreciseProductOfSequenceOperation(startIndexOperation, endIndexOperation, mathExpression, sequenceVariable);
        }
        throw new IllegalStateException(String.format("Operation %s not implemented", ctx.getText()));
    }

    @Override
    public AbstractOperation visitDateParenthesis(DateParenthesisContext ctx) {
        AbstractOperation operation = ctx.dateOperation().accept(this);
        operation.applyingParenthesis();
        return operation;
    }

    @Override
    public AbstractOperation visitDateFunction(DateFunctionContext ctx) {
        AbstractOperation leftOperand = ctx.dateEntity().accept(this);
        int count = ctx.DATE_OPERATIONS().size();

        for (int i = 0; i < count; i++) { //@formatter:off
            String text = "'" + ctx.DATE_OPERATIONS(i).getSymbol().getText() + "'";
            if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_DAYS).equals(text)) {
                leftOperand = new DateAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_MONTHS).equals(text)) {
                leftOperand = new DateAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_YEARS).equals(text)) {
                leftOperand = new DateAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_DAYS).equals(text)) {
                leftOperand = new DateSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_MONTHS).equals(text)) {
                leftOperand = new DateSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_YEARS).equals(text)) {
                leftOperand = new DateSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_DAYS).equals(text)) {
                leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_MONTHS).equals(text)) {
                leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_YEARS).equals(text)) {
                leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else {
                throw new IllegalStateException(String.format("Operation %s not implemented", ctx.DATE_OPERATIONS(i).getText()));
            }//@formatter:on
        }
        return leftOperand;
    }

    @Override
    public AbstractOperation visitTimeParenthesis(TimeParenthesisContext ctx) {
        AbstractOperation operation = ctx.timeOperation().accept(this);
        operation.applyingParenthesis();
        return operation;
    }

    @Override
    public AbstractOperation visitTimeFunction(TimeFunctionContext ctx) {
        AbstractOperation leftOperand = ctx.timeEntity().accept(this);
        int count = ctx.TIME_OPERATIONS().size();

        for (int i = 0; i < count; i++) { //@formatter:off
            String text = "'" + ctx.TIME_OPERATIONS(i).getSymbol().getText() + "'";
            if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_HOURS).equals(text)) {
                leftOperand = new TimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_MINUTES).equals(text)) {
                leftOperand = new TimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_SECONDS).equals(text)) {
                leftOperand = new TimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_HOURS).equals(text)) {
                leftOperand = new TimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_MINUTES).equals(text)) {
                leftOperand = new TimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_SECONDS).equals(text)) {
                leftOperand = new TimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_HOURS).equals(text)) {
                leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_MINUTES).equals(text)) {
                leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_SECONDS).equals(text)) {
                leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else {
                throw new IllegalStateException(String.format("Operation %s not implemented", ctx.TIME_OPERATIONS(i).getText()));
            }
        }//@formatter:on
        return leftOperand;
    }

    @Override
    public AbstractOperation visitDateTimeParenthesis(DateTimeParenthesisContext ctx) {
        AbstractOperation operation = ctx.dateTimeOperation().accept(this);
        operation.applyingParenthesis();
        return operation;
    }

    @Override
    public AbstractOperation visitDateTimeFunction(DateTimeFunctionContext ctx) {
        AbstractOperation leftOperand = ctx.dateTimeEntity().accept(this);//@formatter:off
        int count = ctx.DATE_OPERATIONS().size() + ctx.TIME_OPERATIONS().size();

        for (int i = 0; i < count; i++) {
            String text = "'" + ctx.getChild(1 + (2 * i)).getText() + "'";
            if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_DAYS).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_MONTHS).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_YEARS).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_DAYS).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_MONTHS).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_YEARS).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_DAYS).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_MONTHS).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_YEARS).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_HOURS).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_MINUTES).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.PLUS_SECONDS).equals(text)) {
                leftOperand = new DateTimeAdditionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_HOURS).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_MINUTES).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.MINUS_SECONDS).equals(text)) {
                leftOperand = new DateTimeSubtractionOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_HOURS).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_MINUTES).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
            } else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.SET_SECONDS).equals(text)) {
                leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
            } else {
                throw new IllegalStateException(String.format("Operation %s not implemented", ctx.DATE_OPERATIONS(i).getText()));
            }//@formatter:on
        }
        return leftOperand;
    }

    @Override
    public AbstractOperation visitFunction(FunctionContext ctx) {
        if (functionOperations == null) {
            functionOperations = new ArrayList<>();
        }
        List<AbstractOperation> parameters = new ArrayList<>();
        for (AllEntityTypesContext entityType : ctx.allEntityTypes()) {
            parameters.add(entityType.accept(this));
        }
        FunctionOperation functionOperation = new FunctionOperation(ctx.IDENTIFIER().getText(), parameters, ctx.NO_CACHE_FUNCTION_PREFIX() == null);
        functionOperations.add(functionOperation);
        return functionOperation;
    }

    @Override
    public AbstractOperation visitLogicalConstant(LogicalConstantContext ctx) {
        return new BooleanConstantValueOperation(ctx.getText());
    }

    @Override
    public AbstractOperation visitLogicalDecisionExpression(LogicalDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();

        for (int i = 0; i < logicalExpressions.size() - 1; i += 2) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(logicalExpressions.get(i + 1).accept(this));
        }
        operations.add(logicalExpressions.get(logicalExpressions.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitLogicalFunctionResult(LogicalFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    @Override
    public AbstractOperation visitLogicalVariable(LogicalVariableContext ctx) {
        return createNewVariable(ctx);
    }

    @Override
    public AbstractOperation visitMathDecisionExpression(MathDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();
        List<MathExpressionContext> mathExpressions = ctx.mathExpression();

        for (int i = 0; i < logicalExpressions.size(); i++) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(mathExpressions.get(i).accept(this));
        }
        operations.add(mathExpressions.get(mathExpressions.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitEulerConstant(EulerConstantContext ctx) {
        return new EulerNumberConstantValueOperation();
    }

    @Override
    public AbstractOperation visitPiConstant(PiConstantContext ctx) {
        return new PiNumberConstantValueOperation();
    }

    @Override
    public AbstractOperation visitSummationVariable(SummationVariableContext ctx) {
        if (sequenceVariableStack.peek().isEmpty()) {
            SequenceVariableValueOperation sequenceVariable = new SequenceVariableValueOperation("S");
            sequenceVariableStack.peek().add(sequenceVariable);
            return sequenceVariable;
        }
        return sequenceVariableStack.peek().get(0);
    }

    @Override
    public AbstractOperation visitProductSequenceVariable(ProductSequenceVariableContext ctx) {
        if (sequenceVariableStack.peek().isEmpty()) {
            SequenceVariableValueOperation sequenceVariable = new SequenceVariableValueOperation("P");
            sequenceVariableStack.peek().add(sequenceVariable);
            return sequenceVariable;
        }
        return sequenceVariableStack.peek().get(0);
    }

    @Override
    public AbstractOperation visitNumericConstant(NumericConstantContext ctx) {
        return new PreciseNumberConstantValueOperation(ctx.getText());
    }

    @Override
    public AbstractOperation visitNumericFunctionResult(NumericFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    @Override
    public AbstractOperation visitNumericVariable(NumericVariableContext ctx) {
        if (nonNull(ctx.IDENTIFIER())) {
            return createNewVariable(ctx);
        } else if (nonNull(ctx.NEGATIVE_IDENTIFIER())) {
            return new PreciseNegativeOperation(createNewVariable(ctx, VariableValueOperation::new, () -> ctx.getText().substring(1)));
        }
        throw new IllegalStateException("Invalid numeric operation: " + ctx.getText());
    }

    @Override
    public AbstractOperation visitStringDecisionExpression(StringDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();
        List<StringEntityContext> stringEntities = ctx.stringEntity();

        for (int i = 0; i < logicalExpressions.size(); i++) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(stringEntities.get(i).accept(this));
        }
        operations.add(stringEntities.get(stringEntities.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitStringConstant(StringConstantContext ctx) {
        return new StringConstantValueOperation(ctx.getText().substring(1, ctx.getText().length() - 1));
    }

    @Override
    public AbstractOperation visitStringFunctionResult(StringFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    @Override
    public AbstractOperation visitStringVariable(StringVariableContext ctx) {
        return createNewVariable(ctx);
    }

    @Override
    public AbstractOperation visitDateDecisionExpression(DateDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();
        List<DateOperationContext> dateOperations = ctx.dateOperation();

        for (int i = 0; i < logicalExpressions.size(); i++) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(dateOperations.get(i).accept(this));
        }
        operations.add(dateOperations.get(dateOperations.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitDateConstant(DateConstantContext ctx) {
        return new DateConstantValueOperation(ctx.getText());
    }

    @Override
    public AbstractOperation visitDateCurrentValue(DateCurrentValueContext ctx) {
        return new DateConstantValueOperation();
    }

    @Override
    public AbstractOperation visitDateVariable(DateVariableContext ctx) {
        return createNewVariable(ctx);
    }

    @Override
    public AbstractOperation visitDateFunctionResult(DateFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    @Override
    public AbstractOperation visitTimeDecisionExpression(TimeDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();
        List<TimeOperationContext> timeOperations = ctx.timeOperation();

        for (int i = 0; i < logicalExpressions.size(); i++) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(timeOperations.get(i).accept(this));
        }
        operations.add(timeOperations.get(timeOperations.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitTimeConstant(TimeConstantContext ctx) {
        return new TimeConstantValueOperation(ctx.getText());
    }

    @Override
    public AbstractOperation visitTimeCurrentValue(TimeCurrentValueContext ctx) {
        return new TimeConstantValueOperation();
    }

    @Override
    public AbstractOperation visitTimeVariable(TimeVariableContext ctx) {
        return createNewVariable(ctx);
    }

    @Override
    public AbstractOperation visitTimeFunctionResult(TimeFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    @Override
    public AbstractOperation visitDateTimeDecisionExpression(DateTimeDecisionExpressionContext ctx) {
        List<AbstractOperation> operations = new ArrayList<>();
        List<LogicalExpressionContext> logicalExpressions = ctx.logicalExpression();
        List<DateTimeOperationContext> dateTimeOperations = ctx.dateTimeOperation();

        for (int i = 0; i < logicalExpressions.size(); i++) {
            operations.add(logicalExpressions.get(i).accept(this));
            operations.add(dateTimeOperations.get(i).accept(this));
        }
        operations.add(dateTimeOperations.get(dateTimeOperations.size() - 1).accept(this));
        return new DecisionOperation(operations);
    }

    @Override
    public AbstractOperation visitDateTimeConstant(DateTimeConstantContext ctx) {
        return new DateTimeConstantValueOperation(ctx.getText());
    }

    @Override
    public AbstractOperation visitDateTimeCurrentValue(DateTimeCurrentValueContext ctx) {
        return new DateTimeConstantValueOperation();
    }

    @Override
    public AbstractOperation visitDateTimeVariable(DateTimeVariableContext ctx) {
        return createNewVariable(ctx);
    }

    @Override
    public AbstractOperation visitDateTimeFunctionResult(DateTimeFunctionResultContext ctx) {
        return ctx.function().accept(this);
    }

    private AbstractOperation createNewVariable(ParserRuleContext context) {
        return createNewVariable(context, VariableValueOperation::new, null);
    }

    private AbstractOperation createNewVariable(ParserRuleContext context, Function<String, AbstractVariableValueOperation> supplier,
                                                Supplier<String> nameSupplier) {
        String name;
        if (nameSupplier != null) {
            name = nameSupplier.get();
        } else {
            if (context.getChildCount() == 2) {
                name = context.getChild(1).getText();
            } else {
                name = context.getText();
            }
        }

        boolean containsAssignedVariable = this.parserContext.getAssignedVariables() != null
                && this.parserContext.getAssignedVariables().containsKey(name);
        boolean containsVariable = this.parserContext.getVariables() != null && this.parserContext.getVariables().containsKey(name);
        if (containsAssignedVariable && containsVariable) {
            throw new IllegalStateException(String.format("Duplicate variables named '%s' on current sentence", name));
        }

        if (containsAssignedVariable) {
            AssignedVariableOperation valueOperation = this.parserContext.getAssignedVariables().get(name);
            if (valueOperation == null) {
                throw new IllegalStateException(
                        String.format("Assigned variable '%s' is not declared before the operation '%s'", name, context.getParent().getText()));
            }
            return valueOperation;
        } else {
            if (!this.parserContext.getVariables().containsKey(name)) {
                this.parserContext.getVariables().put(name, supplier.apply(name));
            }
            return this.parserContext.getVariables().get(name);
        }
    }

}
