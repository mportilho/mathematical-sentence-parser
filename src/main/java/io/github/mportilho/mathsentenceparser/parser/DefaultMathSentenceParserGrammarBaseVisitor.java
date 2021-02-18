package io.github.mportilho.mathsentenceparser.parser;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.*;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarVisitor;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class DefaultMathSentenceParserGrammarBaseVisitor implements MathematicalSentenceParserGrammarVisitor<AbstractOperation> {

	private ParserGrammarBaseVisitorContext context;

	@Override
	public AbstractOperation visit(ParseTree tree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitChildren(RuleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStart(StartContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitMathStart(MathStartContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalStart(LogicalStartContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitAssignOperation(AssignOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeExpression(DateTimeExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringExpression(StringExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalValue(LogicalValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicExpression(LogicExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateExpression(DateExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNotExpression(NotExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListExpression(ListExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitComparisonMathExpression(ComparisonMathExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeExpression(TimeExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalParenthesis(LogicalParenthesisContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitInverseHyperbolicSineExpression(InverseHyperbolicSineExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitSequenceExpression(SequenceExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitMathParenthesis(MathParenthesisContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitRootExpression(RootExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitRoundingExpression(RoundingExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNumberValue(NumberValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitFactorialExpression(FactorialExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNegateMathParenthesis(NegateMathParenthesisContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitPercentExpression(PercentExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitSequenceFunction(SequenceFunctionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateOperation(DateOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeOperation(TimeOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeOperation(DateTimeOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitFunction(FunctionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitListContainsOperation(ListContainsOperationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitComparisonOperator(ComparisonOperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalOperator(LogicalOperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitAllEntityTypes(AllEntityTypesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalConstant(LogicalConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalDecisionExpression(LogicalDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalJsonPath(LogicalJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalFunctionResult(LogicalFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitLogicalVariable(LogicalVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitMathDecisionExpression(MathDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitEulerConstant(EulerConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitPiConstant(PiConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitSummationVariable(SummationVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitProductSequenceVariable(ProductSequenceVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNumericConstant(NumericConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNumericJsonPath(NumericJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNumericFunctionResult(NumericFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitNumericVariable(NumericVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringDecisionExpression(StringDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringConstant(StringConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringJsonPath(StringJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringFunctionResult(StringFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitStringVariable(StringVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateDecisionExpression(DateDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateConstant(DateConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateCurrentValue(DateCurrentValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateVariable(DateVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateFunctionResult(DateFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateJsonPath(DateJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeDecisionExpression(TimeDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeConstant(TimeConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeCurrentValue(TimeCurrentValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeVariable(TimeVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeFunctionResult(TimeFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitTimeJsonPath(TimeJsonPathContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeDecisionExpression(DateTimeDecisionExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeConstant(DateTimeConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeCurrentValue(DateTimeCurrentValueContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeVariable(DateTimeVariableContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractOperation visitDateTimeFunctionResult(DateTimeFunctionResultContext ctx) {
		// TODO Auto-generated method stub
		return null;
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

}
