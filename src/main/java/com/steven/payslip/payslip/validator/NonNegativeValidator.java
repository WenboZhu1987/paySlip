package com.steven.payslip.payslip.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class NonNegativeValidator {
	
	public void validate(BigDecimal value) {
		if (value == null) {
			throw new IllegalArgumentException("Data cannot be null");
		}
		if(value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Data should be positive");
		}	
	}
	

}
