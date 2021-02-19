package io.github.mportilho.mathsentenceparser.parser;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarLexer;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.*;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateAdditionOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateElementEnum;
import io.github.mportilho.mathsentenceparser.operation.date.DateSetOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateSubtractionOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateTimeAdditionOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateTimeSetOperation;
import io.github.mportilho.mathsentenceparser.operation.date.DateTimeSubtractionOperation;
import io.github.mportilho.mathsentenceparser.operation.date.TimeAdditionOperation;
import io.github.mportilho.mathsentenceparser.operation.date.TimeSetOperation;
import io.github.mportilho.mathsentenceparser.operation.date.TimeSubtractionOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.AndOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.EqualsOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.GreaterOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.GreaterOrEqualsOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.LessOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.LessOrEqualsOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.NandOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.NegationOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.NorOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.NotEqualsOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.OrOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.XnorOperation;
import io.github.mportilho.mathsentenceparser.operation.logic.XorOperation;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.EmptyOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseAdditionOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseDivisionOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseExponentialOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseFactorialOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseModuloOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseModulusOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseMultiplicationOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseNegativeOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseNumberRoundingOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseNumberRoundingOperation.RoundingEnum;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PrecisePercentualOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseRootOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSquareRootOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSubtractionOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.operation.value.constant.BooleanConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.DateConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.DateTimeConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.EulerNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PiNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.StringConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.TimeConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.ProvidedVariableValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.SequenceVariableValueOperation;

public class DefaultMathSentenceParserGrammarBaseVisitor extends MathematicalSentenceParserGrammarBaseVisitor<AbstractOperation>
		implements OperationSyntaxTreeGenerator {

	private OperationSyntaxTreeContext parserContext;
	private List<FunctionOperation> functionOperations;
	private Stack<List<SequenceVariableValueOperation>> sequenceVariableStack;

	public DefaultMathSentenceParserGrammarBaseVisitor() {
		this.parserContext = new OperationSyntaxTreeContext();
	}

	@Override
	public OperationSyntaxTree createOperationSyntaxTree(StartContext startContext) {
		OperationSyntaxTree syntaxTree = new OperationSyntaxTree(visit(startContext), parserContext);
		if (functionOperations != null) {
			for (FunctionOperation functionOperation : functionOperations) {
				if (functionOperation.isNoCache()) {
					functionOperation.caching(false);
				}
			}
		}
		return syntaxTree;
	}

	@Override
	public AbstractOperation visit(ParseTree tree) {
		return super.visit(tree);
	}

	@Override
	public AbstractOperation visitChildren(RuleNode node) {
		return super.visitChildren(node);
	}

	@Override
	public AbstractOperation visitTerminal(TerminalNode node) {
		return super.visitTerminal(node);
	}

	@Override
	public AbstractOperation visitErrorNode(ErrorNode node) {
		return super.visitErrorNode(node);
	}

	@Override
	public AbstractOperation visitStart(StartContext ctx) {
		return super.visitStart(ctx);
	}

	@Override
	public AbstractOperation visitMathStart(MathStartContext ctx) {
		AbstractOperation previousAssignmentOperation = null;
		for (AssignmentExpressionContext expressionContext : ctx.assignmentExpression()) {
			AbstractOperation assignmentOperation = expressionContext.accept(this);
			assignmentOperation.addParent(previousAssignmentOperation);
			previousAssignmentOperation = assignmentOperation;
		}
		AbstractOperation mathOperation = nonNull(ctx.mathExpression()) ? ctx.mathExpression().accept(this)
				: new EmptyOperation(OperationValueType.NUMBER, parserContext.getAssignedVariables());
		mathOperation.addParent(previousAssignmentOperation);
		return mathOperation;
	}

	@Override
	public AbstractOperation visitLogicalStart(LogicalStartContext ctx) {
		AbstractOperation previousAssignmentOperation = null;
		for (AssignmentExpressionContext expressionContext : ctx.assignmentExpression()) {
			AbstractOperation assignmentOperation = expressionContext.accept(this);
			assignmentOperation.addParent(previousAssignmentOperation);
			previousAssignmentOperation = assignmentOperation;
		}
		AbstractOperation logicalOperation = nonNull(ctx.logicalExpression()) ? ctx.logicalExpression().accept(this)
				: new EmptyOperation(OperationValueType.BOOLEAN, parserContext.getAssignedVariables());
		logicalOperation.addParent(previousAssignmentOperation);
		return logicalOperation;
	}

	@Override
	public AbstractOperation visitAssignOperation(AssignOperationContext ctx) {
		AbstractOperation abstractOperation = ctx.allEntityTypes().accept(this);
		AssignedVariableOperation operator = new AssignedVariableOperation(ctx.IDENTIFIER().getText(), abstractOperation);
		parserContext.getAssignedVariables().put(operator.getVariableName(), operator.getAssignedOperation());
		return operator;
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
	public AbstractOperation visitLogicalValue(LogicalValueContext ctx) {
		return super.visitLogicalValue(ctx);
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
	public AbstractOperation visitListExpression(ListExpressionContext ctx) {
		return super.visitListExpression(ctx);
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
		return ctx.logicalExpression().accept(this);
	}

	@Override
	public AbstractOperation visitInverseHyperbolicSineExpression(InverseHyperbolicSineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitSequenceExpression(SequenceExpressionContext ctx) {
		return super.visitSequenceExpression(ctx);
	}

	@Override
	public AbstractOperation visitHyperbolicSineExpression(HyperbolicSineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitInverseHyperbolicCosineExpression(InverseHyperbolicCosineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitArctangent2Expression(Arctangent2ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
	public AbstractOperation visitRoundingExpression(RoundingExpressionContext ctx) {
		return super.visitRoundingExpression(ctx);
	}

	@Override
	public AbstractOperation visitNumberValue(NumberValueContext ctx) {
		return super.visitNumberValue(ctx);
	}

	@Override
	public AbstractOperation visitHyperbolicTangentExpression(HyperbolicTangentExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitHyperbolicCosineExpression(HyperbolicCosineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitImplicitMultiplicationExpression(ImplicitMultiplicationExpressionContext ctx) {
		AbstractOperation leftOperation = ctx.mathExpression(0).accept(this);
		AbstractOperation rightOperation = ctx.mathExpression(1).accept(this);

		if (rightOperation instanceof PreciseNegativeOperation) {
			((PreciseNegativeOperation) rightOperation).negatingValue(false);
			return new PreciseSubtractionOperation(leftOperation, rightOperation);
		} else if ((rightOperation instanceof PreciseNumberConstantValueOperation
				&& ((PreciseNumberConstantValueOperation) rightOperation).getValue().toString().startsWith("-"))) {
			((PreciseNumberConstantValueOperation) rightOperation).transformToPositiveValue();
			return new PreciseSubtractionOperation(leftOperation, rightOperation);
		}

		rightOperation.applyingParenthesis();
		return new PreciseMultiplicationOperation(leftOperation, rightOperation, true);
	}

	@Override
	public AbstractOperation visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
		if (nonNull(ctx.MULT())) {
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
	public AbstractOperation visitLogarithmExpression(LogarithmExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitSineExpression(SineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitCosineExpression(CosineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitArctangentExpression(ArctangentExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDegreeExpression(DegreeExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitExponentiationExpression(ExponentiationExpressionContext ctx) {
		return new PreciseExponentialOperation(ctx.mathExpression(0).accept(this), ctx.mathExpression(1).accept(this));
	}

	@Override
	public AbstractOperation visitArccosineExpression(ArccosineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitArcsineExpression(ArcsineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitFixedLogarithm(FixedLogarithmContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitVariableLogarithm(VariableLogarithmContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
	public AbstractOperation visitDateOperation(DateOperationContext ctx) {
		AbstractOperation leftOperand = ctx.dateEntity().accept(this);
		for (int i = 0; i < ctx.DATE_OPERATIONS().size(); i++) { //@formatter:off
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
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_DAYS).equals(text)) {
				leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_MONTHS).equals(text)) {
				leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_YEARS).equals(text)) {
				leftOperand = new DateSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.YEAR);
			} else {
				throw new IllegalStateException(String.format("Operation %s not implemented", ctx.DATE_OPERATIONS(i).getText()));
			}//@formatter:on
		}
		return leftOperand;
	}

	@Override
	public AbstractOperation visitTimeOperation(TimeOperationContext ctx) {
		AbstractOperation leftOperand = ctx.timeEntity().accept(this);
		for (int i = 0; i < ctx.TIME_OPERATIONS().size(); i++) { //@formatter:off
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
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_HOURS).equals(text)) {
				leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_MINUTES).equals(text)) {
				leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_SECONDS).equals(text)) {
				leftOperand = new TimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
			} else {
				throw new IllegalStateException(String.format("Operation %s not implemented", ctx.TIME_OPERATIONS(i).getText()));
			}
		}//@formatter:on
		return leftOperand;
	}

	@Override
	public AbstractOperation visitDateTimeOperation(DateTimeOperationContext ctx) {
		AbstractOperation leftOperand = ctx.dateTimeEntity().accept(this);//@formatter:off
		for (int i = 0; i < ctx.DATE_OPERATIONS().size() + ctx.TIME_OPERATIONS().size(); i++) {
			String text = "'" + ctx.getChild(1 + (3 * i)).getText() + "'";
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
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_DAYS).equals(text)) {
				leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.DAY);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_MONTHS).equals(text)) {
				leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MONTH);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_YEARS).equals(text)) {
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
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_HOURS).equals(text)) {
				leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.HOUR);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_MINUTES).equals(text)) {
				leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.MINUTE);
			} else if (MathematicalSentenceParserGrammarLexer.VOCABULARY.getDisplayName(MathematicalSentenceParserGrammarLexer.WITH_SECONDS).equals(text)) {
				leftOperand = new DateTimeSetOperation(leftOperand, ctx.mathExpression(i).accept(this), DateElementEnum.SECOND);
			} else {
				throw new IllegalStateException(String.format("Operation %s not implemented", ctx.DATE_OPERATIONS(i).getText()));
			}//@formatter:on
		}
		return leftOperand;
	}

	@Override
	public AbstractOperation visitFunction(FunctionContext ctx) {
		List<AbstractOperation> parameters = new ArrayList<>();
		for (AllEntityTypesContext entityType : ctx.allEntityTypes()) {
			parameters.add(entityType.accept(this));
		}
		FunctionOperation functionOperation = new FunctionOperation(ctx.IDENTIFIER().getText(), parameters, ctx.NOT() != null);
		functionOperations.add(functionOperation);
		return functionOperation;
	}

	@Override
	public AbstractOperation visitListContainsOperation(ListContainsOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitComparisonOperator(ComparisonOperatorContext ctx) {
		return super.visitComparisonOperator(ctx);
	}

	@Override
	public AbstractOperation visitLogicalOperator(LogicalOperatorContext ctx) {
		return super.visitLogicalOperator(ctx);
	}

	@Override
	public AbstractOperation visitAllEntityTypes(AllEntityTypesContext ctx) {
		return super.visitAllEntityTypes(ctx);
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
	public AbstractOperation visitLogicalJsonPath(LogicalJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
	public AbstractOperation visitNumericJsonPath(NumericJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
			return new PreciseNegativeOperation(
					createNewVariable(ctx, name -> new ProvidedVariableValueOperation(name), () -> ctx.getText().substring(1)));
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
	public AbstractOperation visitStringJsonPath(StringJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
	public AbstractOperation visitDateJsonPath(DateJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
	public AbstractOperation visitTimeJsonPath(TimeJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public AbstractOperation visitDateTimeJsonPath(DateTimeJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitJsonPathDecisionExpression(JsonPathDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitJsonPathValue(JsonPathValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListDecisionExpression(ListDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfNumbers(ListOfNumbersContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfBooleans(ListOfBooleansContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfStrings(ListOfStringsContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfDates(ListOfDatesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfTimes(ListOfTimesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfDateTimes(ListOfDateTimesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfVariables(ListOfVariablesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfJsonPath(ListOfJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListOfFunctionResult(ListOfFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListFromFunction(ListFromFunctionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListFromJsonPath(ListFromJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListVariable(ListVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractOperation createNewVariable(ParserRuleContext context) {
		return createNewVariable(context, name -> new ProvidedVariableValueOperation(name), null);
	}

	private AbstractOperation createNewVariable(ParserRuleContext context, Function<String, AbstractVariableValueOperation> supplier,
			Supplier<String> nameSupplier) {
		String name = null;
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
		boolean containsVariable = this.parserContext.getProvidedVariables() != null && this.parserContext.getProvidedVariables().containsKey(name);
		if (containsAssignedVariable && containsVariable) {
			throw new IllegalStateException(String.format("Duplicate variables named '%s' on current sentence", name));
		}

		if (containsAssignedVariable) {
			AbstractOperation valueOperation = this.parserContext.getAssignedVariables().get(name);
			if (isNull(valueOperation)) {
				throw new IllegalStateException(
						String.format("Assigned variable '%s' is not declared before the operation '%s'", name, context.getParent().getText()));
			}
			return valueOperation;
		} else {
			if (!this.parserContext.getProvidedVariables().containsKey(name)) {
				this.parserContext.getProvidedVariables().put(name, supplier.apply(name));
			}
			return this.parserContext.getProvidedVariables().get(name);
		}
	}

}
