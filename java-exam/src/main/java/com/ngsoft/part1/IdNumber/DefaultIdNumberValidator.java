package com.ngsoft.part1.IdNumber;



/**
 * Validating if a string is a proper id number is a requirement of a lot of
 * controller level form posts. If you do not know how it works - a simple google search will suffice
 * Please don't try to copy code you find online though... If you can find it - so can I
 * Implement this properly and the IdNumberTest will pass.
 * Try to make youre implementation simple and readable.
 */
public class DefaultIdNumberValidator implements IdNumberValidator {
	
    @Override
    public boolean isValid(String idNumber) {
    	
    	if (idNumber==null) {
			throw new IllegalArgumentException();
		}
   	
    	if (!idNumber.matches("[0-9]{1,9}")) {
			return false;
		}
    	
    	return checkForLuhnAlgorithm(idNumber);
    }

    
	private boolean checkForLuhnAlgorithm(String idNumber) {
		
    	String[] numbers=new StringBuilder(idNumber).reverse().toString().split("");   	
    	Integer sum = Integer.parseInt(numbers[0]);
    	
    	for (int i = 1; i < numbers.length; i++) {
    		
			if (i % 2 != 0) {
				int res=Integer.parseInt(numbers[i]) * 2;
				sum +=res>9 ? res-9 : res;
				
			}else {
				sum +=Integer.parseInt(numbers[i]);
			}
		}
    	
    	return sum % 2==0 ? true :false;

	}
}
