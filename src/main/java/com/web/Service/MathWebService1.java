package com.web.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MathWebService1 {
	
	public int NumNeg = 0;
	public int Sqrt = 0;

	public String solve(String expression) throws Exception {
		try{
			
			String total = solveParentheses(expression);
			
			return total;
			
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}

	}
		 
	
	public String solveParentheses(String expression) {
		
		
		if(expression.contains("sqrt")) { // Need to solve the sqrt before it enters into the next While or it will loop forever.
			
			expression = resolveSquareRoot(expression);
		}
		
        while(expression.contains("(") && expression.contains(")")) { // Enters here if it detects a parentheses.
        	
            int startParentheses = 0;
            int endParentheses = 0;
            
            for (int i = 0; i < expression.length(); i++) {
            	
                if (expression.charAt(i) == '(') { // Saves the position of the start of the parentheses.
                	
                    startParentheses = i;
                }
                
                if (expression.charAt(i) == ')') { // Saves the position of the end of the parentheses.

                    endParentheses = i;
                    
                    String currentParentheses = expression.substring(startParentheses + 1, endParentheses);
                    
                    String replacement = calculate(negativeFirstNumber(currentParentheses));
                    
                    String toBeReplaced = expression.substring(startParentheses, endParentheses+1);
                    
                    expression = expression.replace(toBeReplaced, replacement);
                    
                    break;
                    
                }
            }
        }

        return calculate(negativeFirstNumber(ruleOfSigns(expression)));
        
    }


	private String negativeFirstNumber(String expression) { // adds a 0 in front of the - so it wont crash because of a negative number being first 
	    
	    if (expression.charAt(0) == '-') {
	    	
	    	expression = '0' + expression;
	    }
	
	    return expression;
	}
	

	public String ruleOfSigns(String expression) {
		
		StringBuilder texto1 = new StringBuilder(expression);
		
		for (int i = 1; i < expression.length(); i++) { // If for example there is 2--2 itll change to 2+2 or 2-+2 changes to 2-2
        	
            if (expression.charAt(i) == '-' && (expression.charAt(i-1) == '-' || expression.charAt(i-1) == '+')) {
            	
                if (expression.charAt(i-1) == '-') { // 2--2 to 2+2
                	               
                    texto1.replace(i, i+1, "+");
                    expression = texto1.toString();
                    expression = expression.replaceFirst("-", "");
                    
                }
                
                else { // 2+-2 to 2-2
                	
                    texto1.replace(i, i+1, "-");
                    expression = texto1.toString();
                    expression = expression.replaceFirst("\\+", "");
                    
                }

            }else if(expression.charAt(i) == '+' && (expression.charAt(i-1) == '-' || expression.charAt(i-1) == '+')) {
            	
                if (expression.charAt(i-1) == '-') { // 2-+2 to 2-2

                    texto1.replace(i, i+1, "-");
                    expression = texto1.toString();
                    expression = expression.replaceFirst("-", "");
                    
                }
                
                else{ // 2++2 to 2+2
                	
                    texto1.replace(i, i+1, "+");
                    expression = texto1.toString();
                    expression = expression.replaceFirst("\\+", "");
                    
                }
            }
        }
		
		return expression;
	}
	
    
    public String calculate(String text){ // starts the calculation of the expression. Changes the Type from double to String.
     	
     	String respuestaFinal = String.valueOf(addAndSubtract(text));
     	return respuestaFinal;
     
 	}
    
    
    public String resolveSquareRoot(String text) { // solves square root.
    	
    	while (text.contains("sqrt")){
    		int start = 0;
    		int firstParentesis = 0;
            int end = 0;
            Double squareRoot = 0.0;
            
            
            for (int i = 0; i < text.length(); i++) {
            	
                if (text.charAt(i) == 's') { // Saves the position of where the sqrt starts.
                	
                    start = i;
                    Sqrt = 1;
                }
                if (text.charAt(i) == '(') { // saves the position of the first parentheses.
                	
                	firstParentesis = i;
                }
                if (text.charAt(i) == ')') { // saves the position of the second parentheses.
                	
                    end = i;
                    
                    if(Sqrt ==1) { //if the sqrt has an expression inside it it will resolve it before applying the sqrt.
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
    	
    	
        for (int i = 1; i < textCalc.length(); i++) { // This is to make a number negative in case of a multiplication/division with a negative number
        	
            if(textCalc.charAt(i)=='-' && (textCalc.charAt(i-1)=='*' || textCalc.charAt(i-1)=='/')) { 
            	
            	String replace = textCalc.substring(i-1, textCalc.length());
            	
                replace = replace.replace("-", "");
                
                String toBeReplaced = textCalc.substring(i-1, textCalc.length());
                
                textCalc = textCalc.replace(toBeReplaced, replace);
                
                NumNeg += 1;                          
                
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

 