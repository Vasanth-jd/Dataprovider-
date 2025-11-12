package com.runner;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dataProvider.ExcelDataProvider;
import com.manager.PageObjectManager;
import com.usable.BaseClass;

import junit.framework.Assert;


public class FacebookRunner extends BaseClass {
	
	   PageObjectManager page = new PageObjectManager();
	
	@BeforeMethod
	public void launchBrowser() {
		launchBrowser(page.getFileData().getProperty("browser"));          
		launchUrl(page.getFileData().getProperty("url"));
	}
	
	@Test(priority=0,dataProvider="loginData",dataProviderClass=ExcelDataProvider.class)
	public void login(String username,String password)  {    
	
		page.getLogin().getLogin(username, password);
		String actual = page.getLogin().getSuccessMessage();
		Assert.assertEquals(actual, page.getFileData().getProperty("success"));
	}
	
	@Test(priority=1,dataProvider="loginInvalid",dataProviderClass=ExcelDataProvider.class)
	public void loginInvalid(String userName, String password) {
		page.getInvalidLogin().getInvalidLogin(userName, password); 
		String actual=page.getInvalidLogin().getErrorMessage();
		
		Assert.assertEquals(actual,page.getFileData().getProperty("error"));                               
		
		
	}
	
	@AfterMethod
	public void terminate() {
		terminateBrowser();
		
	}
	
	
	}

