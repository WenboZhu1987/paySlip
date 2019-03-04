package com.steven.payslip.payslip.calculator;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.steven.payslip.payslip.model.TaxRate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxCalculatorTest {

	private BigDecimal annualSalary = new BigDecimal(60050);

	@Autowired
	private TaxCalculator taxCalculator;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getRates() throws Exception {
		List<TaxRate> taxRateList = taxCalculator.getRates();
		Assert.assertEquals(taxRateList.size(), 5);
	}

	@Test
	public void calculateGrossIncome() {
		BigDecimal grossIncome = taxCalculator.calculateGrossIncome(annualSalary);
		Assert.assertEquals(grossIncome, new BigDecimal(5004));
	}

	@Test
	public void calculateIncomeTax() {
		BigDecimal incomeTax = taxCalculator.calculateIncomeTax(annualSalary);
		Assert.assertEquals(incomeTax, new BigDecimal(922));
	}

	@Test
	public void calculateNetIncome() {
		BigDecimal netIncome = taxCalculator.calculateNetIncome(annualSalary);
		Assert.assertEquals(netIncome, new BigDecimal(4082));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateGrossIncomeWithNegAnnualSalary() {
		 taxCalculator.calculateGrossIncome(new BigDecimal(-100000));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateIncomeTaxWithNegAnnualSalary() {
		taxCalculator.calculateIncomeTax(new BigDecimal(-100000));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateNetIncomeWithNegAnnualSalary() {
		taxCalculator.calculateNetIncome(new BigDecimal(-100000));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateGrossIncomeWithNullAnnualSalary() {
		 taxCalculator.calculateGrossIncome(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateIncomeTaxWithNullAnnualSalary() {
		taxCalculator.calculateIncomeTax(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateNetIncomeWithNullAnnualSalary() {
		taxCalculator.calculateNetIncome(null);
	}

}
