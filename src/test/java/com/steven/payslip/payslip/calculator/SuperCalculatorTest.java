package com.steven.payslip.payslip.calculator;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperCalculatorTest {
	
	@Autowired
	private SuperCalculator superCalculator;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testCalculate() {
		int result = superCalculator.calculate(new BigDecimal(20000), new BigDecimal(0.095));
		assertEquals(1900, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateWithNegIncome() {
		superCalculator.calculate(new BigDecimal(-20000), new BigDecimal(0.095));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateWithNegSuperRate() {
		superCalculator.calculate(new BigDecimal(20000), new BigDecimal(-0.095));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateWithNullIncome() {
		superCalculator.calculate(null, new BigDecimal(0.095));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateWithNullSuperRate() {
		superCalculator.calculate(new BigDecimal(20000), null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateWithOvertopSuperRate() {
		superCalculator.calculate(new BigDecimal(20000), new BigDecimal(0.6));
	}

}
