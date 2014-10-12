package com.fairfax.statement;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Math Expression Class to check parentheses balancing
 * @author Saravanakumar P
 * @version $Revision$
 */

public class MathExpression {
	
	private String expression;
	private Deque<Character> pStack = new ArrayDeque<Character>();
	
	public MathExpression(String expression){
		this.expression = expression;
	}
	
	/**
	 * Returns the left parentheses to themselves
	 * @param character
	 * @return
	 */
	protected Character getMatchForParentheses(char character) {
		switch (character) {
			case '(':
			case ')':
				return new Character('(');
			case '[':
			case ']':
				return new Character('[');
			case '{':
			case '}':
				return new Character('{');
			default:
				return null;
		}
	}
	
	public String analyse(){
		return isValid() ? "GOOD" : "BAD";
	}
	
	/**
	 * Returns true if the parenthesis in the statement match.
	 * @return boolean
	 */
	  public boolean isValid() {  
		  
		  if(expression == null || expression.length() == 0)
			  return false;

		  for (int index=0; index < expression.length(); index++){
			  
			  Character currentChar 		= expression.charAt(index);
			  Character matchingParenthesis = getMatchForParentheses(currentChar);
			  
			  /*for all the characters in between parenthesis*/
			  if(matchingParenthesis == null){
				  if(index+1 == expression.length() && !pStack.isEmpty())
					  return false;
			  }
			  else if(matchingParenthesis.charValue()  == currentChar)
				  /*Push the parenthesis to the stack*/
				  pStack.push(matchingParenthesis); 
			  else{
				  /*If parenthesis at the top of the stack is not the respective opening parenthesis then return false*/
				 if(pStack.isEmpty() || !pStack.pop().equals(matchingParenthesis)){
					 return false;
				 }
			  }
		  }
		  return true;
	  }
}
