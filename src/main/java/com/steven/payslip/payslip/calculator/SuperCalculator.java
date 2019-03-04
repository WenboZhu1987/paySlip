package com.steven.payslip.payslip.calculator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.steven.payslip.payslip.validator.NonNegativeValidator;

@Component
public class SuperCalculator {
	
	@Autowired
	private NonNegativeValidator validator;
	
	private void validate(BigDecimal taxableIncome, BigDecimal superRate) {
		validator.validate(taxableIncome);
		validator.validate(superRate);
	}
	
	public int calculate(BigDecimal taxableIncome, BigDecimal superRate) {
		validate(taxableIncome, superRate);
		return taxableIncome.multiply(superRate).setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger().intValue();
	}
	
}
