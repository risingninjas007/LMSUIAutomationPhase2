package com.lms.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.TestBase;

/** Page class holding the web elements and methods for Login Page **/
public class LoginPage extends TestBase{

	WebDriver driver;
	Map<String, String> errorMessages;
	
	@FindBy(xpath = "//div[@class='signin-content']//p")
	WebElement lblLoginRequestMsg;// Please login to LMS application

	@FindBy(id = "username")
	WebElement txtUserName;

	@FindBy(id = "password")
	WebElement txtPassword;

	@FindBy(id = "login")
	WebElement btnLogin;

	// Invalid username and password Please try again
	@FindBy(id = "errormessage")
	WebElement lblErrorMsg;

	// Please enter your user name
	@FindBy(xpath = "//div/input[@id='username']/../../following-sibling::div//mat-error")
	WebElement lblUserNameErrorMsg;

	// Please enter your user password
	@FindBy(xpath = "//div/input[@id='password']/../../following-sibling::div//mat-error")
	WebElement lblPasswordErrorMsg;

	public LoginPage() {
		this.driver = getDriver(); //get the driver instance for active thread	
		PageFactory.initElements(driver, this);
	}


	public String getRequestForLogin_Message() {
		return lblLoginRequestMsg.getText(); // Please login to LMS application
	}

	//HashMap has been used since there are multiple error messages are displayed
	public Map<String, String> login() {
		btnLogin.click();
		errorMessages = new HashMap<>();
		errorMessages.put("generalErrorMsg", lblErrorMsg.getText());// Invalid username and password Please try again
		errorMessages.put("userNameErrorMsg", lblUserNameErrorMsg.getText());//Please enter your user name
		errorMessages.put("passwordErrorMsg", lblPasswordErrorMsg.getText());//Please enter your password
		return errorMessages;
	}

	public Map<String, String> loginWithUserName(String userName) {
		errorMessages = new HashMap<>();
		txtUserName.sendKeys(userName);
		txtPassword.clear();
		btnLogin.click();
		errorMessages.put("generalErrorMsg", lblErrorMsg.getText());
		return errorMessages; // Invalid username and password Please try again
	}

	public Map<String, String> loginWithPassword(String password) {
		txtUserName.clear();
		errorMessages = new HashMap<>();
		txtPassword.sendKeys(password);
		
		
		btnLogin.click();
		//waitforVisibility(lblUserNameErrorMsg);
		errorMessages.put("userNameErrorMsg", lblUserNameErrorMsg.getText());//Please enter your user name
		//waitforVisibility(lblErrorMsg);
		errorMessages.put("generalErrorMsg", lblErrorMsg.getText());// Invalid username and password Please try again
		return errorMessages; // Please enter your user name
	}

	
	public HomePage login(String userName, String password) {
		// clear previously entered values if any
		txtUserName.clear();
		txtPassword.clear();
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(password);

		btnLogin.click();
		return new HomePage(); // Return an instance of LMS Home Page on successfull login
	}

	
	
}
