package com.steven.payslip.payslip.processor;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.steven.payslip.payslip.calculator.SuperCalculator;
import com.steven.payslip.payslip.calculator.TaxCalculator;
import com.steven.payslip.payslip.model.DatePeriod;
import com.steven.payslip.payslip.model.Employee;
import com.steven.payslip.payslip.model.Payslip;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaySlipProcessorTest {

	@InjectMocks
	private PaySlipProcessor paySlipProcessor;

	@Mock
	private TaxCalculator taxCalculator;
	@Mock
	private SuperCalculator superCalculator;
	
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		paySlipProcessor = new PaySlipProcessor();
		MockitoAnnotations.initMocks(this);
		employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Rudd");
		employee.setAnnualSalary(new BigDecimal(60000));
		employee.setPayPeriod(new DatePeriod("01 March – 31 March") );
	}

	@Test
	public void testParsePayslip() {
		Mockito.when(taxCalculator.calculateGrossIncome(Mockito.any())).thenReturn(new BigDecimal(10000));
		Mockito.when(taxCalculator.calculateIncomeTax(Mockito.any())).thenReturn(new BigDecimal(1000));
		Mockito.when(taxCalculator.calculateNetIncome(Mockito.any())).thenReturn(new BigDecimal(800));
		Mockito.when(superCalculator.calculate(Mockito.any(), Mockito.any())).thenReturn(1000);
		Payslip payslip = paySlipProcessor.parsePayslip(employee);
		Assert.assertEquals(payslip.getName(), employee.getName());
		Assert.assertEquals(payslip.getGrossIncome(), 10000);
		Assert.assertEquals(payslip.getIncomeTax(), 1000);
		Assert.assertEquals(payslip.getNetIncome(), 800);
		Assert.assertEquals(payslip.getSuperIncome(), 1000);
	}

}
