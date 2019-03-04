package com.steven.payslip.payslip;

import java.io.File;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayslipApplicationTests {

	@Autowired
	PayslipApplication payslipApplication;

	@Test
	public void testRun() throws Exception {
		payslipApplication.run();
		File outputFile = new File("output.csv");
		String csvRecords = new String(Files.readAllBytes(outputFile.toPath()), "UTF8");
		Assert.assertEquals(csvRecords, "David Rudd,01 March\t– 31 March,5004,922,4082,450\r\n"
				+ "Ryan Chen,01 March\t– 31 March,10000,2696,7304,1000\r\n");
	}
}
