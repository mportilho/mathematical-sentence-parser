// Generated from C:/Users/marce/IdeaProjects/ExpressionReader/src/io/github/mportilho/mathsentenceparser\MathematicalSentenceParserGrammar.g4 by ANTLR 4.9.1

    package io.github.mportilho.mathsentenceparser.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MathematicalSentenceParserGrammarParser}.
 */
public interface MathematicalSentenceParserGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(MathematicalSentenceParserGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(MathematicalSentenceParserGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#mathStart}.
	 * @param ctx the parse tree
	 */
	void enterMathStart(MathematicalSentenceParserGrammarParser.MathStartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#mathStart}.
	 * @param ctx the parse tree
	 */
	void exitMathStart(MathematicalSentenceParserGrammarParser.MathStartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#logicalStart}.
	 * @param ctx the parse tree
	 */
	void enterLogicalStart(MathematicalSentenceParserGrammarParser.LogicalStartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#logicalStart}.
	 * @param ctx the parse tree
	 */
	void exitLogicalStart(MathematicalSentenceParserGrammarParser.LogicalStartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignOperation}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignOperation(MathematicalSentenceParserGrammarParser.AssignOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignOperation}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignOperation(MathematicalSentenceParserGrammarParser.AssignOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeExpression(MathematicalSentenceParserGrammarParser.DateTimeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeExpression(MathematicalSentenceParserGrammarParser.DateTimeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(MathematicalSentenceParserGrammarParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(MathematicalSentenceParserGrammarParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalValue(MathematicalSentenceParserGrammarParser.LogicalValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalValue(MathematicalSentenceParserGrammarParser.LogicalValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpression(MathematicalSentenceParserGrammarParser.LogicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpression(MathematicalSentenceParserGrammarParser.LogicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterDateExpression(MathematicalSentenceParserGrammarParser.DateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitDateExpression(MathematicalSentenceParserGrammarParser.DateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MathematicalSentenceParserGrammarParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MathematicalSentenceParserGrammarParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterListExpression(MathematicalSentenceParserGrammarParser.ListExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitListExpression(MathematicalSentenceParserGrammarParser.ListExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonMathExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonMathExpression(MathematicalSentenceParserGrammarParser.ComparisonMathExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonMathExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonMathExpression(MathematicalSentenceParserGrammarParser.ComparisonMathExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterTimeExpression(MathematicalSentenceParserGrammarParser.TimeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitTimeExpression(MathematicalSentenceParserGrammarParser.TimeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalParenthesis(MathematicalSentenceParserGrammarParser.LogicalParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalParenthesis(MathematicalSentenceParserGrammarParser.LogicalParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inverseHyperbolicSineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterInverseHyperbolicSineExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicSineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inverseHyperbolicSineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitInverseHyperbolicSineExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicSineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequenceExpression(MathematicalSentenceParserGrammarParser.SequenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequenceExpression(MathematicalSentenceParserGrammarParser.SequenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hyperbolicSineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterHyperbolicSineExpression(MathematicalSentenceParserGrammarParser.HyperbolicSineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hyperbolicSineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitHyperbolicSineExpression(MathematicalSentenceParserGrammarParser.HyperbolicSineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inverseHyperbolicCosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterInverseHyperbolicCosineExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicCosineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inverseHyperbolicCosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitInverseHyperbolicCosineExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicCosineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modulusExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterModulusExpression(MathematicalSentenceParserGrammarParser.ModulusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modulusExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitModulusExpression(MathematicalSentenceParserGrammarParser.ModulusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterMathParenthesis(MathematicalSentenceParserGrammarParser.MathParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitMathParenthesis(MathematicalSentenceParserGrammarParser.MathParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterTangentExpression(MathematicalSentenceParserGrammarParser.TangentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitTangentExpression(MathematicalSentenceParserGrammarParser.TangentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arctangent2Expression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterArctangent2Expression(MathematicalSentenceParserGrammarParser.Arctangent2ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arctangent2Expression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitArctangent2Expression(MathematicalSentenceParserGrammarParser.Arctangent2ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code squareRootExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterSquareRootExpression(MathematicalSentenceParserGrammarParser.SquareRootExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code squareRootExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitSquareRootExpression(MathematicalSentenceParserGrammarParser.SquareRootExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rootExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterRootExpression(MathematicalSentenceParserGrammarParser.RootExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rootExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitRootExpression(MathematicalSentenceParserGrammarParser.RootExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code roundingExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterRoundingExpression(MathematicalSentenceParserGrammarParser.RoundingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code roundingExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitRoundingExpression(MathematicalSentenceParserGrammarParser.RoundingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberValue(MathematicalSentenceParserGrammarParser.NumberValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberValue(MathematicalSentenceParserGrammarParser.NumberValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hyperbolicTangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterHyperbolicTangentExpression(MathematicalSentenceParserGrammarParser.HyperbolicTangentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hyperbolicTangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitHyperbolicTangentExpression(MathematicalSentenceParserGrammarParser.HyperbolicTangentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hyperbolicCosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterHyperbolicCosineExpression(MathematicalSentenceParserGrammarParser.HyperbolicCosineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hyperbolicCosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitHyperbolicCosineExpression(MathematicalSentenceParserGrammarParser.HyperbolicCosineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implicitMultiplicationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterImplicitMultiplicationExpression(MathematicalSentenceParserGrammarParser.ImplicitMultiplicationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implicitMultiplicationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitImplicitMultiplicationExpression(MathematicalSentenceParserGrammarParser.ImplicitMultiplicationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpression(MathematicalSentenceParserGrammarParser.MultiplicationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpression(MathematicalSentenceParserGrammarParser.MultiplicationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code factorialExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterFactorialExpression(MathematicalSentenceParserGrammarParser.FactorialExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code factorialExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitFactorialExpression(MathematicalSentenceParserGrammarParser.FactorialExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negateMathParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegateMathParenthesis(MathematicalSentenceParserGrammarParser.NegateMathParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negateMathParenthesis}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegateMathParenthesis(MathematicalSentenceParserGrammarParser.NegateMathParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code percentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterPercentExpression(MathematicalSentenceParserGrammarParser.PercentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code percentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitPercentExpression(MathematicalSentenceParserGrammarParser.PercentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logarithmExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogarithmExpression(MathematicalSentenceParserGrammarParser.LogarithmExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logarithmExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogarithmExpression(MathematicalSentenceParserGrammarParser.LogarithmExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterSineExpression(MathematicalSentenceParserGrammarParser.SineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitSineExpression(MathematicalSentenceParserGrammarParser.SineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterCosineExpression(MathematicalSentenceParserGrammarParser.CosineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitCosineExpression(MathematicalSentenceParserGrammarParser.CosineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arctangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterArctangentExpression(MathematicalSentenceParserGrammarParser.ArctangentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arctangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitArctangentExpression(MathematicalSentenceParserGrammarParser.ArctangentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterSumExpression(MathematicalSentenceParserGrammarParser.SumExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitSumExpression(MathematicalSentenceParserGrammarParser.SumExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inverseHyperbolicTangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterInverseHyperbolicTangentExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicTangentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inverseHyperbolicTangentExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitInverseHyperbolicTangentExpression(MathematicalSentenceParserGrammarParser.InverseHyperbolicTangentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code degreeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterDegreeExpression(MathematicalSentenceParserGrammarParser.DegreeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code degreeExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitDegreeExpression(MathematicalSentenceParserGrammarParser.DegreeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exponentiationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterExponentiationExpression(MathematicalSentenceParserGrammarParser.ExponentiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exponentiationExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitExponentiationExpression(MathematicalSentenceParserGrammarParser.ExponentiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arccosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterArccosineExpression(MathematicalSentenceParserGrammarParser.ArccosineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arccosineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitArccosineExpression(MathematicalSentenceParserGrammarParser.ArccosineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arcsineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void enterArcsineExpression(MathematicalSentenceParserGrammarParser.ArcsineExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arcsineExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#mathExpression}.
	 * @param ctx the parse tree
	 */
	void exitArcsineExpression(MathematicalSentenceParserGrammarParser.ArcsineExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fixedLogarithm}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logarithmFunction}.
	 * @param ctx the parse tree
	 */
	void enterFixedLogarithm(MathematicalSentenceParserGrammarParser.FixedLogarithmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fixedLogarithm}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logarithmFunction}.
	 * @param ctx the parse tree
	 */
	void exitFixedLogarithm(MathematicalSentenceParserGrammarParser.FixedLogarithmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableLogarithm}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logarithmFunction}.
	 * @param ctx the parse tree
	 */
	void enterVariableLogarithm(MathematicalSentenceParserGrammarParser.VariableLogarithmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableLogarithm}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logarithmFunction}.
	 * @param ctx the parse tree
	 */
	void exitVariableLogarithm(MathematicalSentenceParserGrammarParser.VariableLogarithmContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#roundingFunction}.
	 * @param ctx the parse tree
	 */
	void enterRoundingFunction(MathematicalSentenceParserGrammarParser.RoundingFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#roundingFunction}.
	 * @param ctx the parse tree
	 */
	void exitRoundingFunction(MathematicalSentenceParserGrammarParser.RoundingFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#sequenceFunction}.
	 * @param ctx the parse tree
	 */
	void enterSequenceFunction(MathematicalSentenceParserGrammarParser.SequenceFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#sequenceFunction}.
	 * @param ctx the parse tree
	 */
	void exitSequenceFunction(MathematicalSentenceParserGrammarParser.SequenceFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#dateOperation}.
	 * @param ctx the parse tree
	 */
	void enterDateOperation(MathematicalSentenceParserGrammarParser.DateOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#dateOperation}.
	 * @param ctx the parse tree
	 */
	void exitDateOperation(MathematicalSentenceParserGrammarParser.DateOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#timeOperation}.
	 * @param ctx the parse tree
	 */
	void enterTimeOperation(MathematicalSentenceParserGrammarParser.TimeOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#timeOperation}.
	 * @param ctx the parse tree
	 */
	void exitTimeOperation(MathematicalSentenceParserGrammarParser.TimeOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#dateTimeOperation}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeOperation(MathematicalSentenceParserGrammarParser.DateTimeOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#dateTimeOperation}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeOperation(MathematicalSentenceParserGrammarParser.DateTimeOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MathematicalSentenceParserGrammarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MathematicalSentenceParserGrammarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listContainsOperation}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listFunction}.
	 * @param ctx the parse tree
	 */
	void enterListContainsOperation(MathematicalSentenceParserGrammarParser.ListContainsOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listContainsOperation}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listFunction}.
	 * @param ctx the parse tree
	 */
	void exitListContainsOperation(MathematicalSentenceParserGrammarParser.ListContainsOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(MathematicalSentenceParserGrammarParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(MathematicalSentenceParserGrammarParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(MathematicalSentenceParserGrammarParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(MathematicalSentenceParserGrammarParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathematicalSentenceParserGrammarParser#allEntityTypes}.
	 * @param ctx the parse tree
	 */
	void enterAllEntityTypes(MathematicalSentenceParserGrammarParser.AllEntityTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathematicalSentenceParserGrammarParser#allEntityTypes}.
	 * @param ctx the parse tree
	 */
	void exitAllEntityTypes(MathematicalSentenceParserGrammarParser.AllEntityTypesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void enterLogicalConstant(MathematicalSentenceParserGrammarParser.LogicalConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void exitLogicalConstant(MathematicalSentenceParserGrammarParser.LogicalConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void enterLogicalDecisionExpression(MathematicalSentenceParserGrammarParser.LogicalDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void exitLogicalDecisionExpression(MathematicalSentenceParserGrammarParser.LogicalDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void enterLogicalJsonPath(MathematicalSentenceParserGrammarParser.LogicalJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void exitLogicalJsonPath(MathematicalSentenceParserGrammarParser.LogicalJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void enterLogicalFunctionResult(MathematicalSentenceParserGrammarParser.LogicalFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void exitLogicalFunctionResult(MathematicalSentenceParserGrammarParser.LogicalFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void enterLogicalVariable(MathematicalSentenceParserGrammarParser.LogicalVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#logicalEntity}.
	 * @param ctx the parse tree
	 */
	void exitLogicalVariable(MathematicalSentenceParserGrammarParser.LogicalVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterMathDecisionExpression(MathematicalSentenceParserGrammarParser.MathDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitMathDecisionExpression(MathematicalSentenceParserGrammarParser.MathDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eulerConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterEulerConstant(MathematicalSentenceParserGrammarParser.EulerConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eulerConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitEulerConstant(MathematicalSentenceParserGrammarParser.EulerConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code piConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterPiConstant(MathematicalSentenceParserGrammarParser.PiConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code piConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitPiConstant(MathematicalSentenceParserGrammarParser.PiConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code summationVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterSummationVariable(MathematicalSentenceParserGrammarParser.SummationVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code summationVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitSummationVariable(MathematicalSentenceParserGrammarParser.SummationVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code productSequenceVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterProductSequenceVariable(MathematicalSentenceParserGrammarParser.ProductSequenceVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code productSequenceVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitProductSequenceVariable(MathematicalSentenceParserGrammarParser.ProductSequenceVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterNumericConstant(MathematicalSentenceParserGrammarParser.NumericConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitNumericConstant(MathematicalSentenceParserGrammarParser.NumericConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterNumericJsonPath(MathematicalSentenceParserGrammarParser.NumericJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitNumericJsonPath(MathematicalSentenceParserGrammarParser.NumericJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterNumericFunctionResult(MathematicalSentenceParserGrammarParser.NumericFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitNumericFunctionResult(MathematicalSentenceParserGrammarParser.NumericFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void enterNumericVariable(MathematicalSentenceParserGrammarParser.NumericVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#numericEntity}.
	 * @param ctx the parse tree
	 */
	void exitNumericVariable(MathematicalSentenceParserGrammarParser.NumericVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void enterStringDecisionExpression(MathematicalSentenceParserGrammarParser.StringDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void exitStringDecisionExpression(MathematicalSentenceParserGrammarParser.StringDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(MathematicalSentenceParserGrammarParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(MathematicalSentenceParserGrammarParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void enterStringJsonPath(MathematicalSentenceParserGrammarParser.StringJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void exitStringJsonPath(MathematicalSentenceParserGrammarParser.StringJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void enterStringFunctionResult(MathematicalSentenceParserGrammarParser.StringFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void exitStringFunctionResult(MathematicalSentenceParserGrammarParser.StringFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void enterStringVariable(MathematicalSentenceParserGrammarParser.StringVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#stringEntity}.
	 * @param ctx the parse tree
	 */
	void exitStringVariable(MathematicalSentenceParserGrammarParser.StringVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateDecisionExpression(MathematicalSentenceParserGrammarParser.DateDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateDecisionExpression(MathematicalSentenceParserGrammarParser.DateDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateConstant(MathematicalSentenceParserGrammarParser.DateConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateConstant(MathematicalSentenceParserGrammarParser.DateConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateCurrentValue(MathematicalSentenceParserGrammarParser.DateCurrentValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateCurrentValue(MathematicalSentenceParserGrammarParser.DateCurrentValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateVariable(MathematicalSentenceParserGrammarParser.DateVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateVariable(MathematicalSentenceParserGrammarParser.DateVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateFunctionResult(MathematicalSentenceParserGrammarParser.DateFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateFunctionResult(MathematicalSentenceParserGrammarParser.DateFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateJsonPath(MathematicalSentenceParserGrammarParser.DateJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateJsonPath(MathematicalSentenceParserGrammarParser.DateJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeDecisionExpression(MathematicalSentenceParserGrammarParser.TimeDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeDecisionExpression(MathematicalSentenceParserGrammarParser.TimeDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeConstant(MathematicalSentenceParserGrammarParser.TimeConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeConstant(MathematicalSentenceParserGrammarParser.TimeConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeCurrentValue(MathematicalSentenceParserGrammarParser.TimeCurrentValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeCurrentValue(MathematicalSentenceParserGrammarParser.TimeCurrentValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeVariable(MathematicalSentenceParserGrammarParser.TimeVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeVariable(MathematicalSentenceParserGrammarParser.TimeVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeFunctionResult(MathematicalSentenceParserGrammarParser.TimeFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeFunctionResult(MathematicalSentenceParserGrammarParser.TimeFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void enterTimeJsonPath(MathematicalSentenceParserGrammarParser.TimeJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#timeEntity}.
	 * @param ctx the parse tree
	 */
	void exitTimeJsonPath(MathematicalSentenceParserGrammarParser.TimeJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeDecisionExpression(MathematicalSentenceParserGrammarParser.DateTimeDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeDecisionExpression(MathematicalSentenceParserGrammarParser.DateTimeDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeConstant(MathematicalSentenceParserGrammarParser.DateTimeConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeConstant}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeConstant(MathematicalSentenceParserGrammarParser.DateTimeConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeCurrentValue(MathematicalSentenceParserGrammarParser.DateTimeCurrentValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeCurrentValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeCurrentValue(MathematicalSentenceParserGrammarParser.DateTimeCurrentValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeVariable(MathematicalSentenceParserGrammarParser.DateTimeVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeVariable(MathematicalSentenceParserGrammarParser.DateTimeVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeFunctionResult(MathematicalSentenceParserGrammarParser.DateTimeFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeFunctionResult(MathematicalSentenceParserGrammarParser.DateTimeFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateTimeJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeJsonPath(MathematicalSentenceParserGrammarParser.DateTimeJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateTimeJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#dateTimeEntity}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeJsonPath(MathematicalSentenceParserGrammarParser.DateTimeJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonPathDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#jsonPathExpression}.
	 * @param ctx the parse tree
	 */
	void enterJsonPathDecisionExpression(MathematicalSentenceParserGrammarParser.JsonPathDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonPathDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#jsonPathExpression}.
	 * @param ctx the parse tree
	 */
	void exitJsonPathDecisionExpression(MathematicalSentenceParserGrammarParser.JsonPathDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonPathValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#jsonPathExpression}.
	 * @param ctx the parse tree
	 */
	void enterJsonPathValue(MathematicalSentenceParserGrammarParser.JsonPathValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonPathValue}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#jsonPathExpression}.
	 * @param ctx the parse tree
	 */
	void exitJsonPathValue(MathematicalSentenceParserGrammarParser.JsonPathValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListDecisionExpression(MathematicalSentenceParserGrammarParser.ListDecisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listDecisionExpression}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListDecisionExpression(MathematicalSentenceParserGrammarParser.ListDecisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfNumbers}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfNumbers(MathematicalSentenceParserGrammarParser.ListOfNumbersContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfNumbers}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfNumbers(MathematicalSentenceParserGrammarParser.ListOfNumbersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfBooleans}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfBooleans(MathematicalSentenceParserGrammarParser.ListOfBooleansContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfBooleans}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfBooleans(MathematicalSentenceParserGrammarParser.ListOfBooleansContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfStrings}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfStrings(MathematicalSentenceParserGrammarParser.ListOfStringsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfStrings}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfStrings(MathematicalSentenceParserGrammarParser.ListOfStringsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfDates}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfDates(MathematicalSentenceParserGrammarParser.ListOfDatesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfDates}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfDates(MathematicalSentenceParserGrammarParser.ListOfDatesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfTimes}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfTimes(MathematicalSentenceParserGrammarParser.ListOfTimesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfTimes}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfTimes(MathematicalSentenceParserGrammarParser.ListOfTimesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfDateTimes}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfDateTimes(MathematicalSentenceParserGrammarParser.ListOfDateTimesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfDateTimes}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfDateTimes(MathematicalSentenceParserGrammarParser.ListOfDateTimesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfVariables}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfVariables(MathematicalSentenceParserGrammarParser.ListOfVariablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfVariables}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfVariables(MathematicalSentenceParserGrammarParser.ListOfVariablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfJsonPath(MathematicalSentenceParserGrammarParser.ListOfJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfJsonPath(MathematicalSentenceParserGrammarParser.ListOfJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOfFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListOfFunctionResult(MathematicalSentenceParserGrammarParser.ListOfFunctionResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOfFunctionResult}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListOfFunctionResult(MathematicalSentenceParserGrammarParser.ListOfFunctionResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listFromFunction}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListFromFunction(MathematicalSentenceParserGrammarParser.ListFromFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listFromFunction}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListFromFunction(MathematicalSentenceParserGrammarParser.ListFromFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listFromJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListFromJsonPath(MathematicalSentenceParserGrammarParser.ListFromJsonPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listFromJsonPath}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListFromJsonPath(MathematicalSentenceParserGrammarParser.ListFromJsonPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void enterListVariable(MathematicalSentenceParserGrammarParser.ListVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listVariable}
	 * labeled alternative in {@link MathematicalSentenceParserGrammarParser#listEntity}.
	 * @param ctx the parse tree
	 */
	void exitListVariable(MathematicalSentenceParserGrammarParser.ListVariableContext ctx);
}