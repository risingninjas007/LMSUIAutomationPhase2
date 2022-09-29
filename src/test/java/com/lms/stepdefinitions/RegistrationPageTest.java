package com.lms.stepdefinitions;

import org.testng.Assert;

import com.github.javafaker.Faker;
import com.lms.base.TestBase;
import com.lms.pages.LoginPage;
import com.lms.pages.RegistrationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationPageTest extends TestBase{

	RegistrationPage registrationPage;
	LoginPage loginPage;
	
	Faker faker = new Faker();
	String firstName=faker.name().firstName();
	String lastName=faker.name().lastName();
	String city=faker.address().city();
	String address=faker.address().buildingNumber();
	String street=faker.address().streetAddressNumber();
	String userName=faker.name().username();
	
	@Given("User is on the Registeration Page")
	public void user_is_on_the_registeration_page() {
		registrationPage=new RegistrationPage();
	}
	
	@When("User selects the Log in button")
	public void user_selects_the_log_in_button() {
		loginPage=registrationPage.clickLogin();
	}
	
	@Then("User lands on Log in page")
	public void user_lands_on_log_in_page() {
		String expectedMsg = "Please login to LMS application";

		// To validate if user is on Login page, validate the message displayed
		String actualMsg = loginPage.getRequestForLogin_Message();
		Assert.assertEquals(actualMsg, expectedMsg, "User has not landed on the login page of LMS portal");
	}
	
	@Given("User is on LMS website")
	public void user_is_on_lms_website() {
		NavigateToLMSPortal();
	}
	
	@When("User lands on Registration page")
	public void user_lands_on_registration_page() {
	    String expectedHeader="Registration Form";
	    String actualHeader=registrationPage.getRegistrationHeader();
	    Assert.assertEquals(actualHeader, expectedHeader);
	}
	
	@Then("User sees the heading on the form as {string}")
	public void user_sees_the_heading_on_the_form_as(String expectedHeader) {
	    String actualHeader=registrationPage.getRegistrationHeader();
	    Assert.assertEquals(actualHeader, expectedHeader);
	}
		
	@When("User fills the valid {int} digit Mobile number in Numerics")
	public void user_fills_the_valid_digit_mobile_number_in_numerics(Integer mobileNumber) {
		registrationPage.setData("mobile",mobileNumber.toString());
	}
	
	@Then("The Mobile Number will be displayed")
	public void the_mobile_number_will_be_displayed() {
		String mobileNumber=registrationPage.getData("mobile");
		Assert.assertNotEquals(mobileNumber, "");
	}
	
	@When("User clicks Sign Up button without selecting BirthDate")
	public void user_clicks_sign_up_button_without_selecting_birth_date() {
		registrationPage.signUp();
	}
	
	@When("User fills the City Name in Alphabets only")
	public void user_fills_the_city_name_in_alphabets_only() {
		registrationPage.setData("city",city);
	}
	
	@Then("The City Name will be displayed")
	public void the_city_name_will_be_displayed() {
		String actualCity=registrationPage.getData("city");
		Assert.assertEquals(actualCity, city);
	}

	
	@When("User enters submit button with all fields empty")
	public void user_enters_submit_button_with_all_fields_empty() {
		registrationPage.signUp();	    
	}
	
	@Then("User sees a button with text {string} on the form")
	public void user_sees_a_button_with_text_on_the_form(String expectedText) {
	    String actualLoginText=registrationPage.getLoginButtonText();
	    Assert.assertEquals(actualLoginText, expectedText);
	}
	
	@When("User fills the First Name in Alphabets only")
	public void user_fills_the_first_name_in_alphabets_only() {
		registrationPage.setData("firstName",firstName);
	}

	@Then("The First Name will be displayed")
	public void the_first_name_will_be_displayed() {
		String actualFirstName=registrationPage.getData("firstName");
		Assert.assertEquals(actualFirstName, firstName);	    
	}

	@When("User fills the Last Name in Alphabets only")
	public void user_fills_the_last_name_in_alphabets_only() {
		registrationPage.setData("lastName",lastName);
	}

	@Then("The Last Name will be displayed")
	public void the_last_name_will_be_displayed() {
		String actualLastName=registrationPage.getData("lastName");
		Assert.assertEquals(actualLastName, lastName);	    
	}

	@When("User fills the Address Field using Alpha Numerics and or Symbols")
	public void user_fills_the_address_field_using_alpha_numerics_and_or_symbols() {
		registrationPage.setData("address",address);
	}

	@Then("The Address will be displayed")
	public void the_address_will_be_displayed() {
		String actualAddress=registrationPage.getData("address");
		Assert.assertEquals(actualAddress, address);	
	}

	@When("User fills the Street name Field using Alpha Numerics and or Symbols")
	public void user_fills_the_street_name_field_using_alpha_numerics_and_or_symbols() {
		registrationPage.setData("street",street);
	}

	@Then("The Street Name will be displayed")
	public void the_street_name_will_be_displayed() {
		String actualStreetAddress=registrationPage.getData("street");
		Assert.assertEquals(actualStreetAddress, street);		    
	}

	@When("User fills the House Number field using Alpha Numerics and or Symbols")
	public void user_fills_the_house_number_field_using_alpha_numerics_and_or_symbols() {
	    //same as address on new Registration page
	}

	@Then("The House Number will be displayed")
	public void the_house_number_will_be_displayed() {
		 //same as address on new Registration page
	}

	@When("User fills the {int} digit Zip code using Numbers only")
	public void user_fills_the_digit_zip_code_using_numbers_only(Integer zipCode) {
		registrationPage.setData("zip",zipCode.toString());
	}

	@Then("The Zip code will be displayed")
	public void the_zip_code_will_be_displayed() {
		String zipCode=registrationPage.getData("zip");
		Assert.assertNotEquals(zipCode, "");
	}

	@When("User selects the respective State Name from the DropBox")
	public void user_selects_the_respective_state_name_from_the_drop_box() {
		registrationPage.selectState();    
	}

	@Then("The State Name will be displayed")
	public void the_state_name_will_be_displayed() {
	}

	@When("User Selects the Date of Birth from the Calender")
	public void user_selects_the_date_of_birth_from_the_calender() {
	    //Require the screen to understand the kind of calendar control used
	}

	@Then("Birth Date will be Displayed")
	public void birth_date_will_be_displayed() {
		 //Require the screen to understand the kind of calendar control used	    
	}

	@When("User fills the valid unique UserName")
	public void user_fills_the_valid_unique_user_name() {
	    registrationPage.setData("userName", userName);
	}

	@Then("UserName will be displayed")
	public void user_name_will_be_displayed() {
		String actualUserName=registrationPage.getData("userName");
		Assert.assertEquals(actualUserName, userName);	
	}

	@When("User fills the valid Password with {int} Upper case, {int} Numeric and {int} special charecter and {int} charecters")
	public void user_fills_the_valid_password_with_upper_case_numeric_and_special_charecter_and_charecters(Integer cntUpperCase, Integer cntNumbers, Integer cntSpclCharacters, Integer cntCharacters) {
	registrationPage.setData("password", "password@123");
	}

	@Then("User Password will  be displayed")
	public void user_password_will_be_displayed() {
		String password=registrationPage.getData("password");
		Assert.assertNotEquals(password, ""); 
	    
	}

	@When("User clicks Sign Up button with invalid First Name")
	public void user_clicks_sign_up_button_with_invalid_first_name() {
		registrationPage.setData("firstName", "invalid$%%^");
	    registrationPage.signUp();    
	}

	@When("User clicks Sign Up button with invalid Last Name")
	public void user_clicks_sign_up_button_with_invalid_last_name() {
		registrationPage.setData("lastName", "invalid$%%^");
		registrationPage.signUp();  	    
	}

	@When("User clicks Sign Up button with invalid Address")
	public void user_clicks_sign_up_button_with_invalid_address() {
		registrationPage.setData("address", "invalid$%%^");
		registrationPage.signUp();   	    
	}

	@When("User clicks Sign Up button with invalid Street Name")
	public void user_clicks_sign_up_button_with_invalid_street_name() {
		registrationPage.setData("street", "invalid$%%^");
		registrationPage.signUp();  	    
	}

	@When("User clicks Sign Up button with invalid House Number")
	public void user_clicks_sign_up_button_with_invalid_house_number() {
		//same as address on new Registration page	    
	}

	@When("User clicks Sign Up button with invalid Zip")
	public void user_clicks_sign_up_button_with_invalid_zip() {
		registrationPage.setData("zip", "invalid$%%^");
		registrationPage.signUp(); 	    
	}

	@When("User clicks Sign Up button with invalid  City")
	public void user_clicks_sign_up_button_with_invalid_city() {
		registrationPage.setData("city", "invalid$%%^");
		registrationPage.signUp(); 
	}

	@When("User clicks Sign Up button without selecting  State")
	public void user_clicks_sign_up_button_without_selecting_state() {
		registrationPage.signUp();	    
	}

	@When("User clicks Sign Up button with invalid Phone Number")
	public void user_clicks_sign_up_button_with_invalid_phone_number() {
		registrationPage.setData("mobile", "invalid$%%^");
		registrationPage.signUp();	    
	}

	@When("User clicks Sign Up button with invalid UserName")
	public void user_clicks_sign_up_button_with_invalid_user_name() {
		registrationPage.setData("userName", "invalid$%%^");
		registrationPage.signUp();    
	}

	@When("User clicks Sign Up button with invalid Password")
	public void user_clicks_sign_up_button_with_invalid_password() {
		registrationPage.setData("password", "invalid$%%^");
		registrationPage.signUp();
	}

	@When("USer clicks Sign Up button with existing User name")
	public void u_ser_clicks_sign_up_button_with_existing_user_name() {
		registrationPage.signUp();
	}

	@Then("User should get a message {string}")
	public void user_should_get_a_message(String expectedError) {
	    String actualError=registrationPage.getErrorMsg();
	    Assert.assertEquals(actualError, expectedError);
	}

	@When("User enters all Field value and click {string} button.")
	public void user_enters_all_field_value_and_click_button(String string) {
	    
	    
	}

}
