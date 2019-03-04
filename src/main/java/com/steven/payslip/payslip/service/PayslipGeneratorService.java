package com.steven.payslip.payslip.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.steven.payslip.payslip.model.Employee;
import com.steven.payslip.payslip.model.Payslip;
import com.steven.payslip.payslip.processor.CsvProcessor;
import com.steven.payslip.payslip.processor.EmployeeProcessor;
import com.steven.payslip.payslip.processor.PaySlipProcessor;

@Service
public class PayslipGeneratorService {

	@Value("${inputFile}")
	private String inputFile;

	@Value("${outputFile}")
	private String outputFile;

	@Autowired
	private PaySlipProcessor paySlipProcessor;

	@Autowired
	private EmployeeProcessor employeeProcessor;

	public void generatePayslip() {
		List<Employee> employees = getEmployees();
		List<Payslip> payslips = getPayslips(employees);
		CsvProcessor.export(outputFile, payslips);
	}

	private List<Payslip> getPayslips(List<Employee> employees) {
		List<Payslip> payslip = new ArrayList<>();
		for (Employee employee : employees) {
			payslip.add(paySlipProcessor.parsePayslip(employee));
		}
		return payslip;
	}

	private List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		List<CSVRecord> csvRecords = CsvProcessor.parse(getFilePath(inputFile));
		if (!CollectionUtils.isEmpty(csvRecords)) {
			employees = getEmployeessFromCsvRecords(csvRecords);
		}
		return employees;
	}

	private List<Employee> getEmployeessFromCsvRecords(List<CSVRecord> csvRecords) {
		List<Employee> employees = new ArrayList<>();
		for (CSVRecord csvRecord : csvRecords) {
			employees.add(employeeProcessor.parseEmployee(csvRecord));
		}
		return employees;
	}

	private String getFilePath(String filePath) {
		URL fileURL = getClass().getResource(filePath);
		return fileURL.getPath();
	}

}
