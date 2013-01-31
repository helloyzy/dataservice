package com.perficient.dataservice.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.*;

public class ExcelUtilsTest {
		
	private static String getTestResDir() {
		return String.format("src%stest%sresources%s", File.separator, File.separator, File.separator);
	}

	@Test
	public void testReadFromExcel() throws Exception {
		String testFilePath = getTestResDir() + "test.xls";
		File file = new File(testFilePath);
		if (file.exists()) {
			List<List<String>> contents = ExcelUtils.readFromExcel(file, 2, 1,
					3);
			StringBuffer strBuf = new StringBuffer();
			for (List<String> record : contents) {
				for (String field : record) {
					strBuf.append(field);
				}
			}
			assertEquals("File content not matche!", "姓名年龄性别Jack25男Mary30女",
					strBuf.toString());
		} else {
			fail("Test file does not exist:" + testFilePath);
		}
	}

	@Test
	public void testReadEmpInfo() throws Exception {
		String testFilePath = getTestResDir() + "EmpInfos.xls";
		File file = new File(testFilePath);
		if (file.exists()) {
			List<List<String>> contents = ExcelUtils.readFromExcel(file, 3, 1,
					16);
			StringBuffer strBuf = new StringBuffer();
			for (List<String> record : contents) {
				for (String field : record) {
					strBuf.append(field);
				}
				strBuf.append(";");
			}
			assertEquals("File content not matche!", "HE010童骅HuaTongSamDirectorDeveloper2004/10/082000/08/01DeliveryMBachelor13857197978sam_tong78sam.tong@perficientgdc.com.cn;" +
					"HE012王林萍LinpingWangLinpingHR SupervisorC&B2004/10/251996/09/01HumanResourceFBachelor13805785340liberling2002linping.wang@perficientgdc.com.cn;", strBuf.toString());
		} else {
			fail("Test file does not exist:" + testFilePath);
		}
	}
}
