package com.steven.payslip.payslip.model;

import static org.junit.Assert.*;

import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class DatePeriodTest {
	
private DatePeriod datePeriod;
private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

	@Before
	public void setUp() throws Exception {
		datePeriod = new DatePeriod("01 March	– 31 March");
	}

	@Test
	public void testPayslipOutput() {
		String payPeriod = datePeriod.payslipOutput(formatter);
		assertEquals("01 March 2019-31 March 2019", payPeriod);
	}

}
