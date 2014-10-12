

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fairfax.statement.MathExpression;

public class MathExpressionTest {
	
	@Test
	public void testNullExpression() {
		MathExpression mExpression = new MathExpression(null);
		assertEquals("Must return BAD", "BAD", mExpression.analyse());
	}
	
	@Test
	public void testEmptyExpression() {
		MathExpression mExpression = new MathExpression("");
		assertEquals("Must return BAD", "BAD", mExpression.analyse());
	}
	
	@Test
	public void testSimpleExpressionWithNoBrackets() {
		MathExpression mExpression = new MathExpression("x+1");
		assertEquals("Must return GOOD", "GOOD", mExpression.analyse());
	}

	@Test
	public void testExpressionWithNoClosingBrackets() {
		MathExpression mExpression = new MathExpression("(x+1");
		assertEquals("Must return BAD", "BAD", mExpression.analyse());
	}
	
	@Test
	public void testExpressionWithMixedBrackets() {
		MathExpression mExpression = new MathExpression("([x+2)]");
		assertEquals("Must return BAD", "BAD", mExpression.analyse());
	}
	
	@Test
	public void testValidExpression() {
		MathExpression mExpression = new MathExpression("(x+2)");
		assertEquals("Must return GOOD", "GOOD", mExpression.analyse());
	}
	
	@Test
	public void testValidExpressionWithMultipleBrackets() {
		MathExpression mExpression = new MathExpression("([x+2][x+1])");
		assertEquals("Must return GOOD", "GOOD", mExpression.analyse());
	}
	
	@Test
	public void testValidComplexExpression() {
		MathExpression mExpression = new MathExpression("[({x*(x+1)*(x+2)*[a+b]})]");
		assertEquals("Must return GOOD", "GOOD", mExpression.analyse());
	}
	
	@Test
	public void testInValidComplexExpression() {
		MathExpression mExpression = new MathExpression("[({x*(x+1)*(x+2)*[a+b])]");
		assertEquals("Must return BAD", "BAD", mExpression.analyse());
	}
	
	@Test
	public void testValidMathematicalExpression() {
		MathExpression mExpression = new MathExpression("((3+29)/((3+(4*5)/6)*7))");
		assertEquals("Must return GOOD", "GOOD", mExpression.analyse());
	}

}
