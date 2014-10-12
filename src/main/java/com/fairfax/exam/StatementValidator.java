package com.fairfax.exam;

import com.fairfax.statement.MathExpression;

public class StatementValidator {
	
	private static String testExpression(String expression){
		MathExpression mExpression = new MathExpression(expression);
		return (expression+ " : " + mExpression.analyse());
	}

	public static void main(String[] args) {
		
		String expressionWithNoParantheses = "x+1",
				simpleValidExpression = "(x+2)",
				expressionWithNoClosingParantheses = "(x+1",
				expressionWithMixedParantheses = "[(x+1])",
				complexValidExpression = "([x+2][x+1]):";
				
		
		System.out.println(testExpression(expressionWithNoParantheses));
		System.out.println(testExpression(simpleValidExpression));
		System.out.println(testExpression(expressionWithNoClosingParantheses));
		System.out.println(testExpression(expressionWithMixedParantheses));
		System.out.println(testExpression(complexValidExpression));

	}

}
