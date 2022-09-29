package com.lms.stepdefinitions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.lms.pages.HomePage;
import com.lms.pages.LoginPage;
import com.lms.pages.ManageProgramPage;
import com.lms.pages.ModifyProgramDetailsPage;
import com.lms.utils.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageProgramTest extends com.lms.base.LMSBasePage {
	HomePage homePage;
	LoginPage loginPage;
	ManageProgramPage manageProgramPage;
	ModifyProgramDetailsPage modifyProgramDetailsPage;
	int totalEntries;
	String progNameErrMsg = "";
	List<String> listOfProgramsSelected=new ArrayList<String>();

	@Given("User logins in with valid username and password")
	public void user_logins_in_with_valid_username_and_password() {
		loginPage = new LoginPage();
		String userName = ConfigReader.getConfigValue("username");
		String password = ConfigReader.getConfigValue("password");		
		homePage = loginPage.login(userName, password);
	}

	@Given("User is logged on to LMS website")
	public void user_is_logged_on_to_lms_website() {
		String expectedHeader = "LMS - Learning Management System";
		String actualHeader = homePage.getPageHeader();

		// To validate if user is on  LMS website page, validate the header displayed
		Assert.assertEquals(actualHeader, expectedHeader,
				"User has not landed on the home page of LMS portal");
	}

	@When("User is on Program page")
	public void user_is_on_program_page() {
		manageProgramPage = homePage.navigatetoPrograms();
	}

	@Then("User should see program page heading with text {string} on the page")
	public void user_should_see_program_page_heading_with_text_on_the_page(String programHeader) {

		String actualHeader = manageProgramPage.getPageHeader();

		// To validate if user is on Home page, validate the header displayed
		Assert.assertEquals(actualHeader, programHeader,
				"User has not landed on the manage program page of LMS portal");

	}

	@Then("User should see the text as {string} total entries below the table.")
	public void user_should_see_the_text_as_total_entries_below_the_table(String expectedInfoText) {
		totalEntries = getTotalEntries();
		int entriesPerPage = getEntriesPerPage();

		expectedInfoText = "Showing 1 to " + entriesPerPage + " of " + totalEntries + " entries";
		String actualInfoText = getCurrentPaginationText();
		Assert.assertEquals(actualInfoText, expectedInfoText);
	}

	@Then("User should see the footer as {string} programs")
	public void user_should_see_the_footer_as_programs(String expectedFooterText) {
		totalEntries = manageProgramPage.getTotalEntries();

		expectedFooterText = expectedFooterText + totalEntries + " programs.";
		String actualInfoText = getFooterText();
		Assert.assertEquals(actualInfoText, expectedFooterText);
	}

	@Then("User should see the Delete button on the top left hand side as Disabled")
	public void user_should_see_the_delete_button_on_the_top_left_hand_side_as_disabled() {
		Assert.assertTrue(isDeleteDisabled(), "Delete button on top left corner of table is not disabled page load");
	}

	@Then("Verify that number of records \\(rows of data in the table) displayed on the page are {int}")
	public void verify_that_number_of_records_rows_of_data_in_the_table_displayed_on_the_page_are(
			Integer expectedRecordsPerPage) {
		int actualProgramsPerPage = getEntriesPerPage();

		Assert.assertEquals(actualProgramsPerPage, expectedRecordsPerPage);
	}

	@Then("Text box used for search has text as {string}")
	public void text_box_used_for_search_has_text_as(String expectedSearchText) {
		String actualSearchText = validateSearchText();
		Assert.assertEquals(actualSearchText, expectedSearchText, "Search text doesn't match");
	}

	@When("User enters  {string} into search box.")
	public void user_enters_into_search_box(String searchText) throws InterruptedException {
		searchData(searchText);
	}

	@Then("Entries for {string} are shown.")
	public void entries_for_are_shown(String searchText) {
		Assert.assertTrue(validateSearchResultsByEntries(searchText),
				"Search results does not match the search criteria for program");
	}

	@Then("Entries for status {string} are shown.")
	public void entries_for_status_are_shown(String searchText) {
		Assert.assertTrue(manageProgramPage.validateSearchResultsByStatus(searchText),
				"Search results does not match the search criteria for status");
	}

	@When("User selects {string} Program using checkbox")
	public void user_selects_program_using_checkbox(String program) throws InterruptedException {
		manageProgramPage.searchData(program);
		manageProgramPage.selectProgram(program);
	}

	@Then("{string} Program gets selected")
	public void program_gets_selected(String program) {
		Assert.assertTrue(manageProgramPage.validateProgramSelection(program), program + " is not selected");
	}

	@When("User clicks on Edit button")
	public void user_clicks_on_edit_button() {
		modifyProgramDetailsPage = manageProgramPage.editProgram();
	}

	@Then("User lands on Program Details form.")
	public void user_lands_on_program_details_form() {
		String expectedHeader = "Program Details";
		String actualHeader = modifyProgramDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Program Details page");
	}

	@Given("User is on Program Details form")
	public void user_is_on_program_details_form() {
		manageProgramPage = homePage.navigatetoPrograms();
		modifyProgramDetailsPage = manageProgramPage.editProgram();
		String expectedHeader = "Program Details";
		String actualHeader = modifyProgramDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Program Details page");
	}

	@When("User edits Name to {string} and selects the Save button")
	public void user_edits_name_to_and_selects_the_save_button(String newProgramName) {
		modifyProgramDetailsPage.updateProgramName(newProgramName);
	}

	@Then("User can see updated Name as {string}")
	public void user_can_see_updated_name_as(String newProgramName) throws InterruptedException {
		String expectedSucessUpdateMsg = "Program Updated";
		String actualSucessUpdateMsg = manageProgramPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageProgramPage.verifyEntryUpdate(newProgramName),
				"Program name is not updated to " + newProgramName);
	}

	@When("User edits Description to {string} and selects the Save button")
	public void user_edits_description_to_and_selects_the_save_button(String newDescription) {
		modifyProgramDetailsPage.updateProgramDescription(newDescription);
	}

	@Then("User can see updated Description as {string}")
	public void user_can_see_updated_description_as(String newDescription) throws InterruptedException {
		String expectedSucessUpdateMsg = "Program Updated";
		String actualSucessUpdateMsg = manageProgramPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageProgramPage.verifyEntryUpdate(newDescription),
				"Program description is not updated to " + newDescription);
	}

	@Given("User is on Program Details form for {string}")
	public void user_is_on_program_details_form_for(String program) throws InterruptedException {
		manageProgramPage = homePage.navigatetoPrograms();
		modifyProgramDetailsPage = manageProgramPage.editProgram(program);
		String expectedHeader = "Program Details";
		String actualHeader = modifyProgramDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Program Details page");
	}

	@When("User changes status to {string} and selects the Save button")
	public void user_changes_status_to_and_selects_the_save_button(String newStatus) {
		modifyProgramDetailsPage.updateProgramStatus(newStatus);
	}

	@Then("User can see updated Status for program {string} should be {string}")
	public void user_can_see_updated_status_for_program_should_be(String program, String status)
			throws InterruptedException {
		String expectedSucessUpdateMsg = "Program Updated";
		String actualSucessUpdateMsg = manageProgramPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageProgramPage.verifyProgramUpdate(program, status),
				"Program description is not updated to " + status);
	}

	@When("User clicks Cancel button")
	public void user_clicks_cancel_button() {
		modifyProgramDetailsPage.cancelUpdate();
	}

	@Then("User can see Program Details form disappears")
	public void user_can_see_program_details_form_disappears() {
		String actualHeader = manageProgramPage.getPageHeader();
		String programHeader = "Manage Program";
		// To validate if Program Details form disappears, validate the header displayed
		// on programs page since the control is returned back to Programs page
		Assert.assertEquals(actualHeader, programHeader);
	}

	@When("User clicks Save button")
	public void user_clicks_save_button() {
		modifyProgramDetailsPage.saveProgramDetails();
	}

	@Then("User can see {string} message")
	public void user_can_see_message(String expectedSucessUpdateMsg) {
		String actualSucessUpdateMsg = verifyConfirmationMsg();
		//Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);
		Assert.assertTrue(actualSucessUpdateMsg.contains(expectedSucessUpdateMsg));
	}

	@When("User clicks on Delete button")
	public void user_clicks_on_delete_button() {
		modifyProgramDetailsPage = manageProgramPage.deleteProgram();
	}

	@Then("User lands on Confirm Deletion form.")
	public void user_lands_on_confirm_deletion_form() {
		//String expectedMsg = "Are you sure you want to delete";
		String actualMsg = getDeleteConfirmMsg();
		Assert.assertNotNull(actualMsg);
		//Assert.assertTrue(actualMsg.contains(expectedMsg), "User is not on Deletion page");
	}

	@Given("User is on Confirm Deletion form")
	public void user_is_on_confirm_deletion_form() {
		manageProgramPage = homePage.navigatetoPrograms();
		modifyProgramDetailsPage = manageProgramPage.deleteProgram();
		String expectedMsg = "Are you sure you want to delete";
		String actualMsg = getDeleteConfirmMsg();
		Assert.assertTrue(actualMsg.contains(expectedMsg), "User is not on Deletion page");
	}

	@When("User clicks {string} button")
	public void user_clicks_button(String userOption) {
		selectDeleteConfirmation(userOption);
	}

		
	@Then("User can see Confirm Deletion form disappears")
	public void user_can_see_confirm_deletion_form_disappears() {
		String actualHeader = manageProgramPage.getPageHeader();
		String programHeader = "Manage Program";
		// To validate if Program Details form disappears, validate the header displayed
		// on programs page since the control is returned back to Programs page
		Assert.assertEquals(actualHeader, programHeader);
	}

	@When("User clicks A New Program button")
	public void user_clicks_a_new_program_button() {
		modifyProgramDetailsPage = manageProgramPage.addNewProgram();
	}

	@Given("User is on New Program Details form")
	public void user_is_on_new_program_details_form() {
		manageProgramPage = homePage.navigatetoPrograms();
		modifyProgramDetailsPage = manageProgramPage.addNewProgram();
	}

	@When("User clicks Save button without entering data")
	public void user_clicks_save_button_without_entering_data() {
		progNameErrMsg = modifyProgramDetailsPage.saveProgramDetails();
	}

	@Then("User gets message {string}")
	public void user_gets_message(String expectedProgNameErrMsg) {
		Assert.assertEquals(progNameErrMsg, expectedProgNameErrMsg);
	}

	@When("User enters {string} in program name text box")
	public void user_enters_in_program_name_text_box(String expectedProgramName) {
		modifyProgramDetailsPage.setProgramName(expectedProgramName);
	}

	@Then("User can see {string} entered in program name text box")
	public void user_can_see_entered_in_program_name_text_box(String expectedProgramName) {
		String programName = modifyProgramDetailsPage.getProgramName();
		Assert.assertEquals(programName, expectedProgramName);
	}

	@When("User enters {string} in program description text box")
	public void user_enters_in_program_description_text_box(String expectedProgramDesc) {
		modifyProgramDetailsPage.setProgramDescription(expectedProgramDesc);
	}

	@Then("User can see {string} entered in program description text box")
	public void user_can_see_entered_in_program_description_text_box(String expectedProgramDesc) {
		String programName = modifyProgramDetailsPage.getProgramDescription();
		Assert.assertEquals(programName, expectedProgramDesc);
	}

	@When("User selects {string} using radiobutton")
	public void user_selects_using_radiobutton(String expectedStatus) {
		modifyProgramDetailsPage.setProgramStatus(expectedStatus);
	}

	@Then("User can see {string} status selected")
	public void user_can_see_status_selected(String expectedStatus) {
		String programStatus = modifyProgramDetailsPage.getProgramStatus();
		Assert.assertEquals(programStatus.toLowerCase(), expectedStatus.toLowerCase());
	}

	@When("User selects more than one Program using checkbox")
	public void user_selects_more_than_one_program_using_checkbox(DataTable programsList) throws InterruptedException {
		
		List<List<String>> listOfProgramsFromTable = programsList.asLists(String.class);
		for (List<String> program : listOfProgramsFromTable) {
			
			listOfProgramsSelected.add(program.get(0));
			
			manageProgramPage.searchData(program.get(0));
			manageProgramPage.selectProgram(program.get(0));
		}
	}

	@Then("Programs get selected")
	public void programs_get_selected(DataTable expectedProgramsTable) {
		
		List<List<String>> listOfProgramsFromTable = expectedProgramsTable.asLists(String.class);
		List<String> expectedProgramsList=new ArrayList<String>();
		for (List<String> program : listOfProgramsFromTable) {
			expectedProgramsList.add(program.get(0));
		}
		
		Assert.assertEquals(listOfProgramsSelected, expectedProgramsList);
	}
	
	@When("User selects a {string} using checkbox")
	public void user_selects_a_using_checkbox(String program) throws InterruptedException {
		manageProgramPage.searchData(program);
		manageProgramPage.selectProgram(program);
	}
	@When("User clicks on Delete button on top left corner")
	public void user_clicks_on_delete_button_on_top_left_corner() {
		modifyProgramDetailsPage=manageProgramPage.deleteSelectedProgram();
	}
	
	@Then("Verify that previous link is disabled")
	public void verify_that_previous_link_is_disabled() {
		Assert.assertTrue(verifyPrevLinks(),
				"Previous links for navigating pages are not disbaled when the home page is loaded for the first time after login" );
	}
	
	@Given("User is on the page number {string}")
	public void user_is_on_the_page_number(String prevPage) {
		manageProgramPage = homePage.navigatetoPrograms();
		manageProgramPage.goToPage(prevPage);
	}
	
		
	@When("User clicks navigate {string} button")
	public void user_clicks_navigate_button(String navigation) {
		navigate(navigation);
	}

	
	@Then("User navigated to page number {string}")
	public void user_navigated_to_page_number(String nextPage) {
		Assert.assertTrue(verifyCurrentPage(nextPage),
				"User has not navigated to page number "+nextPage );
	}
	
	@When("User is on last page of Manage Program")
	public void user_is_on_last_page_of_manage_program() {
		manageProgramPage = homePage.navigatetoPrograms();
		navigateToLastPage();
	}
	@Then("Verify that next link is disabled")
	public void verify_that_next_link_is_disabled() {
		Assert.assertTrue(verifyNextLinks(),
				"Next links for navigating pages are not disbaled when the last page is loaded" );
	}
	
	@When("User clicks onthe Ascending arrow \\(down ) near to the Program name")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_program_name() {
		manageProgramPage.Sort("program","ascending");
	}
	@Then("User can see the results in Ascending order of Program name")
	public void user_can_see_the_results_in_ascending_order_of_program_name() {
		Assert.assertTrue(manageProgramPage.verifySort("program","ascending"),"Program Names are not sorted in ascending order");
	}

	@When("User clicks onthe Ascending arrow \\(down ) near to the Program Description")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_program_description() {
		manageProgramPage.Sort("program description","ascending");
	}
	@Then("User can see the results in Ascending order of Program Description")
	public void user_can_see_the_results_in_ascending_order_of_program_description() {
		Assert.assertTrue(manageProgramPage.verifySort("program description","ascending"),"Program Descriptions are not sorted in ascending order");
	}
	
	@When("User clicks onthe Ascending arrow \\(down ) near to the Program Status")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_program_status() {
		manageProgramPage.Sort("program status","ascending");
	}
	@Then("User can see the results in Ascending order of Program Status")
	public void user_can_see_the_results_in_ascending_order_of_program_status() {
		Assert.assertTrue(manageProgramPage.verifySort("program status","ascending"),"Program Status is not sorted in ascending order");
	}

	
	@When("User clicks onthe Descending arrow \\(down ) near to the Program name")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_program_name() {
		manageProgramPage.Sort("program","descending");
	}
	@Then("User can see the results in Descending order of Program name")
	public void user_can_see_the_results_in_descending_order_of_program_name() {
		Assert.assertTrue(manageProgramPage.verifySort("program","descending"),"Program Names are not sorted in descending order");
	}

	@When("User clicks onthe Descending arrow \\(down ) near to the Program Description")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_program_description() {
		manageProgramPage.Sort("program description","descending");
	}
	@Then("User can see the results in Descending order of Program Description")
	public void user_can_see_the_results_in_descending_order_of_program_description() {
		Assert.assertTrue(manageProgramPage.verifySort("program description","descending"),"Program Descriptions are not sorted in descending order");
	}

	@When("User clicks onthe Descending arrow \\(down ) near to the Program Status")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_program_status() {
		manageProgramPage.Sort("program status","descending");
	}
	@Then("User can see the results in Descending order of Program Status")
	public void user_can_see_the_results_in_descending_order_of_program_status() {
		Assert.assertTrue(manageProgramPage.verifySort("program status","descending"),"Program Status is not sorted in descending order");
	}

}
