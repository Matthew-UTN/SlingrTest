package com.web.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.DTO.MathDTO;

@Service
public class MathWebService2 {
	
	public int NumNeg = 0;
	 public int Sqrt = 0;

	public String solve(MathDTO dto) throws Exception {
		
		try{
			
			String expression = dto.getExpression();
			
			int precision = dto.getPrecision();
			
			String total = solveParentheses(expression);
			
			Double toBeTruncated = Double.parseDouble(total);

			Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
					.setScale(precision, RoundingMode.HALF_UP)
					.doubleValue();
			
			total = String.valueOf(truncatedDouble);
			
			return total;
			
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}

	}
		 
	
	public String solveParentheses (String text) {
		
		
		while(text.contains("sqrt")) { // Need to solve the sqrt before it enters into the next While or it will loop forever.
			
			text = resolveSquareRoot(text);
		}
		
        while(text.contains("(") && text.contains(")")) { // Enters here if it detects a parentheses.
        	
            int start = 0;
            int end = 0;
            
            for (int i = 0; i < text.length(); i++) {
            	
                if (text.charAt(i) == '(') { // Saves the position of the start of the parentheses.
                	
                    start = i;
                }
                
                if (text.charAt(i) == ')') { // Saves the position of the end of the parentheses.

                    end = i;
                    
                    String currentParentheses = text.substring(start + 1, end);
                    String replacement = negativeFirstNumber(currentParentheses);
                    String toBeReplaced = text.substring(start, end+1);
                    text = text.replace(toBeReplaced, replacement);
                    
                    break;
                    
                }
            }
        }

        
        for (int i = 1; i < text.length(); i++) {
        	
            if (text.charAt(i)=='-' && (text.charAt(i-1)=='*' || text.charAt(i-1) == '/')) {
            	
                for (int j = i-1; j >= 0 ; j--) {
                	
                    if (text.charAt(j)=='+') { // Changes a negative number to positive
                    	
                        StringBuilder texto1 = new StringBuilder(text); 
                        texto1.replace(j, j+1, "-");
                        text = texto1.toString();
                        text = text.replaceFirst("-", "");
                        break;
                        
                    }
                    else if (text.charAt(j)=='-') {
                    	
                        StringBuilder texto1 = new StringBuilder(text);
                        texto1.replace(j, j+1, "+");
                        text = texto1.toString();
                        text = text.replaceFirst("-", "");
                        break;
                        
                    }
                }
            }
        }
        
        for (int i = 1; i < text.length(); i++) { // If for example there is 2--2 itll change to 2+2 or 2-+2 changes to 2-2
        	
            if (text.charAt(i) == '-' && (text.charAt(i-1) == '-' || text.charAt(i-1) == '+')) {
            	
                if (text.charAt(i-1) == '-') { // 2--2 to 2+2
                	
                    StringBuilder texto1 = new StringBuilder(text);
                    texto1.replace(i, i+1, "+");
                    text = texto1.toString();
                    text = text.replaceFirst("-", "");
                }
                else {
                	
                    StringBuilder texto1 = new StringBuilder(text);// 2+-2 to 2-2
                    texto1.replace(i, i+1, "-");
                    text = texto1.toString();
                    text = text.replaceFirst("\\+", "");
                }

            }else if(text.charAt(i) == '+' && (text.charAt(i-1) == '-' || text.charAt(i-1) == '+')) {
            	
                if (text.charAt(i-1) == '-') { // 2-+2 to 2-2
                	
                    StringBuilder texto1 = new StringBuilder(text);
                    texto1.replace(i, i+1, "-");
                    text = texto1.toString();
                    text = text.replaceFirst("-", "");
                }
                else{
                	
                    StringBuilder texto1 = new StringBuilder(text);// 2++2 to 2+2
                    texto1.replace(i, i+1, "+");
                    text = texto1.toString();
                    text = text.replaceFirst("\\+", "");
                }
            }
        }
        
        if (text.charAt(0) == '-') {
        	
            text = '0' + text;
        }

        return calculate(text);
    }
	

    private String negativeFirstNumber(String text) { // adds a 0 in front of the - so it wont crash because of a negative number being first 
        
        if (text.charAt(0) == '-') {
        	
            text = '0' + text;
        }
        String negNumSolved = calculate(text);
        return negNumSolved;
    }
    
    
    public String calculate(String text){
     	
     	String respuestaFinal = String.valueOf(addAndSubtract(text));
     	return respuestaFinal;
     
 	}
    
    
    public String resolveSquareRoot(String text) {//solves square root
    	
    	while (text.contains("sqrt")){
    		int start = 0;
    		int firstParentesis = 0;
            int end = 0;
            Double squareRoot = 0.0;
            
            
            for (int i = 0; i < text.length(); i++) {
            	
                if (text.charAt(i) == 's') { // Saves the position of where the sqrt starts
                	
                    start = i;
                    Sqrt = 1;
                }
                if (text.charAt(i) == '(') { // saves the position of the first parentheses
                	
                	firstParentesis = i;
                }
                if (text.charAt(i) == ')') { // saves the position of the second parentheses
                	
                    end = i;
                    
                    if(Sqrt ==1) {
	                    String currentSqrt = text.substring(firstParentesis+1, end);
	                    
	                    if(currentSqrt.contains("+")||currentSqrt.contains("-")||currentSqrt.contains("*")||currentSqrt.contains("/")) {
	                    	
	                    	squareRoot = Double.parseDouble(solveParentheses(currentSqrt));
	                    }else {
	                    	
	                    	squareRoot = Double.parseDouble(currentSqrt);
	                    }
	                    
	                    String replacement = String.valueOf(Math.sqrt(squareRoot));
	                    String toBeReplaced = text.substring(start,end+1);
	                    text = text.replace(toBeReplaced, replacement);
	                    
	                    Sqrt = 0;
	                    
                    break;
                    }
                }
            }
    	}
    	
    	return text;
    }
    

    public double addAndSubtract(String textCalc) {
    	
        for (int i = 1; i < textCalc.length(); i++) {
        	
            if(textCalc.charAt(i)=='-' && (textCalc.charAt(i-1)=='*' || textCalc.charAt(i-1)=='/')) { // Changes a number from negative to positive and saves that it was negative before
            	
                textCalc = textCalc.replace("-", "");
                NumNeg = 1;
            }
        }

        String[] text = textCalc.split("-");
        List<String> listText = new ArrayList<>();

        for (int i = 0; i < text.length; i++) { // Does the calculation of a positive and negative(that was changed to positive) and makes it negative
        	
            if(text[i]!=""){
            	
                listText.add(text[i]);
            }
            
            if (i != text.length - 1) {
            	
                listText.add("-");//Example : 9*-9 when it was changed to 9*9 adds a 0 like 0-9*9 so it will come out negative
            }
        }
        

        for (int i = 0; i < listText.size(); i++) {
        	
            if (listText.get(i).contains("+") && listText.get(i).length()>1) {
                
            	String temp = listText.get(i);
            	
                String[] parteDelTexto = temp.split("\\+");                              

                listText.remove(i);

                for (int j = parteDelTexto.length-1; j >= 0; j--) {
                	
                    listText.add(i,parteDelTexto[j]);
                    if (j!=0) {
                    	
                        listText.add(i,"+");
                    }
                }
            }
            
        }
        

        double total;
        

        if (listText.get(0).contains("*") || listText.get(0).contains("/")) {
        	
            total = divisionAndMultiplication(listText.get(0));
        }
        else {
        	
            if (textCalc.charAt(0)=='+') { //-9*-9 --> problem was "","+","9*9" so it removes the empty and puts 0 --> "0","+","9*9"
            	
                listText.add(0,"0");
                listText.remove(1);
            }
            
            total = Double.parseDouble(listText.get(0));
        }
        

        for (int i = 2; i < listText.size(); i+=2) {
        	
            if (listText.get(i-1) == "-") { // Following PEMDAS it makes sure to do multiplication or division before subtraction
            	
                total = total - divisionAndMultiplication(listText.get(i));
            }
            
            else if (listText.get(i-1) == "+") { // Following PEMDAS it makes sure to do multiplication or division before addition
            	
                total = total + divisionAndMultiplication(listText.get(i));
            }               
        }
        return total;
    }
    
    private double divisionAndMultiplication(String textCalc)
    {
        String[] text = textCalc.split("\\*");
        List<String> listText = new ArrayList<>();

        for (int i = 0; i < text.length; i++) { // Puts the equation into an array like 2*3 to [2,*,3]
        	
            listText.add(text[i]);
            
            if (i != text.length - 1) {
            	
                listText.add("*");
            }
        }

        for (int i = 0; i < listText.size(); i++) { // Puts the equation into an array like 2/3 to [2,/,3]
        	
            if (listText.get(i).contains("/") && listText.get(i).length() > 1) {
            	
                String[] parteDelTexto = listText.get(i).split("\\/");

                listText.remove(i);

                for (int j = parteDelTexto.length - 1; j >= 0; j--) {
                    listText.add(i, parteDelTexto[j]);
                    if (j != 0) {
                        listText.add(i, "/");
                    }
                }
            }

        }

        double total = Double.parseDouble(listText.get(0)); // Saves the first value to do the calculation
        
        for (int i = 2; i < listText.size(); i += 2) {
        	
            if (listText.get(i-1) == "/") {
            	
                total = total / Double.parseDouble(listText.get(i));
                if(NumNeg==1) { // If it was saved that this was supposed to be negative it changes to negative
                    total = total*-1;
                    NumNeg = 0;
                }
            }
            else if (listText.get(i-1) == "*" ) {
            	
                total = total * Double.parseDouble(listText.get(i));
                if(NumNeg==1) { // If it was saved that this was supposed to be negative it changes to negative
                    total = total*-1;
                    NumNeg = 0;
                }
            }

        }

        return total;
    }
}
