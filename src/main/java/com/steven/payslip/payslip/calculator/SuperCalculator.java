package com.steven.payslip.payslip.calculator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.steven.payslip.payslip.validator.NonNegativeValidator;
import com.steven.payslip.payslip.validator.SuperValidator;

@Component
public class SuperCalculator {
	
	@Autowired
	private NonNegativeValidator nonNegativeValidator;
	
	@Autowired
	private SuperValidator superValidator;
	
	
	public int calculate(BigDecimal grossIncome, BigDecimal superRate) {
		nonNegativeValidator.validate(grossIncome);
		superValidator.validate(superRate);
		return grossIncome.multiply(superRate).setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger().intValue();
	}
	
}
