package com.perficient.dataservice.utils;

import java.util.ArrayList;
import java.util.List;

import com.perficient.dataservice.model.Employee;

public class EmpMgr {
	
	private static EmpMgr sharedInstance = null;
	
	private List<Employee> employees;

	private EmpMgr() {
		employees = new ArrayList<Employee>();
	}
	
	public static EmpMgr sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new EmpMgr();
		}
		return sharedInstance;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
