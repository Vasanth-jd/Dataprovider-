package com.pageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interfaceElement.LoginInterface;
import com.usable.BaseClass;

public class LoginInvalid extends BaseClass implements LoginInterface {

	@FindBy(id = userName_id)
	private WebElement uName;
	
	@FindBy(id = pass_id)
	private WebElement pass;
	
	@FindBy(name = login_name)
	private WebElement login;
	
	@FindBy(xpath = errorMsg_xpath)
	private WebElement error;
	
	public LoginInvalid() {
		PageFactory.initElements(driver, this);
	}
	
	public void getInvalidLogin(String userName,String password) {
		sendKeys(uName, userName );
		sendKeys(pass, password);
		elementClick(login);
		
	}
	public String getErrorMessage() {
		String text = getText(error);
		return text ;
		
	}
}
