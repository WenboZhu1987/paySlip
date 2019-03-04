package com.steven.payslip.payslip.model;

import lombok.Data;

@Data
public class Payslip {
	
	private String name;
	private String payPeriod;
	private int grossIncome;
	private int incomeTax;
	private int netIncome;
	private int superIncome;
	
}
