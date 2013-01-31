package com.perficient.dataservice.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

public class EmployeeTest {

	@Test
	public void testJSON_SingleInstance() {
		Employee emp = new Employee();
		emp.setEmpNum("HE10087");
		emp.setEmail("hello@123.com");
		emp.setFirstName("");
		String empJSON = new Gson().toJson(emp);
		assertEquals("{\"empNum\":\"HE10087\",\"firstName\":\"\",\"email\":\"hello@123.com\"}", empJSON);
	}
	
	@Test
	public void testJSON_Array() {
		Employee emp = new Employee();
		emp.setEmpNum("HE10087");
		emp.setEmail("hello@123.com");
		emp.setFirstName("");
		Employee emp1 = new Employee();
		emp1.setEmpNum("HE10088");
		emp1.setEmail("hello1@123.com");
		emp1.setFirstName("Jack");
		Employee[] emps = new Employee[] {emp, emp1};
		String empsJSON = new Gson().toJson(emps);
		assertEquals("[{\"empNum\":\"HE10087\",\"firstName\":\"\",\"email\":\"hello@123.com\"}," +
				"{\"empNum\":\"HE10088\",\"firstName\":\"Jack\",\"email\":\"hello1@123.com\"}]", empsJSON);
	}
	
	@Test
	public void testJSONToEmployee() {
		String empJSON = "{\"empNum\":\"HE10087\",\"firstName\":\"\",\"email\":\"hello@123.com\"}";
		Employee emp = new Gson().fromJson(empJSON, Employee.class);
		assertEquals("HE10087", emp.getEmpNum());
		assertEquals("", emp.getFirstName());
		assertEquals("hello@123.com", emp.getEmail());
	}
	
	@Test
	public void testJSONToEmpArray() {
		String empsJSON = "[{\"empNum\":\"HE10087\",\"firstName\":\"\",\"email\":\"hello@123.com\"}," +
				"{\"empNum\":\"HE10088\",\"firstName\":\"Jack\",\"email\":\"hello1@123.com\"}]";
		Employee[] emps = new Gson().fromJson(empsJSON, Employee[].class);
		assertEquals("HE10087", emps[0].getEmpNum());
		assertEquals("", emps[0].getFirstName());
		assertEquals("hello@123.com", emps[0].getEmail());
		assertEquals(2, emps.length);
	}

}
