package com.web.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MathWebService1 {
	
	public int NumNeg = 0;
	public int Sqrt = 0;

	public String solve(String expression, int precision) throws Exception {
		try{
			
			expression = solveParentheses(resolveSquareRoot(expression)); // Need to solve the sqrt before it enters into the next While or it will loop forever.

			String total = String.valueOf(addAndSubtract(negativeFirstNumber(ruleOfSigns(expression))));
			
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
	
	
	public String resolveSquareRoot(String expression) { // solves square root.
    	
    	while (expression.contains("sqrt")){
    		
    		int start = 0;
    		int openingParentesis = 0;
            int closingParentheses = 0;
            Double squareRoot = 0.0;
            
            
            for (int i = 0; i < expression.length(); i++) {
            	
                if (expression.charAt(i) == 's') { // Saves the position of where the sqrt starts.
                	
                    start = i;
                    Sqrt = 1;
                }
                if (expression.charAt(i) == '(') { // saves the position of the first parentheses.
                	
                	openingParentesis = i;
                }
                if (expression.charAt(i) == ')') { // saves the position of the second parentheses.
                	
                    closingParentheses = i;
                    
                    if(Sqrt ==1) { //if the sqrt has an expression inside it it will resolve it before applying the sqrt.
	                    
                    	String currentSqrt = expression.substring(openingParentesis+1, closingParentheses);
	                    
	                    if(currentSqrt.contains("+")||currentSqrt.contains("-")||currentSqrt.contains("*")||currentSqrt.contains("/")) {
	                    	
	                    	squareRoot = Double.parseDouble(solveParentheses(currentSqrt));
	                    	
	                    }else {
	                    	
	                    	squareRoot = Double.parseDouble(currentSqrt);
	                    	
	                    }
	                    
	                    String replacement = String.valueOf(Math.sqrt(squareRoot));
	                    String toBeReplaced = expression.substring(start,closingParentheses+1);
	                    expression = expression.replace(toBeReplaced, replacement);
	                    
	                    Sqrt = 0;
	                    
                    break;
                    }
                }
            }
    	}
    	
    	return expression;
    	
    }
		 
	
	public String solveParentheses(String expression) {

		
        while(expression.contains("(") && expression.contains(")")) { // Enters here if it detects a parentheses.
        	
            int startParentheses = 0;
            int endParentheses = 0;
            
            for (int i = 0; i < expression.length(); i++) {
            	
                if (expression.charAt(i) == '(') { // Saves the position of the start of the parentheses.
                	
                    startParentheses = i;
                }
                
                if (expression.charAt(i) == ')') { // Saves the position of the end of the parentheses.

                    endParentheses = i;
                    
                    String currentParentheses = expression.substring(startParentheses + 1, endParentheses); // Saves the current parentheses that is being calculated
                    
                    String replacement = String.valueOf(addAndSubtract(negativeFirstNumber(currentParentheses))); // First we make sure that it has a 0- in case of a negative first number
                    
                    String toBeReplaced = expression.substring(startParentheses, endParentheses+1);
                    
                    expression = expression.replace(toBeReplaced, replacement);
                    
                    break;
                    
                }
            }
        }

        return expression;
        
    }


	private String negativeFirstNumber(String expression) { // adds a 0 in front of the - so it wont crash because of a negative number being first 
	    
	    if (expression.charAt(0) == '-') {
	    	
	    	expression = '0' + expression;
	    }
	
	    return expression;
	}
	

	public String ruleOfSigns(String expression) {
		
		StringBuilder signsSolver = new StringBuilder(expression);
		
		for (int i = 1; i < expression.length(); i++) { // If for example there is 2--2 itll change to 2+2 or 2-+2 changes to 2-2
        	
            if (expression.charAt(i) == '-' && (expression.charAt(i-1) == '-' || expression.charAt(i-1) == '+')) {
            	
                if (expression.charAt(i-1) == '-') { // 2--2 to 2+2
                	               
                    signsSolver.replace(i, i+1, "+");
                    expression = signsSolver.toString();
                    expression = expression.replaceFirst("-", "");
                    
                }
                
                else { // 2+-2 to 2-2
                	
                    signsSolver.replace(i, i+1, "-");
                    expression = signsSolver.toString();
                    expression = expression.replaceFirst("\\+", "");
                    
                }

            }else if(expression.charAt(i) == '+' && (expression.charAt(i-1) == '-' || expression.charAt(i-1) == '+')) {
            	
                if (expression.charAt(i-1) == '-') { // 2-+2 to 2-2

                    signsSolver.replace(i, i+1, "-");
                    expression = signsSolver.toString();
                    expression = expression.replaceFirst("-", "");
                    
                }
                
                else{ // 2++2 to 2+2
                	
                    signsSolver.replace(i, i+1, "+");
                    expression = signsSolver.toString();
                    expression = expression.replaceFirst("\\+", "");
                    
                }
            }
        }
		
		return expression;
	}
    

    public double addAndSubtract(String expression) {
    	  	
        for (int i = 1; i < expression.length(); i++) { // This is to make a number negative in case of a multiplication/division with a negative number
        	
            if(expression.charAt(i)=='-' && (expression.charAt(i-1)=='*' || expression.charAt(i-1)=='/')) { 
            	
            	String replacement = expression.substring(i-1, expression.length()); // Because java doesnt have a remove function I improvised a somewhat similar way.
            	
                replacement = replacement.replaceFirst("-", "");
                
                String toBeReplaced = expression.substring(i-1, expression.length());
                
                expression = expression.replace(toBeReplaced, replacement);
                
                NumNeg += 1;                          
                
            }
            
        }
        

        String[] splitExpressionWithSubtraction = expression.split("-"); // Initializes the variable using subtraction
        
        List<String> groupUpNumbers = new ArrayList<>(); // Groups up numbers to be used in the equations
        

        for (int i = 0; i < splitExpressionWithSubtraction.length; i++) { // splits up the expression like [10,-,10] so its easier to be calculated
        	
            if(splitExpressionWithSubtraction[i]!=""){
            	
                groupUpNumbers.add(splitExpressionWithSubtraction[i]);
                
            }
            
            if (i != splitExpressionWithSubtraction.length - 1) {
            	
                groupUpNumbers.add("-"); 
            
            }
        }
        

        for (int i = 0; i < groupUpNumbers.size(); i++) {
        	
            if (groupUpNumbers.get(i).contains("+") && groupUpNumbers.get(i).length()>1) {
            	
                String[] splitExpressionWithAddition = groupUpNumbers.get(i).split("\\+"); // splits the expression up like [10,+,10] so its easier to be calculated
 
                groupUpNumbers.remove(i);

                for (int j = splitExpressionWithAddition.length-1; j >= 0; j--) {
                	
                    groupUpNumbers.add(i,splitExpressionWithAddition[j]);
                    if (j!=0) {
                    	
                        groupUpNumbers.add(i,"+");
                    }
                }
            }
            
        }
        

        double total;
        

        if (groupUpNumbers.get(0).contains("*") || groupUpNumbers.get(0).contains("/")) {
        	
            total = divisionAndMultiplication(groupUpNumbers.get(0)); // we save the first number of the multiplication or division for later use
            
        }
        
        else {
        	
            if (expression.charAt(0)=='+') { //-9*-9 --> problem was "","+","9*9" so it removes the empty and puts 0 --> "0","+","9*9"

                groupUpNumbers.remove(0);
                
            }
            
            total = Double.parseDouble(groupUpNumbers.get(0));
            
        }
        

        for (int i = 2; i < groupUpNumbers.size(); i+=2) {
        	
            if (groupUpNumbers.get(i-1) == "-") { // Following PEMDAS it makes sure to do multiplication or division before subtraction
            	
                total = total - divisionAndMultiplication(groupUpNumbers.get(i));
                
            }
            
            else if (groupUpNumbers.get(i-1) == "+") { // Following PEMDAS it makes sure to do multiplication or division before addition
            	
                total = total + divisionAndMultiplication(groupUpNumbers.get(i));
                
            }  
            
        }
        
        return total;
    }
    
    private double divisionAndMultiplication(String expression) {
    	
        String[] splitExpression = expression.split("\\*"); // Initializes the variable using multiplication
        
        List<String> groupUpNumbers = new ArrayList<>(); // this is used to help the multiplication such as grouping up the strings to be converted to double to be used, example 
        										   		 // "10*20" will be split up like [10,*,20] this way the ide knows what number is being used.

        for (int i = 0; i < splitExpression.length; i++) { // Puts the equation into an array like 2*3 to [2,*,3]
        	
            groupUpNumbers.add(splitExpression[i]);
            
            if (i != splitExpression.length - 1) {
            	
                groupUpNumbers.add("*");
            }
        }

        for (int i = 0; i < groupUpNumbers.size(); i++) { // Puts the equation into an array like 2/3 to [2,/,3]
        	
            if (groupUpNumbers.get(i).contains("/") && groupUpNumbers.get(i).length() > 1) {
            	
                String[] splitExpressionWithDivision = groupUpNumbers.get(i).split("\\/");

                groupUpNumbers.remove(i);

                for (int j = splitExpressionWithDivision.length - 1; j >= 0; j--) {
                	
                    groupUpNumbers.add(i, splitExpressionWithDivision[j]);
                    
                    if (j != 0) {
                    	
                        groupUpNumbers.add(i, "/");
                        
                    }
                    
                }
                
            }

        }

        double total = Double.parseDouble(groupUpNumbers.get(0)); // Saves the first value to do the calculation
        
        for (int i = 2; i < groupUpNumbers.size(); i += 2) {
        	
            if (groupUpNumbers.get(i-1) == "/") {
            	
                total = total / Double.parseDouble(groupUpNumbers.get(i));
                
                if(NumNeg==1) { // If it was saved that this was supposed to be negative it changes to negative
                	
                    total = total*-1;
                    NumNeg = 0;
                    
                }
                
            } else if (groupUpNumbers.get(i-1) == "*" ) {
            	
                total = total * Double.parseDouble(groupUpNumbers.get(i));
                
                if(NumNeg==1) { // If it was saved that this was supposed to be negative it changes to negative
                	
                    total = total*-1;
                    NumNeg = 0;
                    
                }
                
            }

        }

        return total;
    }

}

 