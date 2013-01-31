package com.perficient.dataservice.utils;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.perficient.dataservice.model.Employee;

public class FileUtilsTest {

	@Test
	public void testObjectSerialization() throws Exception {
		Employee emp = new Employee();
		emp.setEmpNum("HE10086");
		emp.setEmail("hello@123.com");
		String objectFilePath = "emp.obj";
		FileUtils.writeObjectToFile(objectFilePath, emp);
		Employee emp1 = (Employee)FileUtils.readObjectFromFile(objectFilePath);
		assertEquals("Employee number should be the same", emp.getEmpNum(), emp1.getEmpNum());
		assertEquals("Employee email should be the same", emp.getEmail(), emp1.getEmail());
		assertNull(emp1.getFirstName());
		assertNull(emp1.getLastName());
		new File(objectFilePath).delete();
	}
	
	@Test
	public void testListSerialization() throws Exception {
		Employee emp = new Employee();
		emp.setEmpNum("HE10087");
		emp.setEmail("hello@123.com");
		Employee emp1 = new Employee();
		emp1.setEmpNum("HE10088");
		emp1.setEmail("hello1@123.com");
		String objectFilePath = "emps.obj";
		Employee[] emps = new Employee[]{emp, emp1};
		FileUtils.writeObjectToFile(objectFilePath, emps);
		Employee[] emps1 = (Employee[])FileUtils.readObjectFromFile(objectFilePath);
		assertEquals("Employees count should be the same", emps.length, emps1.length);
		new File(objectFilePath).delete();
	}

}
