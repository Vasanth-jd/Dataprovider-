package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public Object[][] getDataById(String filePath, String sheetName, String id) {
	    Object[][] result = null;
	    try {
	        FileInputStream fis = new FileInputStream(new File(filePath));
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheet(sheetName);
	        DataFormatter formatter = new DataFormatter();

	        Row header = sheet.getRow(0);
	        int totalCols = header.getPhysicalNumberOfCells();
	        int totalRows = sheet.getPhysicalNumberOfRows();

	        for (int i = 1; i < totalRows; i++) {
	            Row row = sheet.getRow(i);
	            if (row != null && formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase(id)) {
	                result = new Object[1][totalCols - 1]; // Exclude ID
	                for (int j = 1; j < totalCols; j++) {
	                    result[0][j - 1] = formatter.formatCellValue(row.getCell(j));
	                }
	                break;
	            }
	        }
	        workbook.close();
	        fis.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
