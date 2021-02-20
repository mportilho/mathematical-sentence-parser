grammar MathematicalSentenceParserGrammar;

@header {
    package io.github.mportilho.mathsentenceparser.grammar;
}

options {
    language=Java;
}

/* ########################################  Lexical rules  ######################################## */

IF : 'if';
THEN : 'then';
ELSE : 'else';
ELSEIF : 'elsif';
ENDIF : 'endif';

AND : 'and' ;
OR  : 'or' ;
XOR : 'xor' ;
XNOR : 'xnor' ;
NAND : 'nand' ;
NOR : 'nor' ;
TRUE  : 'true' ;
FALSE : 'false' ;

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;
PERCENT : '%';
MODULO : 'mod';
MODULUS :  '|';
EXCLAMATION : '!';
EXPONENTIATION : '^';
ROOT : 'root' | '\u221A' ;
SQRT : 'sqrt' ;
BINARY_LOGARITHM : 'lb';
NATURAL_LOGARITHM : 'ln';
COMMOM_LOGARITHM : 'log10';
LOGARITHM : 'log';
SUMMATION : 'S[';
PRODUCT_SEQUENCE : 'P[';
SUMMATION_VARIABLE : 'S' ;
PRODUCT_SEQUENCE_VARIABLE : 'P' ;
MINUS_PARENTHESIS : MinusParenthesisOperator;

SINE : 'sin';
COSINE : 'cos';
TANGENT : 'tan';
ARCSINE : 'asin';
ARCCOSINE : 'acos';
ARCTANGENT : 'atan';
ARCTANGENT2 : 'atan2';
HYPERBOLIC_SINE : 'sinh';
HYPERBOLIC_COSINE : 'cosh';
HYPERBOLIC_TANGENT : 'tanh';
INVERSE_HYPERBOLIC_SINE : 'asinh';
INVERSE_HYPERBOLIC_COSINE : 'acosh';
INVERSE_HYPERBOLIC_TANGENT : 'atanh';

PI : MINUS? ('pi' | 'PI') ;
EULER : MINUS? 'E' ;
DEGREE : '\u00B0' | 'deggre' | 'deggres' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '=' ;
NEQ:  '!=' | '<>';
NOT : '~' | '¬';

// Rounding Tokens
R_UP : 'up';
R_DOWN : 'down';
R_CEILING : 'ceiling';
R_FLOOR : 'floor';
R_HALF_UP : 'halfUp';
R_HALF_DOWN : 'halfDown';
R_HALF_EVEN : 'halfEven';
R_UNNECESSARY : 'unnecessary';

// Time Tokens
DATE_OPERATIONS
    : PLUS_DAYS | PLUS_MONTHS | PLUS_YEARS
    | MINUS_DAYS | MINUS_MONTHS | MINUS_YEARS
    | WITH_DAYS | WITH_MONTHS | WITH_YEARS
;
TIME_OPERATIONS
    : PLUS_HOURS | PLUS_MINUTES | PLUS_SECONDS
    | MINUS_HOURS | MINUS_MINUTES | MINUS_SECONDS
    | WITH_HOURS | WITH_MINUTES | WITH_SECONDS
    ;
NOW_DATE: 'currDate' ;
NOW_TIME: 'currTime' ;
NOW_DATETIME: 'currDateTime' ;

PLUS_DAYS : 'plusDays';
PLUS_MONTHS : 'plusMonths';
PLUS_YEARS : 'plusYears';
PLUS_HOURS : 'plusHours';
PLUS_MINUTES : 'plusMinutes';
PLUS_SECONDS : 'plusSeconds';

MINUS_DAYS : 'minusDays';
MINUS_MONTHS : 'minusMonths';
MINUS_YEARS : 'minusYears';
MINUS_HOURS : 'minusHours';
MINUS_MINUTES : 'minusMinutes';
MINUS_SECONDS : 'minusSeconds';

WITH_DAYS : 'withDays';
WITH_MONTHS : 'withMonths';
WITH_YEARS : 'withYears';
WITH_HOURS : 'withHours';
WITH_MINUTES : 'withMinutes';
WITH_SECONDS : 'withSeconds';

// Grammar Assist Tokens
LPAREN : '(' ;
RPAREN : ')' ;
LBLACKET : '[' ;
RBLACKET : ']' ;
QUOTES : '\'';
ASSIGNMENT : ':=' ;
COMMA : ',';
SEMI : ';' ;
PERIOD : '.';
CONTAINS: 'contains' ;
JSON_PATH:  '$.' RawTextNoWhiteSpace;
FUNCTION_PREFIX: 'f.' ;

// DECIMAL, IDENTIFIER, and OTHER TYPES
IDENTIFIER : IdentifierText ;
NEGATIVE_IDENTIFIER: NegativeSymbol IdentifierText ;
STRING: QUOTES (~ '\'' )*  QUOTES;
NUMBER: Decimal | OctalDigits | HexDigits;
POSITIVE: PositiveNumber ;
DATE: NumberPart NumberPart NumberPart NumberPart MINUS MONTH_PART MINUS DAY_PART ;
TIME: HOUR_PART Colon MINUTE_SECOND_PART (Colon MINUTE_SECOND_PART)? ;
DATETIME: DATE 'T' TIME ;

// TYPE CASTING
BOOLEAN_TYPE: '<bool>';
NUMBER_TYPE: '<number>';
STRING_TYPE: '<text>';
DATE_TYPE: '<date>' ;
TIME_TYPE: '<time>' ;
DATETIME_TYPE: '<datetime>' ;
LIST_TYPE: '<list>' ;

ASSIGNABLE_TYPES: STRING | DATE | TIME | DATETIME
  ;

// Small lexer parts
DAY_PART
  : '0' '1'..'9'
  | '1'..'2' NumberPart
  | '3' '0'..'1'
  ;
MONTH_PART
  : '0' '1'..'9'
  | '1' '0'..'2'
  ;
HOUR_PART
  : '0'..'1' NumberPart
  | '2' '0'..'3'
  ;
MINUTE_SECOND_PART: '0'..'5' NumberPart ;

// Fragments
fragment IdentifierText: [a-zA-Z_][a-zA-Z_0-9]* ;
fragment NegativeSymbol : '-';
fragment Decimal: NegativeSymbol?[0-9]+('.'[0-9]+)? ;
fragment PositiveNumber: [0-9]+ ;
fragment OctalDigits: '0' '0'..'7'+ ;
fragment HexDigits: '0x' ('0'..'9' | 'a'..'f' | 'A'..'F')+ ;
fragment IdentifierStart :   ('a'..'z'|'A'..'Z'|'_') ;
fragment IdentifierPart :   ('a'..'z'|'A'..'Z'|NumberPart|'_') ;
fragment NumberPart :   ('0'..'9');
fragment Colon : ':';
fragment Slash : '/';
fragment JsonPathSeparator : '->' ;
fragment RawTextNoWhiteSpace: ('\\' [\\"] | ~[ \\"\r\n\t\u000C] )+ ;
fragment RawAlphabeticNoWhiteSpace: ('\\' [\\"] | ~[ \\"\r\n\t\u000C0-9] )+ ;
fragment MinusParenthesisOperator: '-(';

// COMMENT and WS are stripped from the output token stream by sending to a different channel 'skip'
// COMMENT : '//' .+? ('\n'|EOF) -> skip ;
COMMENT
  : ( '//' ~[\r\n]* '\r'? '\n'
  | '/*' .*? '*/'
  | EOF
  ) -> skip
  ;

WS : [ \r\t\u000C\n]+ -> channel(HIDDEN) ;

/* ########################################  Grammar rules  ######################################## */

/* Grammar rules */

start: mathStart | logicalStart ;

mathStart
  : (assignmentExpression SEMI)* mathExpression EOF
  ;

logicalStart
  : (assignmentExpression SEMI)* logicalExpression? EOF
  ;

assignmentExpression
  : IDENTIFIER ASSIGNMENT allEntityTypes # assignOperation
  ;

logicalExpression
  : (NOT | EXCLAMATION) logicalExpression # notExpression
  | logicalExpression logicalOperator logicalExpression # logicExpression
  | mathExpression comparisonOperator mathExpression # comparisonMathExpression
  | stringEntity comparisonOperator stringEntity # stringExpression
  | dateOperation comparisonOperator dateOperation # dateExpression
  | timeOperation comparisonOperator timeOperation # timeExpression
  | dateTimeOperation comparisonOperator dateTimeOperation # dateTimeExpression
  | listFunction # listExpression
  | LPAREN logicalExpression RPAREN # logicalParenthesis
  | logicalEntity # logicalValue
  ;

mathExpression
  : LPAREN mathExpression RPAREN # mathParenthesis
  | MINUS_PARENTHESIS mathExpression RPAREN # negateMathParenthesis
  | mathExpression PERCENT # percentExpression
  | mathExpression EXCLAMATION # factorialExpression
  | mathExpression ROOT mathExpression # rootExpression
  | SQRT LPAREN mathExpression RPAREN # squareRootExpression
  | <assoc=right> mathExpression EXPONENTIATION mathExpression # exponentiationExpression
  | mathExpression mathExpression # implicitMultiplicationExpression
  | mathExpression (MULT | DIV | MODULO) mathExpression # multiplicationExpression
  | mathExpression (PLUS | MINUS) mathExpression # sumExpression
  | MODULUS mathExpression MODULUS # modulusExpression
  | logarithmFunction # logarithmExpression
  | roundingFunction # roundingExpression
  | sequenceFunction # sequenceExpression
  | mathExpression DEGREE # degreeExpression
  | SINE LPAREN mathExpression RPAREN #sineExpression
  | COSINE LPAREN mathExpression RPAREN #cosineExpression
  | TANGENT LPAREN mathExpression RPAREN #tangentExpression
  | ARCSINE LPAREN mathExpression RPAREN #arcsineExpression
  | ARCCOSINE LPAREN mathExpression RPAREN #arccosineExpression
  | ARCTANGENT LPAREN mathExpression RPAREN #arctangentExpression
  | ARCTANGENT2 LPAREN mathExpression COMMA mathExpression RPAREN #arctangent2Expression
  | HYPERBOLIC_SINE LPAREN mathExpression RPAREN #hyperbolicSineExpression
  | HYPERBOLIC_COSINE LPAREN mathExpression RPAREN #hyperbolicCosineExpression
  | HYPERBOLIC_TANGENT LPAREN mathExpression RPAREN #hyperbolicTangentExpression
  | INVERSE_HYPERBOLIC_SINE LPAREN mathExpression RPAREN #inverseHyperbolicSineExpression
  | INVERSE_HYPERBOLIC_COSINE LPAREN mathExpression RPAREN #inverseHyperbolicCosineExpression
  | INVERSE_HYPERBOLIC_TANGENT LPAREN mathExpression RPAREN #inverseHyperbolicTangentExpression
  | numericEntity # numberValue
  ;

logarithmFunction
  : (BINARY_LOGARITHM | NATURAL_LOGARITHM | COMMOM_LOGARITHM) LPAREN mathExpression RPAREN #fixedLogarithm
  | LOGARITHM LPAREN mathExpression COMMA mathExpression RPAREN # variableLogarithm
  ;

roundingFunction
  : (R_UP | R_DOWN | R_CEILING | R_FLOOR | R_HALF_UP | R_HALF_DOWN | R_HALF_EVEN | R_UNNECESSARY) LPAREN mathExpression COMMA mathExpression RPAREN;

sequenceFunction
  : (SUMMATION | PRODUCT_SEQUENCE) mathExpression COMMA mathExpression RBLACKET LPAREN mathExpression RPAREN
  ;

dateOperation: dateEntity (DATE_OPERATIONS mathExpression (AND DATE_OPERATIONS mathExpression)*)? ;
timeOperation: timeEntity (TIME_OPERATIONS mathExpression (AND TIME_OPERATIONS mathExpression)*)? ;
dateTimeOperation: dateTimeEntity ((DATE_OPERATIONS | TIME_OPERATIONS) mathExpression (AND (DATE_OPERATIONS | TIME_OPERATIONS) mathExpression)*)? ;

function
  : NOT? FUNCTION_PREFIX IDENTIFIER LPAREN (allEntityTypes (COMMA  allEntityTypes)*)* RPAREN
  ;

listFunction
  : listEntity CONTAINS allEntityTypes # listContainsOperation
  ;

comparisonOperator
  : (GT | GE | LT | LE | EQ | NEQ)
  ;

logicalOperator
  : AND | OR | (NAND | NOR | XOR | XNOR) | (EQ | NEQ)
  ;

allEntityTypes
  : stringEntity
  | dateOperation
  | timeOperation
  | dateTimeOperation
  | logicalExpression
  | mathExpression
  | listEntity
  | jsonPathExpression
  ;

logicalEntity
  : (TRUE | FALSE) # logicalConstant
  | IF logicalExpression THEN logicalExpression (ELSEIF logicalExpression THEN logicalExpression)? ELSE logicalExpression ENDIF # logicalDecisionExpression
  | BOOLEAN_TYPE? jsonPathExpression # logicalJsonPath
  | BOOLEAN_TYPE? function # logicalFunctionResult
  | BOOLEAN_TYPE? IDENTIFIER # logicalVariable
  ;

numericEntity
  : IF logicalExpression THEN mathExpression (ELSEIF logicalExpression THEN mathExpression)? ELSE mathExpression ENDIF # mathDecisionExpression
  | EULER # eulerConstant
  | PI # piConstant
  | SUMMATION_VARIABLE # summationVariable
  | PRODUCT_SEQUENCE_VARIABLE # productSequenceVariable
  | NUMBER # numericConstant
  | NUMBER_TYPE? jsonPathExpression # numericJsonPath
  | NUMBER_TYPE? function # numericFunctionResult
  | (NUMBER_TYPE? IDENTIFIER | NEGATIVE_IDENTIFIER) # numericVariable
  ;

stringEntity
  : IF logicalExpression THEN stringEntity (ELSEIF logicalExpression THEN stringEntity)? ELSE stringEntity ENDIF # stringDecisionExpression
  | STRING # stringConstant
  | STRING_TYPE? jsonPathExpression # stringJsonPath
  | STRING_TYPE? function # stringFunctionResult
  | STRING_TYPE? IDENTIFIER # stringVariable
  ;

dateEntity
  : IF logicalExpression THEN dateOperation (ELSEIF logicalExpression THEN dateOperation)? ELSE dateOperation ENDIF # dateDecisionExpression
  | DATE # dateConstant
  | NOW_DATE # dateCurrentValue
  | DATE_TYPE? IDENTIFIER # dateVariable
  | DATE_TYPE? function # dateFunctionResult
  | DATE_TYPE? jsonPathExpression # dateJsonPath
  ;

timeEntity
  : IF logicalExpression THEN timeOperation (ELSEIF logicalExpression THEN timeOperation)? ELSE timeOperation ENDIF # timeDecisionExpression
  | TIME # timeConstant
  | NOW_TIME # timeCurrentValue
  | TIME_TYPE? IDENTIFIER # timeVariable
  | TIME_TYPE? function # timeFunctionResult
  | TIME_TYPE? jsonPathExpression # timeJsonPath
  ;

dateTimeEntity
  : IF logicalExpression THEN dateTimeOperation (ELSEIF logicalExpression THEN dateTimeOperation)? ELSE dateTimeOperation ENDIF # dateTimeDecisionExpression
  | DATETIME # dateTimeConstant
  | NOW_DATETIME # dateTimeCurrentValue
  | DATETIME_TYPE? IDENTIFIER # dateTimeVariable
  | DATETIME_TYPE? function # dateTimeFunctionResult
  | DATETIME_TYPE? jsonPathExpression # dateTimeJsonPath
  ;

jsonPathExpression
  : IF logicalExpression THEN jsonPathExpression (ELSEIF logicalExpression THEN jsonPathExpression)? ELSE jsonPathExpression ENDIF # jsonPathDecisionExpression
  | IDENTIFIER JSON_PATH # jsonPathValue
  ;

listEntity
  : IF logicalExpression THEN listFunction (ELSEIF logicalExpression THEN listFunction)? ELSE listFunction ENDIF # listDecisionExpression
  | LBLACKET NUMBER (COMMA NUMBER)* RBLACKET # listOfNumbers
  | LBLACKET (TRUE | FALSE) (COMMA (TRUE | FALSE))* RBLACKET # listOfBooleans
  | LBLACKET STRING (COMMA STRING)* RBLACKET # listOfStrings
  | LBLACKET DATE (COMMA DATE)* RBLACKET # listOfDates
  | LBLACKET TIME (COMMA TIME)* RBLACKET # listOfTimes
  | LBLACKET DATETIME (COMMA DATETIME)* RBLACKET # listOfDateTimes
  | LBLACKET IDENTIFIER (COMMA IDENTIFIER)* RBLACKET # listOfVariables
  | LBLACKET jsonPathExpression (COMMA jsonPathExpression)* RBLACKET # listOfJsonPath
  | LBLACKET function RBLACKET # listOfFunctionResult
  | LIST_TYPE function # listFromFunction
  | LIST_TYPE jsonPathExpression # listFromJsonPath
  | LIST_TYPE IDENTIFIER # listVariable
  ;