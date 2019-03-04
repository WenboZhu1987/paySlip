package com.steven.payslip.payslip.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.steven.payslip.payslip.model.TaxRate;
import com.steven.payslip.payslip.validator.NonNegativeValidator;

@Component
@ConfigurationProperties(prefix = "taxrate")
public class TaxCalculator {

	private List<TaxRate> rates = new ArrayList<>();

	public List<TaxRate> getRates() {
		return rates;
	}

	public void setRates(List<TaxRate> rates) {
		this.rates = rates;
	}

	@Autowired
	private NonNegativeValidator validator;

	public BigDecimal calculateIncomeTax(BigDecimal annualSalary) {

		validator.validate(annualSalary);
		List<BigDecimal> taxIncomes = new ArrayList<BigDecimal>();

		getRates().stream().forEach(taxRate -> {
			BigDecimal incomeTax = BigDecimal.ZERO;
			if (taxRate.findRate(annualSalary.intValue())) {
				incomeTax = getIncomeTax(annualSalary, taxRate);
			} 
			taxIncomes.add(incomeTax);
		});

		BigDecimal totalIncome = taxIncomes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalIncome.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP).setScale(0,
				BigDecimal.ROUND_HALF_UP);
	}

	private BigDecimal getIncomeTax(BigDecimal annualSalary, TaxRate taxRate) {
		validator.validate(annualSalary);
		BigDecimal taxableIncome;
		taxableIncome = annualSalary.subtract(new BigDecimal(taxRate.getMin())).add(BigDecimal.ONE);
		return taxableIncome.multiply(taxRate.getPercentage()).add(new BigDecimal(taxRate.getBaseTaxAmount()));
	}

	public BigDecimal calculateGrossIncome(BigDecimal annualSalary) {
		validator.validate(annualSalary);
		return annualSalary.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP).setScale(0,
				BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal calculateNetIncome(BigDecimal annualSalary) {
		validator.validate(annualSalary);
		return calculateGrossIncome(annualSalary).subtract(calculateIncomeTax(annualSalary)).setScale(0,
				BigDecimal.ROUND_HALF_UP);
	}

}
