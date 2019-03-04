package com.steven.payslip.payslip.processor;

import java.math.BigDecimal;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.steven.payslip.payslip.model.DatePeriod;
import com.steven.payslip.payslip.model.Employee;
import com.steven.payslip.payslip.validator.SuperValidator;

@Component
public class EmployeeProcessor {
	
	@Autowired
	private SuperValidator superValidator;
	

	public Employee parseEmployee(CSVRecord csvRecord) {

		Employee employee = new Employee();
		try {
			employee.setFirstName(csvRecord.get(0));
			employee.setLastName(csvRecord.get(1));
			employee.setAnnualSalary(new BigDecimal(csvRecord.get(2)));
			employee.setSuperRate(getSuperRate(csvRecord.get(3)));
			employee.setPayPeriod(new DatePeriod(csvRecord.get(4)));
		} catch (Exception e) {
			throw new RuntimeException("can't parse CSV  " + csvRecord.toString() + " to employee.", e);
		}
		return employee;
	}

	public BigDecimal getSuperRate(String superRate) {
		BigDecimal divideRate = new BigDecimal(superRate.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
		superValidator.validate(divideRate);
		return divideRate;
	}

}
