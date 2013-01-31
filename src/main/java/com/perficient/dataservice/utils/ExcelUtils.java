package com.perficient.dataservice.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtils {
	
	public static List<List<String>> readFromExcel(File srcExcelFile, int startRow, int startCol, int endCol) throws JXLException, IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		Workbook workbook = Workbook.getWorkbook(srcExcelFile);
		Sheet sheet = workbook.getSheet(0);
		int rowCount = sheet.getRows();
		int colCount = sheet.getColumns();
		if (rowCount == 0 || colCount == 0) {
			return result;
		}
		int startRowIndex = startRow - 1;
		int startColIndex = startCol - 1;
		int endColIndex = colCount > endCol ? endCol - 1 : colCount - 1;
		
		for (int i = startRowIndex; i < rowCount; i++) {
			List<String> record = new ArrayList<String>();
			for (int j = startColIndex; j <= endColIndex; j++) {
				Cell cell = sheet.getCell(j, i);
				String cellContent = cell.getContents().trim();
				record.add(cellContent);
			}
			result.add(record);
		}
		return result;
	}

}
