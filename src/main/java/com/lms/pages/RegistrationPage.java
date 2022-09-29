package com.lms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.TestBase;

public class RegistrationPage extends TestBase {

	WebDriver driver;

	@FindBy(xpath = "")
	WebElement lblPageHeader;

	@FindBy(xpath = "")
	WebElement btnLogin;

	@FindBy(xpath = "")
	WebElement txtFirstName;

	@FindBy(xpath = "")
	WebElement txtLastName;

	@FindBy(xpath = "")
	WebElement txtHouseNum;

	@FindBy(xpath = "")
	WebElement txtCity;

	@FindBy(xpath = "")
	WebElement txtStreet;

	@FindBy(xpath = "")
	WebElement state;

	@FindBy(xpath = "")
	WebElement txtZip;

	@FindBy(xpath = "")
	WebElement birthDate;

	@FindBy(xpath = "")
	WebElement txtUserName;

	@FindBy(xpath = "")
	WebElement txtEmail;

	@FindBy(xpath = "")
	WebElement txtPhone;

	@FindBy(xpath = "")
	WebElement txtPassword;

	@FindBy(xpath = "")
	WebElement btnSignUp;

	@FindBy(xpath = "")
	WebElement lblErrorMsg;

	public RegistrationPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickLogin() {
		btnLogin.click();
		return new LoginPage();
	}

	public String getRegistrationHeader() {
		return lblPageHeader.getText();
	}

	public void setData(String category, String value) {
		switch (category) {
		case "mobile":
			txtPhone.clear();
			txtPhone.sendKeys(value);
			break;
		case "city":
			txtCity.clear();
			txtCity.sendKeys(value);
			break;
		case "firstName":
			txtFirstName.clear();
			txtFirstName.sendKeys(value);
			break;
		case "lasttName":
			txtLastName.clear();
			txtLastName.sendKeys(value);
			break;
		case "address":
			txtHouseNum.clear();
			txtHouseNum.sendKeys(value);
			break;
		case "street":
			txtStreet.clear();
			txtStreet.sendKeys(value);
			break;
		case "zip":
			txtZip.clear();
			txtZip.sendKeys(value);
			break;
		case "userName":
			txtUserName.clear();
			txtUserName.sendKeys(value);
			break;
		case "password":
			txtPassword.clear();
			txtPassword.sendKeys(value);
			break;
		}
	}

	public String getData(String category) {
		String value = "";
		switch (category) {
		case "mobile":
			value = txtPhone.getText();
			break;
		case "city":
			value = txtCity.getText();
			break;
		case "firstName":
			value = txtFirstName.getText();
			break;
		case "lasttName":
			value = txtLastName.getText();
			break;
		case "address":
			value = txtHouseNum.getText();
			break;
		case "street":
			value = txtStreet.getText();
			break;
		case "zip":
			value = txtZip.getText();
			break;
		case "password":
			value = txtPassword.getText();
			break;
		case "userName":
			value = txtUserName.getText();
			break;
		}

		return value;
	}

	public void signUp() {
		btnSignUp.click();
	}

	public String getErrorMsg() {
		return lblErrorMsg.getText();
	}

	public String getLoginButtonText() {
		return btnLogin.getText();
	}

	public void selectState() {
		// Code with Select

	}
}
