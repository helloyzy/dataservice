package com.perficient.dataservice.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.perficient.dataservice.model.Employee;
import com.perficient.dataservice.utils.EmpImportResult.StatusCode;

public class EmpImporter {

	/**
	 * Logic: get the first field (Employee Number), if it is empty, then check
	 * the following fields: if any of the following fields contains
	 * information, consider the whole resultset to be incomplete
	 * 
	 * @param contents
	 * @param startColumn
	 * @param endColumn
	 * @return boolean - if any of the employee record is incomplete, then
	 *         return false else true.
	 */
	private static boolean isEmpInfoComplete(List<List<String>> contents) {
		for (List<String> empInfo : contents) {
			// the first field being the Employee number
			String empNum = empInfo.get(0);
			if (empNum == null || empNum.trim().length() == 0) {
				for (int i = 1; i < empInfo.size(); i++) {
					String info = empInfo.get(i);
					if (info != null && info.trim().length() > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Get the first row (being the title info) and it should match the given
	 * sequence
	 * 
	 * @param contents
	 * @return boolean
	 */
	private static boolean isEmpInfoWellFormed(List<String> titles) {
		// 16 fields currently
		String[] empTitleSequence = Employee.getEmployeeTitleSequence();
		if (titles.size() != empTitleSequence.length) {
			return false;
		}
		for (int i = 0; i < empTitleSequence.length; i++) {
			if (!empTitleSequence[i].equalsIgnoreCase(titles.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static EmpImportResult employeesFromExcel(String fileLocation) {
		EmpImportResult result = new EmpImportResult();
		result.setStatusCode(StatusCode.success);
		File file = new File(fileLocation);
		try {
			if (file.exists()) {
				// needs to read the title
				List<List<String>> contents = ExcelUtils.readFromExcel(file, 2,
						1, 16);
				if (contents.size() == 0) {
					result.setStatusCode(StatusCode.errorNoEmpInfoFromFile);
					return result;
				}
				List<String> titles = contents.get(0);
				if (!isEmpInfoWellFormed(titles)) {
					result.setStatusCode(StatusCode.errorBadFormEmpInfo);
					return result;
				}
				List<List<String>> empInfos = contents.subList(1, contents.size());
				if (!isEmpInfoComplete(empInfos)) {
					result.setStatusCode(StatusCode.errorEmpInfoIncomplete);
					return result;
				}
				List<Employee> empList = new ArrayList<Employee>();
				for (List<String> empInfo : empInfos) {
					Employee emp = new Employee();
					emp.setEmpNum(empInfo.get(0));
					emp.setName(empInfo.get(1));
					emp.setFirstName(empInfo.get(2));
					emp.setLastName(empInfo.get(3));
					emp.setPreferredName(empInfo.get(4));
					emp.setLevel(empInfo.get(5));
					emp.setRole(empInfo.get(6));
					emp.setJoinCompanyDate(empInfo.get(7));
					emp.setStartWorkingDate(empInfo.get(8));
					emp.setLeavingDate(empInfo.get(9));
					emp.setDepartment(empInfo.get(10));
					emp.setSex(empInfo.get(11));
					emp.setEducationBackground(empInfo.get(12));
					emp.setMobile(empInfo.get(13));
					emp.setYahooIM(empInfo.get(14));
					emp.setEmail(empInfo.get(15));
					empList.add(emp);
				}
				result.setEmployees(empList);
			} else {
				result.setStatusCode(StatusCode.errorFileNotExist);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatusCode(StatusCode.errorNotSpecified);
		}
		return result;
	}

	public static List<Employee> employeesFromJson(String filePath) {
		List<Employee> result = new ArrayList<Employee>();
		return result;
	}

}
