package com.steven.payslip.payslip.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TaxRate {

	private int min;
	private int max;
	private int baseTaxAmount;
	private BigDecimal percentage;

	public boolean findRate(int income) {
		return income >= min && income <= max;
	}

}
