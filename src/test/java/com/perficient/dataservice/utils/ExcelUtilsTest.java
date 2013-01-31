package com.perficient.dataservice.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.*;

public class ExcelUtilsTest {

	@Test
	public void testReadFromExcel() throws Exception {
		String testFilePath = "src\\test\\resources\\test.xls";
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
			assertEquals("File content not matche!", "姓名年龄性别Jack25男Mary30女", strBuf.toString());
		} else {
			fail("Test file does not exist:" + testFilePath);
		}
	}

}
