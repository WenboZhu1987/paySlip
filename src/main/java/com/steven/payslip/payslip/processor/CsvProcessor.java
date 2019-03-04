package com.steven.payslip.payslip.processor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.steven.payslip.payslip.model.Payslip;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvProcessor {

	@SuppressWarnings("resource")
	public static List<CSVRecord> parse(String filePath) {
		List<CSVRecord> csvRecords;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			csvRecords = new CSVParser(reader, CSVFormat.DEFAULT).getRecords();
		} catch (IOException e) {
			throw new RuntimeException("Error when reading Csv File!", e);
		}
		return csvRecords;
	}

	@SuppressWarnings("rawtypes")
	public static void export(String fileName, List<Payslip> payslips) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		File file = null;
		try {
			file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWriter = new FileWriter(file.getAbsolutePath());
			csvFilePrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			for (Payslip payslip : payslips) {
				List<Comparable> csvDataRecord = new ArrayList<Comparable>();
				csvDataRecord.add(payslip.getName());
				csvDataRecord.add(payslip.getPayPeriod());
				csvDataRecord.add(payslip.getGrossIncome());
				csvDataRecord.add(payslip.getIncomeTax());
				csvDataRecord.add(payslip.getNetIncome());
				csvDataRecord.add(payslip.getSuperIncome());
				csvFilePrinter.printRecord(csvDataRecord);
			}
			log.info("Successfully generated CSV file in path: " + file.getAbsolutePath());
		} catch (Exception e) {
			throw new RuntimeException("Error when writting Csv File!" + e);
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (Exception e) {
				throw new RuntimeException("Error when closing fileWriter!" + e);
			}
		}
	}
	

}
