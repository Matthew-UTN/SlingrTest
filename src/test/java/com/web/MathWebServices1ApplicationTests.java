package com.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.Service.MathWebService1;

@SpringBootTest
class MathWebServices1ApplicationTests {
	
	MathWebService1 instance = new MathWebService1();

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testAddition() throws Exception {
		String expression = "2+3";
		
		String expectedResult = "5.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "2+-3";
		
		expectedResult = "-1.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "2-+3";
			
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSubtraction() throws Exception {
		String expression = "2-3";
		
		String expectedResult = "-1.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "2--2";
		
		expectedResult = "4.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testMultiplication() throws Exception {
		String expression = "4*4";
		
		String expectedResult = "16.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "4*-4";
		
		expectedResult = "-16.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "-4*4";
	
		expectedResult = "-16.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "-4*-4";
		
		expectedResult = "16.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testDivision() throws Exception {
		String expression = "10/2";
		
		String expectedResult = "5.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "-1/2";
		
		expectedResult = "-0.5";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "1/-2";
		
		expectedResult = "-0.5";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "-1/-2";
		
		expectedResult = "0.5";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSqrt() throws Exception {
		String expression = "sqrt(4)";
		
		String expectedResult = "2.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
		
		expression = "-sqrt(4)";
		
		expectedResult = "-2.0";
		
		result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}

	
	
	@Test
	public void testExpression1() throws Exception {
		String expression = "2*(7-3)";
		
		MathWebService1 instance = new MathWebService1();
		
		String expectedResult = "8.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression2() throws Exception {
		String expression = "1.2*(2+4.5)";
		
		MathWebService1 instance = new MathWebService1();
		
		String expectedResult = "7.8";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression3() throws Exception {
		String expression = "2+3*sqrt(4)";
		
		MathWebService1 instance = new MathWebService1();
		
		String expectedResult = "8.0";
		
		String result = instance.solve(expression);
		
		assertEquals(expectedResult, result);
	}	

}
