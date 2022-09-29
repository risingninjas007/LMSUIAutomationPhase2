package com.lms.stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.lms.base.TestBase;
import com.lms.pages.HomePage;
import com.lms.pages.LoginPage;
import com.lms.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** Step definitions file for Login scenarios */
public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	Map<String, String> errorMessages = new HashMap<>();
	String invalidUserErrorMsg, invalidPasswordErrorMsg;

	

	@Given("User is on the Login Page")
	public void user_is_on_the_login_page() {
		loginPage = new LoginPage();
		String expectedMsg = "Please login to LMS application";

		// To validate if user is on Login page, validate the message displayed
		String actualMsg = loginPage.getRequestForLogin_Message();
		Assert.assertEquals(actualMsg, expectedMsg, "User has not landed on the login page of LMS portal");
	}

	@When("User clicks the Login button")
	public void user_clicks_the_login_button() {
		errorMessages = loginPage.login();
	}

	@Then("User should receive the message {string}")
	public void user_should_receive_the_message(String generalErrorMsg) {
		String actualMsg = errorMessages.get("generalErrorMsg");
		Assert.assertEquals(actualMsg, generalErrorMsg, "The error message for invalid credentials is not displayed");
	}

	@Then("User should receive {string} message under username")
	public void user_should_receive_message_under_username(String userNameErrorMsg) {
		invalidUserErrorMsg = errorMessages.get("userNameErrorMsg");
		Assert.assertEquals(invalidUserErrorMsg, userNameErrorMsg,
				"The error message for invalid user name is not displayed");
	}

	@Then("User should receive {string} message under password")
	public void user_should_receive_message_under_password(String passwordErrorMsg) {
		invalidPasswordErrorMsg = errorMessages.get("passwordErrorMsg");
		Assert.assertEquals(invalidPasswordErrorMsg, passwordErrorMsg,
				"The error message for invalid password is not displayed");
	}

	@When("User clicks the Login button after entering invalid username")
	public void user_clicks_the_login_button_after_entering_invalid_username() {
		errorMessages = loginPage.loginWithUserName("Invalid");
	}

	@When("User clicks the Login button after entering invalid password")
	public void user_clicks_the_login_button_after_entering_invalid_password() {
		errorMessages = loginPage.loginWithPassword("invalid");
	}

	@When("User clicks the Login button after entering valid username and password")
	public void user_clicks_the_login_button_after_entering_valid_username_and_password() {
		String userName = ConfigReader.getConfigValue("username");
		String password = ConfigReader.getConfigValue("password");
		homePage = loginPage.login(userName, password);
	}

	@Then("User should see the LMS Home page.")
	public void user_should_see_the_lms_home_page() {
		String expectedHeader = "LMS - Learning Management System";
		String actualHeader = homePage.getPageHeader();

		// To validate if user is on Home page, validate the header displayed
		Assert.assertEquals(actualHeader, expectedHeader,
				"User has not landed on the home page of LMS portal");
	}

}
