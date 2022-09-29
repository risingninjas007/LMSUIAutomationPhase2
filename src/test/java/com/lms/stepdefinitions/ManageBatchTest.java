package com.lms.stepdefinitions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.lms.pages.HomePage;
import com.lms.pages.ManageBatchPage;
import com.lms.pages.ModifyBatchDetailsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageBatchTest {

	ManageBatchPage manageBatchPage;
	HomePage homePage = new HomePage();
	String actualBatchHeading;
	ModifyBatchDetailsPage modifyBatchDetailsPage;
	String batchNameErrMsg = "";
	List<String> listOfBatchesSelected = new ArrayList<String>();

	@When("User is on Batch page")
	public void user_is_on_batch_page() {
		manageBatchPage = homePage.navigateToBatch();
	}

	@Then("User should see a heading with text {string} on the page")
	public void user_should_see_a_heading_with_text_on_the_page(String expectedBatchHeading) {

		actualBatchHeading = manageBatchPage.getbatchPageHeading();
		Assert.assertEquals(actualBatchHeading, expectedBatchHeading, "Batch page heading does not match");
	}

	@When("User clicks on Edit button for batch")
	public void user_clicks_on_edit_button_for_batch() {
		modifyBatchDetailsPage = manageBatchPage.editBatch();
	}

	@Then("User lands on Batch Details form.")
	public void user_lands_on_batch_details_form() {
		String expectedHeader = "Batch Details";
		String actualHeader = modifyBatchDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Batch Details page");
	}

	@Given("User is on New Batch Details form")
	public void user_is_on_new_batch_details_form() {

		manageBatchPage = homePage.navigateToBatch();
		modifyBatchDetailsPage = manageBatchPage.addNewBatchAndNavigateToBatchDetails();
	}

	@Given("User is on Batch details page")
	public void user_is_on_batch_details_page() {
		manageBatchPage = homePage.navigateToBatch();
		modifyBatchDetailsPage = manageBatchPage.editBatch();
		String expectedHeader = "Batch Details";
		String actualHeader = modifyBatchDetailsPage.getDialogTitle();
		Assert.assertEquals(actualHeader, expectedHeader, "User is not on Batch Details page");
	}

	@When("User edits batch Name to {string} and selects the Save button")
	public void user_edits_batch_name_to_and_selects_the_save_button(String newBatchName) {
		modifyBatchDetailsPage.updateBatchName(newBatchName);
	}

	@Then("User can see updated batch Name as {string}")
	public void user_can_see_updated_batch_name_as(String newBatchName) throws InterruptedException {
		String expectedSucessUpdateMsg = "batch Updated";
		String actualSucessUpdateMsg = manageBatchPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageBatchPage.verifyEntryUpdate(newBatchName),
				"Batch name is not updated to " + newBatchName);

	}

	@When("user edits batch description to {string} and selects the Save button")
	public void user_edits_batch_description_to_and_selects_the_save_button(String newBatchDesc) {
		modifyBatchDetailsPage.updateBatchDescription(newBatchDesc);
	}

	@Then("User can see edited batch descricption as {string}")
	public void user_can_see_edited_batch_descricption_as(String newBatchDesc) throws InterruptedException {
		String expectedSucessUpdateMsg = "batch Updated";
		String actualSucessUpdateMsg = manageBatchPage.verifyEntryUpdate();
		Assert.assertEquals(actualSucessUpdateMsg, expectedSucessUpdateMsg);

		Assert.assertTrue(manageBatchPage.verifyEntryUpdate(newBatchDesc),
				"Batch description is not updated to " + newBatchDesc);
	}

	@When("user edits program name")
	public void user_edits_program_name() {
		modifyBatchDetailsPage.updateProgramName();
	}

	@Then("user can see edited program name")
	public void user_can_see_edited_program_name() {
	}

	@When("user changes status to {string} and selects the the Save button")
	public void user_changes_status_to_and_selects_the_the_save_button(String newStatus) {
		modifyBatchDetailsPage.updateBatchStatus(newStatus);
	}

	@Then("User can see updated Status as {string}")
	public void user_can_see_updated_status_as(String newStatus) {
		Assert.assertNotNull(modifyBatchDetailsPage.verifyBatchRecordsColumnTxt(newStatus, 4));
	}

	@When("user edits No of classes to {string} and selects the the Save button")
	public void user_edits_no_of_classes_to_and_selects_the_the_save_button(String numOfClasses)
			throws InterruptedException {
		modifyBatchDetailsPage.updateBatchNumberOfClasses(numOfClasses);
	}

	@Then("user can see updated No of classes as {string}")
	public void user_can_see_updated_no_of_classes_as(String numOfClasses) {
		Assert.assertTrue(modifyBatchDetailsPage.verifyBatchRecordsColumnTxt(numOfClasses, 5));
	}

	@And("User can see {string} alert")
	public void user_can_see_alert(String expectedAlertMsg) {
		String actualAlertMsg = modifyBatchDetailsPage.verifySuccessAlertMsg();
		Assert.assertTrue(expectedAlertMsg.contains(actualAlertMsg), " Batch updation fail ");
	}

	@When("user clicks cancel button")
	public void user_clicks_cancel_button() {
		modifyBatchDetailsPage.clickCancelEditRecord();
	}

	@Then("User can see batch page")
	public void user_can_see_batch_page() {

		String actualBatchHeading = manageBatchPage.getbatchPageHeading();
		Assert.assertEquals(actualBatchHeading, "Manage Batch", "Batch page heading does not match");

	}

	@When("User clicks Save button without entering batch data")
	public void user_clicks_save_button_without_entering_batch_data() {
		batchNameErrMsg = modifyBatchDetailsPage.saveBatchDetails();
	}

	@Then("User gets message {string} for Batch")
	public void user_gets_message(String expectedProgNameErrMsg) {
		Assert.assertEquals(batchNameErrMsg, expectedProgNameErrMsg);
	}

	@Then("User clicks Save button for Batch")
	public void user_clicks_save_button_for_batch() {
		modifyBatchDetailsPage.saveBatchDetails();
	}

	@When("User selects more than one Batch using checkbox")
	public void user_selects_more_than_one_batch_using_checkbox(DataTable programsList) throws InterruptedException {

		List<List<String>> listOfBatchesFromTable = programsList.asLists(String.class);

		for (List<String> batch : listOfBatchesFromTable) {

			listOfBatchesSelected.add(batch.get(0));

			manageBatchPage.enterDataInSearchBox(batch.get(0));
			manageBatchPage.selectBatch(batch.get(0));
		}
	}

	@Then("batches get selected")
	public void batches_get_selected(DataTable expectedBatchesTable) {

		List<List<String>> listOfBatchesFromTable = expectedBatchesTable.asLists(String.class);

		List<String> expectedBatchesList = new ArrayList<String>();

		for (List<String> program : listOfBatchesFromTable) {
			expectedBatchesList.add(program.get(0));
		}

		Assert.assertEquals(listOfBatchesSelected, expectedBatchesList);
	}

	@When("User selects a batch {string} using checkbox")
	public void user_selects_a_batch_using_checkbox(String batch) throws InterruptedException {
		manageBatchPage.searchData(batch);
		manageBatchPage.selectBatch(batch);
	}

	@When("User clicks on Delete button on top left corner of batch page")
	public void user_clicks_on_delete_button_on_top_left_corner_of_batch_page() {
		modifyBatchDetailsPage = manageBatchPage.deleteSelectedBatch();
	}

	@Then("User searches {string} and can see Batch is not deleted.")
	public void user_searches_and_can_see_batch_is_not_deleted(String batchName) throws InterruptedException {

		manageBatchPage.enterDataInSearchBox(batchName);
		Assert.assertTrue(manageBatchPage.validateSearchResultsByEntries(batchName), "Bath name not found");

	}

	@When("User clicks A New Batch button")
	public void user_clicks_a_new_batch_button() {
		modifyBatchDetailsPage = manageBatchPage.addNewBatchAndNavigateToBatchDetails();
	}

	@When("User enters {string} in batch name text box")
	public void user_enters_in_batch_name_text_box(String expectedBatchName) {

		modifyBatchDetailsPage.setBatchName(expectedBatchName);
	}

	@Then("User can see {string} entered in batch name text box")
	public void user_can_see_entered_in_batch_name_text_box(String expectedBatchName) {

		String batchName = modifyBatchDetailsPage.getBatchName();
		Assert.assertEquals(batchName, expectedBatchName, "Batch name does not match");
	}

	@When("User is on last page of Manage Batch")
	public void user_is_on_last_page_of_manage_batch() {

		manageBatchPage = homePage.navigateToBatch();
		manageBatchPage.navigateToLastPage();
	}

	@When("User enters {string} in batch description text box")
	public void user_enters_in_batch_description_text_box(String expectedBatchDesc) {

		modifyBatchDetailsPage.setBatchDesc(expectedBatchDesc);
	}

	@Then("User can see {string} entered in batch description text box")
	public void user_can_see_entered_in_batch_description_text_box(String expectedBatchDesc) {

		String batchDesc = modifyBatchDetailsPage.getBatchDesc();
		Assert.assertEquals(batchDesc, expectedBatchDesc);
	}

	@When("User selects batch status -> {string} using radiobutton")
	public void user_selects_using_radiobutton(String expectedStatus) {

		modifyBatchDetailsPage.setBatchStatus(expectedStatus);
	}

	@Then("User can see {string} batch status selected")
	public void user_can_see_status_selected(String expectedStatus) {

		String actualBatchStatus = modifyBatchDetailsPage.getBatchStatus();
		Assert.assertEquals(actualBatchStatus.toLowerCase(), expectedStatus.toLowerCase(),
				"Batch status does not match");
	}

	@When("User enters {string} in number of classes text box")
	public void user_enters_in_number_of_classes_text_box(String batchClassesCount) {
		modifyBatchDetailsPage.setBatchClassesCount(batchClassesCount);
	}

	@Then("User can see {string} entered in number of classes text box")
	public void user_can_see_entered_in_number_of_classes_text_box(String expectedBatchClassesCount) {

		String actualBatchClassesCount = modifyBatchDetailsPage.getBatchClassesCount();
		Assert.assertEquals(actualBatchClassesCount, expectedBatchClassesCount, "Batch classes count does not match");

	}

	@When("User chooses {string} from dropdown")
	public void user_chooses_from_dropdown(String expectedProgramName) throws InterruptedException {
		modifyBatchDetailsPage.setProgramName(expectedProgramName);
	}

	@When("User clicks onthe Ascending arrow \\(down) near to the Batch name")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_batch_name() {
		manageBatchPage.Sort("batch name", "ascending");
	}

	@Then("User can see the results in Ascending order of Batch name")
	public void user_can_see_the_results_in_ascending_order_of_batch_name() {
		Assert.assertTrue(manageBatchPage.verifySort("batch name", "ascending"),
				"Batch Names are not sorted in ascending order");
	}

	@When("User clicks onthe Ascending arrow \\(down) near to the Batch Description")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_batch_description() {
		manageBatchPage.Sort("batch description", "ascending");
	}

	@Then("User can see the results in Ascending order of Batch Description")
	public void user_can_see_the_results_in_ascending_order_of_batch_description() {
		Assert.assertTrue(manageBatchPage.verifySort("batch description", "ascending"),
				"Batch Descriptions are not sorted in ascending order");
	}

	@When("User clicks onthe Ascending arrow \\(down) near to the Batch Status")
	public void user_clicks_onthe_ascending_arrow_down_near_to_the_batch_status() {
		manageBatchPage.Sort("batch status", "ascending");
	}

	@Then("User can see the results in Ascending order of Batch Status")
	public void user_can_see_the_results_in_ascending_order_of_batch_status() {
		Assert.assertTrue(manageBatchPage.verifySort("batch status", "ascending"),
				"Batch Status is not sorted in ascending order");
	}

	@When("User clicks onthe Descending arrow \\(down) near to the Batch name")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_batch_name() {
		manageBatchPage.Sort("batch name", "descending");
	}

	@Then("User can see the results in Descending order of Batch name")
	public void user_can_see_the_results_in_descending_order_of_batch_name() {
		Assert.assertTrue(manageBatchPage.verifySort("batch name", "descending"),
				"Batch Names are not sorted in descending order");
	}

	@When("User clicks onthe Descending arrow \\(down) near to the Batch Description")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_batch_description() {
		manageBatchPage.Sort("batch description", "descending");
	}

	@Then("User can see the results in Descending order of Batch Description")
	public void user_can_see_the_results_in_descending_order_of_batch_description() {
		Assert.assertTrue(manageBatchPage.verifySort("batch description", "descending"),
				"Batch Descriptions are not sorted in descending order");
	}

	@When("User clicks onthe Descending arrow \\(down) near to the Batch Status")
	public void user_clicks_onthe_descending_arrow_down_near_to_the_batch_status() {
		manageBatchPage.Sort("batch status", "descending");
	}

	@Then("User can see the results in Descending order of Batch Status")
	public void user_can_see_the_results_in_descending_order_of_batch_status() {
		Assert.assertTrue(manageBatchPage.verifySort("batch status", "descending"),
				"Batch Status is not sorted in descending order");
	}

}
