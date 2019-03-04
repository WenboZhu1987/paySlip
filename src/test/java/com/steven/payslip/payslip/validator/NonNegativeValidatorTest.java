package com.steven.payslip.payslip.validator;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class NonNegativeValidatorTest {
	
	private NonNegativeValidator nonNegativeValidator;

	@Before
	public void setUp() throws Exception {
		nonNegativeValidator = new NonNegativeValidator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithZero() {
		nonNegativeValidator.validate(BigDecimal.ZERO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithNeg() {
		nonNegativeValidator.validate(new BigDecimal(-10));
	}

}
