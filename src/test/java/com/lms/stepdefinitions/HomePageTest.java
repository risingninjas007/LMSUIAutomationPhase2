package com.lms.stepdefinitions;

import org.testng.Assert;

import com.lms.base.TestBase;
import com.lms.pages.HomePage;
import com.lms.pages.LoginPage;

import com.lms.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;

	@Given("User landed on the Home page after logging into the LMS website")
	public void user_landed_on_the_home_page_after_logging_into_the_lms_website() {
		loginPage = new LoginPage();
		String userName = ConfigReader.getConfigValue("username");
		String password = ConfigReader.getConfigValue("password");
		homePage = loginPage.login(userName, password);
	}

	@Then("verify that title of the page is {string}")
	public void verify_that_title_of_the_page_is(String expectedTitle) {
		String actualTitle = homePage.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Login has failed and user is not on Home Page");
	}

	@Then("User should see a heading with text {string} on the Home page")
	public void user_should_see_a_heading_with_text_on_the_home_page(String expectedHeader) {
		String actualHeader = homePage.getPageHeader();
		Assert.assertEquals(actualHeader, expectedHeader, "Login has failed and user is not on Home Page");
	}

	@Then("User should see a button with text {string} on the menu bar")
	public void user_should_see_a_button_with_text_on_the_menu_bar(String expectedMenu) {
		Assert.assertTrue(homePage.verifyMenu(expectedMenu),
				expectedMenu + " is not available as ope of the menu option in the header");
	}

}
