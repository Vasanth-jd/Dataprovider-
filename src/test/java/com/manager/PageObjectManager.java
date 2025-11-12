package com.manager;

import com.pageObjectModel.LoginInvalid;
import com.pageObjectModel.LoginPage;
import com.utility.ExcelUtility;
import com.utility.FileReadManager;

public class PageObjectManager {
	private FileReadManager fileData;
	public FileReadManager getFileData() {
		if(fileData==null) {
			fileData=new FileReadManager();
		}
		return fileData;
		
	}
	
	
	private LoginPage login;
	public LoginPage getLogin() {
		if(login==null) {
			login= new LoginPage();
		}
		return login;
		
	}
	
	private ExcelUtility excelData;
	public ExcelUtility getExcelData() {
		if(excelData==null) {
			excelData= new ExcelUtility();
		}
		return excelData;
		
	}
	
	private LoginInvalid invalidData;
	public LoginInvalid getInvalidLogin() {
		if(invalidData==null) {
			invalidData= new LoginInvalid();
		}
		return invalidData;
		
	}
	
}