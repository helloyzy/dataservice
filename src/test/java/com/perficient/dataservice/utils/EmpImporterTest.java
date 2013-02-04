package com.perficient.dataservice.utils;

import static org.junit.Assert.*;

import static com.perficient.dataservice.utils.TestUtils.*;

import org.junit.Test;

import com.perficient.dataservice.model.Employee;
import com.perficient.dataservice.utils.EmpImportResult.StatusCode;

public class EmpImporterTest {

	@Test
	public void testEmployeesFromExcel_NoRecords() {
		String filePath = getTestResDir() + "EmptyFile.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorNoEmpInfoFromFile, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel_BadFormat_FewerColumns() {
		String filePath = getTestResDir() + "EmpInfos_BadFormat_FewerColumns.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorBadFormEmpInfo, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel_BadFormat_FieldNameNotMatch() {
		String filePath = getTestResDir() + "EmpInfos_BadFormat_FieldNameNotMatch.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorBadFormEmpInfo, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel_BadFormat_TitleSequenceNotCorrect() {
		String filePath = getTestResDir() + "EmpInfos_BadFormat_TitleSequenceNotCorrect.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorBadFormEmpInfo, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel_EmpInfoIncomplete() {
		String filePath = getTestResDir() + "EmpInfos_EmpInfoIncomplete.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorEmpInfoIncomplete, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel_FileNotExist() {
		String filePath = getTestResDir() + "EmpInfos_FileNotExist.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.errorFileNotExist, result.getStatusCode());
		assertNull(result.getEmployees());
	}
	
	@Test
	public void testEmployeesFromExcel() {
		String filePath = getTestResDir() + "EmpInfos_EmpImporter.xls";
		EmpImportResult result = EmpImporter.employeesFromExcel(filePath);
		assertEquals(StatusCode.success, result.getStatusCode());
		assertEquals(2, result.getEmployees().size());
		// check for some fields
		Employee firstEmp = result.getEmployees().get(0);
		assertTrue(firstEmp.getName().equalsIgnoreCase("童骅"));
		assertTrue(firstEmp.getMobile().trim().length() == 0);
		assertTrue(firstEmp.getLeavingDate().trim().length() == 0);
		Employee secondEmp = result.getEmployees().get(1);
		assertTrue(secondEmp.getName().equalsIgnoreCase("王林萍"));
		assertTrue(secondEmp.getEmail().trim().length() == 0);
	}

}
