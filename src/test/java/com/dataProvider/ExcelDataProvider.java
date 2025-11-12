package com.dataProvider;

import org.testng.annotations.DataProvider;

import com.manager.PageObjectManager;

public class ExcelDataProvider {
	PageObjectManager page = new PageObjectManager();
	@DataProvider(name="loginData")
	public Object[][] getExcelData(){
	
		Object[][] dataById = page.getExcelData().getDataById(page.getFileData().getProperty("excelPath"), "Sheet1", "TC_001");
		return dataById;
		
		
		
	}
	@DataProvider(name="loginInvalid")
	public Object[][] getInvalidData(){
		return page.getExcelData().getDataById(page.getFileData().getProperty("excelPath"), "Sheet1","TC_002");
		
	
		
	}

}
