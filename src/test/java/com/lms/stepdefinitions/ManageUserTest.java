package com.lms.stepdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.lms.base.LMSBasePage;
import com.lms.pages.HomePage;
import com.lms.pages.ManageUserPage;
import com.lms.pages.ModifyUserDetailsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageUserTest extends LMSBasePage {

	ManageUserPage manageUserPage;
	HomePage homePage = new HomePage();
	ModifyUserDetailsPage modifyUserDetailsPage;
	int totalEntries;

	@When("User clicks the Tab {string}")
	public void user_clicks_the_tab(String string) {
		manageUserPage = homePage.navigatetoUsers();
	}

	@Then("User is on the Manage user page")
	public void user_is_on_the_manage_user_page() {
		manageUserPage = homePage.navigatetoUsers();
		String actualHeader = manageUserPage.getPageHeader();
		String expectedHeader = "Manage User";
		Assert.assertEquals(actualHeader, expectedHeader, "User has not landed on the users page of LMS portal");
	}

	@Then("User should see the page heading as {string}")
	public void user_should_see_the_page_heading_as(String expectedHeader) {
		String actualHeader = manageUserPage.getPageHeader();
		Assert.assertEquals(actualHeader, expectedHeader, "User has not landed on the users page of LMS portal");
	}

	@Then("Verify that next link is disabled if Manage user table has less less than or equal to {int} rows")
	public void verify_that_next_link_is_disabled_if_manage_user_table_has_less_less_than_or_equal_to_rows(
			Integer entriesCnt) {
		Assert.assertTrue(manageUserPage.verifyNextLink(entriesCnt));
	}

	@Then("Verify that next link is enabled if Manage user table has more than to {int} rows")
	public void verify_that_next_link_is_enabled_if_manage_user_table_has_more_than_to_rows(Integer entriesCnt) {
		Assert.assertTrue(manageUserPage.verifyNextLink(entriesCnt));
	}

	@Given("User is on the page number {string} of Manage User Page")
	public void user_is_on_the_page_number_of_manage_user_page(String prevPage) {
		manageUserPage = homePage.navigatetoUsers();
		manageUserPage.goToPage(prevPage);
	}

	@When("User is on last page of Manage User Page")
	public void user_is_on_last_page_of_manage_user() {
		manageUserPage = homePage.navigatetoUsers();
		navigateToLastPage();
	}

	@Then("User should see the text {string} below the user table. \"")
	public void user_should_see_the_text_below_the_user_table(String expectedText) {
		totalEntries = getTotalEntries();
		int entriesPerPage = getEntriesPerPage();

		expectedText = "Showing 1 to " + entriesPerPage + " of " + totalEntries + " entries";
		String actualInfoText = getCurrentPaginationText();
		Assert.assertEquals(actualInfoText, expectedText);
	}

	@Then("User should see the table footer as {string}")
	public void user_should_see_the_table_footer_as(String expectedFooterText) {
		String actualInfoText = getFooterText();
		Assert.assertEquals(actualInfoText, expectedFooterText);
	}

	@Then("User should see the button with text {string}")
	public void user_should_see_the_button_with_text(String expectedBtnText) {
		Assert.assertEquals(getNewEntryLabel(), expectedBtnText);
	}

	@When("User clicks Add New User button")
	public void user_clicks_add_new_user_button() {
		modifyUserDetailsPage = manageUserPage.addNewUser();
	}

	@Then("User should see the {string} dialog box")
	public void user_should_see_the_dialog_box(String expectedHeader) {
		String actualHeader = getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on User Details page");
	}

	@Then("User should see the user table header with options listed")
	public void user_should_see_the_user_table_header_with_options_listed(DataTable userTableHeaders) {
		List<List<String>> listOfHeadersFromTable = userTableHeaders.asLists(String.class);
		List<String> expectedHeaders = new ArrayList<String>();
		for (List<String> header : listOfHeadersFromTable) {
			expectedHeaders.add(header.get(0));
		}
		List<String> actualHeaders = manageUserPage.getUserTableHeaders();
		Assert.assertEquals(actualHeaders, expectedHeaders);

	}

	@When("User clicks sort icon in table header {string} to sort in Ascending order")
	public void user_clicks_sort_icon_in_table_header(String header) {
		manageUserPage.Sort(header, "ascending");
	}

	@Then("User can see the results in Ascending order of {string}")
	public void user_can_see_the_results_in_ascending_order(String header) {
		Assert.assertTrue(manageUserPage.verifySort(header, "ascending"));
	}

	@When("User clicks sort icon in table header {string} to sort in Descending order")
	public void user_clicks_sort_icon_in_table_header_Descending(String header) {
		manageUserPage.Sort(header, "descending");
	}

	@Then("User can see the results in Descending order of {string}")
	public void user_can_see_the_results_in_ascending_order_Descending(String header) {
		Assert.assertTrue(manageUserPage.verifySort(header, "descending"));
	}

	@When("User checks empty checkbox in user table header")
	public void user_checks_empty_checkbox_in_user_table_header() {
		manageUserPage.checkHeaderCheckBox();
	}

	@Then("Check box in all the rows of user table should be checked")
	public void check_box_in_all_the_rows_of_user_table_should_be_checked() {
		Assert.assertTrue(manageUserPage.checkIfAllCheckBoxChecked());
	}

	@When("User checks {string} in user table")
	public void user_checks_a_row_in_user_table(String user) {
		manageUserPage.selectUser(user);
	}

	@Then("Delete icon at the top left corner of the user table enabled")
	public void delete_icon_at_the_top_left_corner_of_the_user_table_enabled() {
		Assert.assertTrue(!isDeleteDisabled(), "Delete button on top left corner of table is not enabled page load");
	}

	@Then("User clicks the delete icon at the top left corner of user table")
	public void user_clicks_the_delete_icon_at_the_top_left_corner_of_user_table() {
		modifyUserDetailsPage = manageUserPage.deleteSelectedUser();
	}

	@Then("User can see Confirm Deletion form disappears and user is back on manage user page")
	public void user_can_see_confirm_deletion_form_disappears_and_user_is_back_on_manage_user_page() {
		String actualHeader = manageUserPage.getPageHeader();
		String expectedHeader = "Manage User";

		Assert.assertEquals(actualHeader, expectedHeader);
	}

	@Then("User clicks close\\(x) button")
	public void user_clicks_close_x_button() {
		closeDialog();
	}

	@When("User clicks on Edit button for user")
	public void user_clicks_on_edit_button_for_user() {
		modifyUserDetailsPage = manageUserPage.editUser();
	}

	@When("User clicks on Delete button for user")
	public void user_clicks_on_delete_button_for_user() {
		modifyUserDetailsPage = manageUserPage.deleteUser();
	}

	@When("User clicks ID in any row")
	public void user_clicks_id_in_any_row() {
		modifyUserDetailsPage = manageUserPage.retrieveUserInfo();
	}

	@Then("User details dialog box displayed with user information")
	public void user_details_dialog_box_displayed_with_user_information() {
		String actualHeader = getDialogTitle();
		String expectedHeader = "User Details";
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on User Details page");
	}

	@Then("User should see a button with text Cancel in user details window")
	public void user_should_see_a_button_with_text_cancel_in_user_details_window() {
		Assert.assertTrue(modifyUserDetailsPage.isCancelAvailable());
	}

	@Given("User is on User details window")
	public void user_is_on_user_details_window() {
		manageUserPage = homePage.navigatetoUsers();
		modifyUserDetailsPage = manageUserPage.addNewUser();

		String actualHeader = getDialogTitle();
		String expectedHeader = "User Details";
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on User Details page");
	}

	@When("User clicks A cancel button")
	public void user_clicks_a_cancel_button() {
		modifyUserDetailsPage.cancelUserEdit();
	}

	@Then("User details window should be closed")
	public void user_details_window_should_be_closed() {
		String actualHeader = manageUserPage.getPageHeader();
		String expectedHeader = "Manage User";

		Assert.assertEquals(actualHeader, expectedHeader);
	}

	@Then("User should see a cancel\\(x) in user details window")
	public void user_should_see_a_cancel_x_in_user_details_window() {
		Assert.assertTrue(modifyUserDetailsPage.isCloseXAvailable());
	}

	@Then("User should see a button with text Submit in user details window")
	public void user_should_see_a_button_with_text_submit_in_user_details_window() {
		Assert.assertTrue(modifyUserDetailsPage.isSubmitAvailable());
	}

	@Then("User should see the placeholders for user data")
	public void user_should_see_the_placeholders_for_user_data(DataTable expectedLabelsFromTable) {
		List<List<String>> listOflabelsFromTable = expectedLabelsFromTable.asLists(String.class);
		List<String> actualLabels = modifyUserDetailsPage.getUserDetailsPageLabels();

		for (List<String> label : listOflabelsFromTable) {
			Assert.assertTrue(actualLabels.contains(label.get(0)));
		}
	}

	@When("User clicks the drop down icon for state")
	public void user_clicks_the_drop_down_icon_for_state() {
		modifyUserDetailsPage.clickStateDropdown();
	}

	@Then("User should be able to select state from the drop down menu")
	public void user_should_be_able_to_select_state_from_the_drop_down_menu() {
		// no dropdown appears
		Assert.fail();
	}

	@When("User clicks the drop down icon for User Role")
	public void user_clicks_the_drop_down_icon_for_user_role() {
		// no dropdown appears
		Assert.fail();
	}

	@Then("User should be able to select user role from the drop down menu")
	public void user_should_be_able_to_select_user_role_from_the_drop_down_menu() {
		// no dropdown appears
		Assert.fail();
	}

	@When("User clicks save button")
	public void user_clicks_save_button() throws InterruptedException {
		modifyUserDetailsPage.submit();
	}

	@Then("User should see a message {string}")
	public void user_should_see_a_message_mandatory_fields_cannot_be_empty(String expectedMsg) {
		// No messages displayed
		Assert.fail();
	}

	@Then("User should see the add button with text {string}")
	public void user_should_see_the_add_button_with_text(String expectedLabel) {
		Assert.assertTrue(modifyUserDetailsPage.isAddAddressAvailable(expectedLabel));
	}

	@When("User clicks the button Add C\\/O, Apt, Suite, Unit")
	public void user_clicks_the_button() {
		modifyUserDetailsPage.addAddress();
	}

	@Then("User should see the input field with Label {string}")
	public void user_should_see_the_input_field_with_label(String expectedLabel) {
		Assert.assertTrue(modifyUserDetailsPage.isAddress2TextAvailable(expectedLabel));
	}

	@When("User enters mandatory details")
	public void user_enters_mandatory_details(DataTable dataTable) {
		List<Map<String,String>> userDetails=dataTable.asMaps();
		modifyUserDetailsPage.enterUserDetails(userDetails);
	}

	@When("User clicks Submit button")
	public void user_clicks_submit_button() throws InterruptedException {
		modifyUserDetailsPage.submit();
	}

	@Then("New User Should be Saved.")
	public void new_user_should_be_saved(DataTable dataTable) {
		List<Map<String,String>> userDetails=dataTable.asMaps();
		modifyUserDetailsPage.verifyUserDetails(userDetails);
	}

	@When("User clicks postal code input field")
	public void user_clicks_postal_code_input_field()  {
		modifyUserDetailsPage.clickPostalCode();
	}
	
	@Then("User should see the input Number arrows in the postal code input field")
	public void user_should_see_the_input_number_arrows_in_the_postal_code_input_field() {
		Assert.assertTrue(modifyUserDetailsPage.isArrowsShown());
	}

}
