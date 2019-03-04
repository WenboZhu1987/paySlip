package com.steven.payslip.payslip.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.steven.payslip.payslip.model.Employee;
import com.steven.payslip.payslip.model.Payslip;
import com.steven.payslip.payslip.processor.EmployeeProcessor;
import com.steven.payslip.payslip.processor.PaySlipProcessor;

public class PayslipGeneratorServiceTest {
	
	@Mock
	private PaySlipProcessor paySlipProcessor;
	
	@Mock
	private EmployeeProcessor employeeProcessor;
	
	@InjectMocks
	private PayslipGeneratorService subject;

	private String inputCSVFile = "/testInput.csv";

	private String outputCSVFile = "out.csv";
	
	private Employee employee;
	
	private Payslip payslip;

	@Before
	public void setUp() throws Exception {
		subject = new PayslipGeneratorService();
		MockitoAnnotations.initMocks(this);
		employee =new Employee();
		payslip = new Payslip();
	}

	@Test
	public void testGeneratePayslip() {
		ReflectionTestUtils.setField(subject, "inputFile", inputCSVFile);
		ReflectionTestUtils.setField(subject, "outputFile", outputCSVFile);
		Mockito.when(employeeProcessor.parseEmployee(Mockito.any())).thenReturn(employee);
		Mockito.when(paySlipProcessor.parsePayslip(Mockito.any())).thenReturn(payslip);
		subject.generatePayslip();
	}

}
