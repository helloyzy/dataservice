package com.perficient.dataservice.utils;

import java.util.List;

import com.perficient.dataservice.model.Employee;

public class EmpImportResult {
	
	public enum StatusCode {success, errorNoEmpInfoFromFile, errorBadFormEmpInfo, errorEmpInfoIncomplete, errorFileNotExist, errorNotSpecified}
	
	private StatusCode statusCode;
	
	private List<Employee> employees;
	
	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
	
}
