package com.steven.payslip.payslip.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Rudd");
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("David Rudd", employee.getName());
		
	}

}
