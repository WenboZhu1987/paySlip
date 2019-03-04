package com.steven.payslip.payslip.processor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.steven.payslip.payslip.model.Payslip;

public class CsvProcessorTest {
	
	private Payslip payslip;

	@Before
	public void setUp() throws Exception {
		payslip = new Payslip();
		payslip.setName("David Rudd");
		payslip.setPayPeriod("01 March	- 31 March");
		payslip.setGrossIncome(5004);
		payslip.setIncomeTax(922);
		payslip.setNetIncome(4082);
		payslip.setSuperIncome(450);
	}

	@Test
	public void testParse() {
		URL fileURL = getClass().getResource("/testInput.csv");
	    File csvFile = new File(fileURL.getFile());
	    List<CSVRecord> csvRecordList = CsvProcessor.parse(csvFile.getAbsolutePath());
	    Assert.assertEquals(csvRecordList.size(), 2);
	}
	
	@Test(expected = RuntimeException.class)
	public void testParseWithWrongFilePath() {
		URL fileURL = getClass().getResource("input/testInput.csv");
	    File csvFile = new File(fileURL.getFile());
	    CsvProcessor.parse(csvFile.getAbsolutePath());
	}

	@Test
	public void testExport() throws IOException {
	    String fileName = "testOutPut.csv";
	    CsvProcessor.export(fileName, Arrays.asList(payslip));
	}
	

	


}
