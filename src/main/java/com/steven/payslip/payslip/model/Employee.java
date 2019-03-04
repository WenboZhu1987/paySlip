package com.steven.payslip.payslip.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Employee {

	private String firstName;
	private String lastName;
	private BigDecimal annualSalary;
	private BigDecimal superRate;
	private DatePeriod payPeriod;

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

}
