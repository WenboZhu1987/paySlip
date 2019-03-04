package com.steven.payslip.payslip.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class DatePeriod {
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	
	public DatePeriod(String datePeriod) {
		//parse String to get startDate and endDate
		String[] date = datePeriod.split("–");
		String startDate = date[0].trim()+ " " + LocalDate.now().getYear() ;
		String endDate = date[1].trim()+ " " + LocalDate.now().getYear();
		this.startDate=LocalDate.parse(startDate,formatter);
		this.endDate = LocalDate.parse(endDate,formatter);
	}
	
	public String payslipOutput(DateTimeFormatter formatter) {
		return String.format("%s-%s", startDate.format(formatter), endDate.format(formatter));
	}

}
