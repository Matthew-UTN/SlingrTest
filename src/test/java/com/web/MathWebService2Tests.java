package com.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.web.DTO.MathDTO;
import com.web.Service.MathWebService2;

class MathWebService2Tests {

	MathWebService2 instance = new MathWebService2();

	@Test
	public void testAddition() throws Exception {
		
		MathDTO dto = new MathDTO("2+3",1);
		
		String expectedResult = "5.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("2+-3",1);
		
		expectedResult = "-1.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("2-+3",1);
			
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSubtraction() throws Exception {
		
		MathDTO dto = new MathDTO("2-3",1);
		
		String expectedResult = "-1.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("2--2",1);
		
		expectedResult = "4.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testMultiplication() throws Exception {
		
		MathDTO dto = new MathDTO("4*4",1);
		
		String expectedResult = "16.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("4*-4",1);
		
		expectedResult = "-16.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("-4*4",1);
	
		expectedResult = "-16.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("-4*-4",1);
		
		expectedResult = "16.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testDivision() throws Exception {
		
		MathDTO dto = new MathDTO("10/2",1);
		
		String expectedResult = "5.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("-1/2",1);
		
		expectedResult = "-0.5";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("1/-2",1);
		
		expectedResult = "-0.5";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("-1/-2",1);
		
		expectedResult = "0.5";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testSqrt() throws Exception {
		MathDTO dto = new MathDTO("sqrt(4)",1);
		
		String expectedResult = "2.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
		dto = new MathDTO("-sqrt(4)",1);
		
		expectedResult = "-2.0";
		
		result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testPrecision() throws Exception {
		
		MathDTO dto = new MathDTO("2/3",4);
		
		String expectedResult = "0.6667";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
		
	}

	
	
	@Test
	public void testExpression1() throws Exception {
		MathDTO dto = new MathDTO("2*(7-3)",1);
		
		String expectedResult = "8.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression2() throws Exception {
		MathDTO dto = new MathDTO("1.2*(2+4.5)",1);
		
		String expectedResult = "7.8";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void testExpression3() throws Exception {
		MathDTO dto = new MathDTO("2+3*sqrt(4)",1);
		
		String expectedResult = "8.0";
		
		String result = instance.solve(dto);
		
		assertEquals(expectedResult, result);
	}
	
}
