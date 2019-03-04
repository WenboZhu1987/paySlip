package com.steven.payslip.payslip.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.steven.payslip.payslip.model.DatePeriod;
import com.steven.payslip.payslip.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeProcessorTest {
	
	@Autowired
	private EmployeeProcessor employeeProcessor;
	private CSVRecord csvRecord;

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("resource")
	@Test
	public void testParseEmployee() throws IOException {
		String firstName = "David";
	    String lastName = "Rudd";
	    String anuualSalary = "60000";
	    String superRate = "9%";
	    String paymentPeriod = "01 March\t– 31 March";
	    StringJoiner joiner = new StringJoiner(",");
	    joiner.add(firstName).add(lastName).add(anuualSalary).add(superRate).add(paymentPeriod);
	    String csvLine = joiner.toString();
	    List<CSVRecord> csvRecordList = new CSVParser(new BufferedReader(new StringReader(csvLine)), CSVFormat.DEFAULT).getRecords();
	    csvRecord = csvRecordList.get(0);
	    
	    Employee employee = employeeProcessor.parseEmployee(csvRecord);
	    Assert.assertEquals(employee.getFirstName(), firstName);
	    Assert.assertEquals(employee.getLastName(), lastName);
	    Assert.assertEquals(employee.getAnnualSalary(), new BigDecimal(anuualSalary));
	    Assert.assertEquals(employee.getSuperRate(), new BigDecimal("0.09"));
	    Assert.assertEquals(employee.getPayPeriod().getStartDate(),new DatePeriod(paymentPeriod).getStartDate());
	    Assert.assertEquals(employee.getPayPeriod().getEndDate(),new DatePeriod(paymentPeriod).getEndDate());
	}

	@Test
	public void testGetSuperRate() {
		String superRate = "9%";
		Assert.assertEquals(employeeProcessor.getSuperRate(superRate), new BigDecimal("0.09"));
	}

}
