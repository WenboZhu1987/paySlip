package com.steven.payslip.payslip.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class SuperValidator {
	
	public void validate(BigDecimal value) {
		if (value == null) {
			throw new IllegalArgumentException("Super cannot be null");
		}
		if(value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal(0.5)) > 0 ) {
			throw new IllegalArgumentException("Super should be in range from 0% to 50 %");
		}	
	}

}
