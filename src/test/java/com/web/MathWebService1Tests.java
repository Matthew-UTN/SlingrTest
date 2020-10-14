package com.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.Service.MathWebService1;

@SpringBootTest
class MathWebService1Tests {
	
	MathWebService1 instance = new MathWebService1();
	
	int precision = 1;
	
	@Test
	public void testAddition() throws Exception {
		String expression = "2+3";
		
		String expectedResult = "5.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "2+-3";
		
		expectedResult = "-1.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "2-+3";
			
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSubtraction() throws Exception {
		String expression = "2-3";
		
		String expectedResult = "-1.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "2--2";
		
		expectedResult = "4.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testMultiplication() throws Exception {
		String expression = "4*4";
		
		String expectedResult = "16.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "4*-4";
		
		expectedResult = "-16.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "-4*4";
	
		expectedResult = "-16.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "-4*-4";
		
		expectedResult = "16.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testDivision() throws Exception {
		String expression = "10/2";
		
		String expectedResult = "5.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "-1/2";
		
		expectedResult = "-0.5";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "1/-2";
		
		expectedResult = "-0.5";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "-1/-2";
		
		expectedResult = "0.5";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSqrt() throws Exception {
		String expression = "sqrt(4)";
		
		String expectedResult = "2.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
		expression = "-sqrt(4)";
		
		expectedResult = "-2.0";
		
		result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testPrecision() throws Exception {
		
		String expression = "2/3";
		
		int precision = 4;
		
		String expectedResult = "0.6667";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
		
	}

	
	
	@Test
	public void testExpression1() throws Exception {
		String expression = "2*(7-3)";
		
		String expectedResult = "8.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression2() throws Exception {
		String expression = "1.2*(2+4.5)";
		
		String expectedResult = "7.8";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression3() throws Exception {
		String expression = "2+3*sqrt(4)";
		
		String expectedResult = "8.0";
		
		String result = instance.solve(expression,precision);
		
		assertEquals(expectedResult, result);
	}	

}
