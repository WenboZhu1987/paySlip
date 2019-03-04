package com.steven.payslip.payslip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.steven.payslip.payslip.service.PayslipGeneratorService;

@SpringBootApplication
public class PayslipApplication implements CommandLineRunner{
	
	@Autowired
	private PayslipGeneratorService payslipGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(PayslipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		payslipGeneratorService.generatePayslip();
	}

}
