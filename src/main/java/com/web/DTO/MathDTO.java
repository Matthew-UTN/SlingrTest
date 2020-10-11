package com.web.DTO;

public class MathDTO {

	private String expression;
	
	private int precision;
	
	
	
	public String getExpression() {
		return expression;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public int getPrecision() {
		return precision;
	}
	
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	
	
	public MathDTO(String expression, int precision) {
		super();
		this.expression = expression;
		this.precision = precision;
	}
	
	public MathDTO() {
		super();
	}
	
	
	
}
