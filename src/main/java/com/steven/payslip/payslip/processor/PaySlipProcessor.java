package com.steven.payslip.payslip.processor;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.steven.payslip.payslip.calculator.SuperCalculator;
import com.steven.payslip.payslip.calculator.TaxCalculator;
import com.steven.payslip.payslip.model.Employee;
import com.steven.payslip.payslip.model.Payslip;

@Component
public class PaySlipProcessor {

	@Autowired
	private TaxCalculator taxCalculator;

	@Autowired
	private SuperCalculator superCalculator;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");

	public Payslip parsePayslip(Employee employee) {

		Payslip payslip = new Payslip();
		payslip.setName(employee.getName());
		payslip.setIncomeTax(taxCalculator.calculateIncomeTax(employee.getAnnualSalary()).intValue());
		payslip.setGrossIncome(taxCalculator.calculateGrossIncome(employee.getAnnualSalary()).intValue());
		payslip.setSuperIncome(superCalculator.calculate(taxCalculator.calculateGrossIncome(employee.getAnnualSalary()),
				employee.getSuperRate()));

		payslip.setNetIncome(taxCalculator.calculateNetIncome(employee.getAnnualSalary()).intValue());
		payslip.setPayPeriod(employee.getPayPeriod().payslipOutput(formatter));

		return payslip;
	}

}
