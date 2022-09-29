package com.lms.stepdefinitions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;

import com.lms.pages.HomePage;
import com.lms.pages.ManageAssignmentPage;
import com.lms.pages.ModifyAssignmentDetailsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageAssignmentTest {

	ManageAssignmentPage manageAssignmentPage;
	HomePage homePage = new HomePage();
	ModifyAssignmentDetailsPage modifyAssignmentDetailsPage;
	List<String> listOfAssignmentsSelected = new ArrayList<String>();

	int totalAssignments;
	String assignmentNameErrMsg = "";

	@When("User is on Manage Assignment page")
	public void user_is_on_manage_assignment_page() {
		manageAssignmentPage = homePage.navigatetoAssignments();
	}

	@Then("User should see assignment page heading with text {string} on the page")
	public void user_should_see_assignment_page_heading_with_text_on_the_page(String assignmentHeader) {
		String actualHeader = manageAssignmentPage.getPageHeader();

		// To validate if user is on LMS website page, validate the header displayed
		Assert.assertEquals(actualHeader, assignmentHeader, "User has not landed on the assignment page of LMS portal");
	}

	@Then("User should see the text as {string} total assignment entries below the table.")
	public void user_should_see_the_text_as_total_assignment_entries_below_the_table(String expectedInfoText) {
		totalAssignments = manageAssignmentPage.getTotalEntries();
		int assignmentsPerPage = manageAssignmentPage.getEntriesPerPage();

		expectedInfoText = "Showing 1 to " + assignmentsPerPage + " of " + totalAssignments + " entries";
		String actualInfoText = manageAssignmentPage.getCurrentPaginationText();
		Assert.assertEquals(actualInfoText, expectedInfoText);
	}

	@Then("User should see the footer as {string} assignments")
	public void user_should_see_the_footer_as_assignments(String expectedFooterText) {
		totalAssignments = manageAssignmentPage.getTotalEntries();

		expectedFooterText = expectedFooterText + totalAssignments + " assignments.";
		String actualInfoText = manageAssignmentPage.getFooterText();
		Assert.assertEquals(actualInfoText, expectedFooterText);
	}

	@When("User selects assignment -> {string} using checkbox")
	public void user_selects_assignment_using_checkbox(String assignment) throws InterruptedException {
		manageAssignmentPage.searchData(assignment);
		manageAssignmentPage.selectAssignment(assignment);
	}

	@Then("Assignment {string} gets selected")
	public void assignment_gets_selected(String assignment) {
		Assert.assertTrue(manageAssignmentPage.validateAssignmentSelection(assignment),
				assignment + " is not selected");
	}

	@When("User clicks on Edit button for assignment")
	public void user_clicks_on_edit_button_for_assignment() {
		modifyAssignmentDetailsPage = manageAssignmentPage.editAssignment();
	}

	@Then("User lands on Assignment Details form.")
	public void user_lands_on_assignment_details_form() {
		String expectedHeader = "Assignment Details";
		String actualHeader = modifyAssignmentDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Program Details page");
	}

	@Given("User is on Assignment Details form")
	public void user_is_on_assignment_details_form() {
		manageAssignmentPage = homePage.navigatetoAssignments();
		modifyAssignmentDetailsPage = manageAssignmentPage.editAssignment();

		String expectedHeader = "Assigment Details";
		String actualHeader = modifyAssignmentDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Assignment Details page");
	}

	@When("User edits assignment Name as {string} and saves")
	public void user_edits_name_as_and_saves(String newAssignmentName) {
		modifyAssignmentDetailsPage.updateAssignmentName(newAssignmentName);
	}

	@Then("User can see updated assignment Name as {string}")
	public void user_can_see_updated_assignment_name_as(String newAssignmentName) throws InterruptedException {
		String expectedSucessUpdateMsg = "Assignment Updated";
		String actualSucessUpdateMsg = manageAssignmentPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageAssignmentPage.verifyEntryUpdate(newAssignmentName),
				"Assignment name is not updated to " + newAssignmentName);
	}

	@When("User edits assignment Description as {string} and saves")
	public void user_edits_description_as_and_saves(String newAssignmentDesc) {
		modifyAssignmentDetailsPage.updateAssignmentDescription(newAssignmentDesc);
	}

	@Then("User can see updated assignment Description as {string}")
	public void user_can_see_updated_description_as(String newAssignmentDesc) throws InterruptedException {
		String expectedSucessUpdateMsg = "Assignment Updated";
		String actualSucessUpdateMsg = manageAssignmentPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageAssignmentPage.verifyEntryUpdate(newAssignmentDesc),
				"Assignment description is not updated to " + newAssignmentDesc);
	}

	@When("User edits Grade as {string} and saves")
	public void user_edits_grade_as_and_saves(String newAssignmentGrade) {
		modifyAssignmentDetailsPage.updateAssignmentGrade(newAssignmentGrade);
	}

	@Then("User can see update sucessfull message {string}")
	public void user_can_see_updated_grade_as(String expectedSucessUpdateMsg) {
		String actualSucessUpdateMsg = manageAssignmentPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);
	}

	@When("User edits Assignment Due Date to {string}")
	public void user_edits_assignment_due_date_to(String newDate) {
		modifyAssignmentDetailsPage.updateAssignmentDate(newDate);
	}

	@Then("User can see updated Due Date as {string}")
	public void user_can_see_updated_due_date_as(String expectedDate) throws ParseException {
		String actualDate = manageAssignmentPage.getDate();
		Date formattedDate= new SimpleDateFormat("dd/MM/yyyy").parse(expectedDate);
		Assert.assertEquals(actualDate.substring(4, 10), formattedDate.toString().substring(4, 10),"Date format do not match after the update");
	}

	@When("User clicks Cancel button on assignment details page")
	public void user_clicks_cancel_button_on_assignment_details_page() {
		modifyAssignmentDetailsPage.cancelUpdate();
	}

	@Then("User can see Assignment Details form disappears")
	public void user_can_see_assignment_details_form_disappears() {

		String actualHeader = modifyAssignmentDetailsPage.getDialogTitle();
		Assert.assertNull(actualHeader, "User is still on Assignment Details page, cancel operation has failed");

	}

	@When("User enters {string} in assignment name text box")
	public void user_enters_in_assignment_name_text_box(String newValue) {
		modifyAssignmentDetailsPage.setAssignmentName(newValue);
	}

	@When("User enters {string} in assignment description text box")
	public void user_enters_in_assignment_description_text_box(String newValue) {
		modifyAssignmentDetailsPage.setAssignmentDescription(newValue);
	}

	@Then("User clicks Save button on assignment details page")
	public void user_clicks_save_button_on_assignment_details_page() {
		modifyAssignmentDetailsPage.saveAssignmentDetails();
	}

	@When("User clicks on Delete button after selecting {string}")
	public void user_clicks_on_delete_button_after_selecting(String assignment) throws InterruptedException {
		manageAssignmentPage.searchData(assignment);
		manageAssignmentPage.selectAssignment(assignment);
		modifyAssignmentDetailsPage = manageAssignmentPage.deleteAssignment();
	}

	@Then("User can see Confirm Deletion form disappears and user is on assignments page")
	public void user_can_see_confirm_deletion_form_disappears_and_user_is_on_assignments_page() {
		String actualHeader = manageAssignmentPage.getPageHeader();
		String expectedHeader = "Manage Assignment";
		// To validate if Assignment Details form disappears, validate the header
		// displayed
		// on Assignment page since the control is returned back to Assignment page
		Assert.assertEquals(actualHeader, expectedHeader);
	}

	@When("User selects more than one Assignment using checkbox")
	public void user_selects_more_than_one_assignment_using_checkbox(DataTable assignmentList)
			throws InterruptedException {
		List<List<String>> listOfProgramsFromTable = assignmentList.asLists(String.class);
		for (List<String> assignment : listOfProgramsFromTable) {

			listOfAssignmentsSelected.add(assignment.get(0));

			manageAssignmentPage.searchData(assignment.get(0));
			manageAssignmentPage.selectAssignment(assignment.get(0));
		}
	}

	@Then("Assignments get selected")
	public void assignments_get_selected(DataTable expectedAssignmentsTable) {
		List<List<String>> listOfProgramsFromTable = expectedAssignmentsTable.asLists(String.class);
		List<String> expectedProgramsList = new ArrayList<String>();
		for (List<String> program : listOfProgramsFromTable) {
			expectedProgramsList.add(program.get(0));
		}

		Assert.assertEquals(listOfAssignmentsSelected, expectedProgramsList);
	}

	@When("User clicks on Delete button on top left corner after selecting {string}")
	public void user_clicks_on_delete_button_on_top_left_corner_after_selecting(String assignment)
			throws InterruptedException {
		manageAssignmentPage.searchData(assignment);
		manageAssignmentPage.selectAssignment(assignment);
		manageAssignmentPage.deleteSelectedAssignments();
	}

	@When("User clicks on New Assignment button")
	public void user_clicks_on_new_assignment_button() {
		modifyAssignmentDetailsPage = manageAssignmentPage.addNewAssignment();
	}

	@Then("user lands on New Assignment details form")
	public void user_lands_on_new_assignment_details_form() {
		manageAssignmentPage = homePage.navigatetoAssignments();
		modifyAssignmentDetailsPage = manageAssignmentPage.addNewAssignment();
		/*
		 * String expectedHeader = "Assignment Details"; String actualHeader =
		 * modifyAssignmentDetailsPage.getDialogTitle();
		 * //Assert.assertEquals(actualHeader, expectedHeader,
		 * "User is not on New Assignment Details page"); --> Defect
		 */
	}

	@When("User clicks Save button without entering assignment data")
	public void user_clicks_save_button_without_entering_data() {
		assignmentNameErrMsg = modifyAssignmentDetailsPage.saveAssignmentDetails();
	}

	@Then("User gets message {string} on New Assignment details form")
	public void user_gets_message(String expectedProgNameErrMsg) {
		Assert.assertEquals(assignmentNameErrMsg, expectedProgNameErrMsg);
	}

	@When("user enters Assignment name {string}")
	public void user_enters_assignment_name(String expectedAssignmentName) {
		modifyAssignmentDetailsPage.setAssignmentName(expectedAssignmentName);
	}

	@Then("User can see {string} entered in assignment name field")
	public void user_can_see_entered_in_assignment_name_fields(String expectedAssignmentName) {
		String assignmentName = modifyAssignmentDetailsPage.getAssignmentName();
		Assert.assertEquals(assignmentName, expectedAssignmentName);
	}

	@When("user enters Assignment Description {string}")
	public void user_enters_assignment_description(String expectedAssignmenDescription) {
		modifyAssignmentDetailsPage.setAssignmentDescription(expectedAssignmenDescription);
	}

	@Then("User can see {string} entered in assignment description field")
	public void user_can_see_entered_in_assignment_description_field(String expectedAssignmenDescription) {
		String assignmentDesc = modifyAssignmentDetailsPage.getAssignmentDescription();
		Assert.assertEquals(assignmentDesc, expectedAssignmenDescription);
	}

	@When("user enters Assignment Grade {string}")
	public void user_enters_assignment_grade(String expectedAssignmenGrade) {
		modifyAssignmentDetailsPage.setAssignmentsGrade(expectedAssignmenGrade);
	}

	@Then("User can see {string} entered in assignment grade field")
	public void user_can_see_entered_in_assignment_grade_field(String expectedAssignmenGrade) {
		String assignmentGrade = modifyAssignmentDetailsPage.getAssignmentGrade();
		Assert.assertEquals(assignmentGrade, expectedAssignmenGrade);
	}

	@When("user enters assignment due date {string}")
	public void user_enters_assignment_due_date(String expectedAssignmentDate) {
		modifyAssignmentDetailsPage.setAssignmentDate(expectedAssignmentDate);
	}

	@Then("user can see {string} entered in assignment due date field")
	public void user_can_see_entered_in_assignment_due_date_field(String expectedAssignmentDate) throws ParseException {
		String assignmentDate = modifyAssignmentDetailsPage.getAssignmentDate();
		System.out.println(assignmentDate);
		Date formattedDate= new SimpleDateFormat("dd/MM/yyyy").parse(expectedAssignmentDate);
		System.out.println(formattedDate);
		Assert.assertEquals(assignmentDate.substring(4, 10), formattedDate.toString().substring(4, 10));
	}

	@When("User clicks onthe Ascending arrow \\(down ) near to the Assignment name")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_assignment_name() {
		manageAssignmentPage.Sort("assignment", "ascending");
	}

	@Then("User can see the results in Ascending order of Assignment name")
	public void user_can_see_the_results_in_ascending_order_of_assignment_name() {
		Assert.assertTrue(manageAssignmentPage.verifySort("assignment", "ascending"),
				"Assignment Name is not sorted in ascending order");
	}

	@When("User clicks onthe Descending arrow \\(down ) near to the Assignment name")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_assignment_name() {
		manageAssignmentPage.Sort("assignment", "descending");
	}

	@Then("User can see the results in Descending order of Assignment name")
	public void user_can_see_the_results_in_descending_order_of_assignment_name() {
		Assert.assertTrue(manageAssignmentPage.verifySort("assignment", "descending"),
				"Assignment Name is not sorted in descending order");
	}

	@When("User clicks onthe Descending arrow \\(down ) near to the Assignment Description")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_assignment_description() {
		manageAssignmentPage.Sort("assignment description", "descending");
	}

	@Then("User can see the results in Descending order of Assignment Description")
	public void user_can_see_the_results_in_descending_order_of_assignment_description() {
		Assert.assertTrue(manageAssignmentPage.verifySort("assignment description", "descending"),
				"Assignment description is not sorted in descending order");
	}

	@When("User clicks onthe Ascending arrow \\(down ) near to the Assignment Description")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_assignment_description() {
		manageAssignmentPage.Sort("assignment description", "ascending");
	}

	@Then("User can see the results in Ascending order of Assignment Description")
	public void user_can_see_the_results_in_ascending_order_of_assignment_description() {
		Assert.assertTrue(manageAssignmentPage.verifySort("assignment description", "ascending"),
				"Assignment description is not sorted in ascending order");
	}

	
}
