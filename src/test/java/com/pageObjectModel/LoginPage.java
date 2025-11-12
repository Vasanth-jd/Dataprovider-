package com.pageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interfaceElement.LoginInterface;
import com.usable.BaseClass;
import com.utility.FileReadManager;

public class LoginPage extends BaseClass implements LoginInterface {
	
	@FindBy(id = userName_id)
	private WebElement uName;
	
	@FindBy(id = pass_id)
	private WebElement pass;
	
	@FindBy(name = login_name)
	private WebElement login;
	
	@FindBy(xpath = success_xpath)
	private WebElement success;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	FileReadManager reader = new FileReadManager();
	public void getLogin(String username, String password)  {
		
		sendKeys(uName, username);
		
		sendKeys(pass, password);
		
		elementClick(login);
		explicitlyWait(success, "visiblity", 20);
	
	
	

}
	public String getSuccessMessage() {
		return getText(success);
		
	}
	}
