package com.lms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.LMSBasePage;

public class ManageAssignmentPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(xpath = "//div[contains(text(),'Manage Assignment')]")
	WebElement lblAssignmentHeader;

	@FindBy(xpath = "//tbody/tr")
	List<WebElement> totalEntries;

	@FindBy(css = ".p-paginator-current")
	WebElement lblPaginationText; // Showing _ to _ of _ entries

	@FindBy(xpath = "//button[contains(@class,'p-paginator-next')]")
	WebElement nextPaginator;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-first')]")
	WebElement firstPaginator;

	@FindBy(css = ".p-ai-center")
	WebElement lblFooterText; // In total there are _ assignments.

	@FindBy(xpath = "//td/following-sibling::td//button[contains(@icon,'pi-pencil')]")
	WebElement btnEdit;

	@FindBy(xpath = "//th[@psortablecolumn='assignmentName']")
	WebElement assignmentHeader;

	@FindBy(xpath = "//th[@psortablecolumn='assignmentDescription']")
	WebElement assignmentDescHeader;
	
	@FindBy(xpath = "//tbody//button[contains(@class,'p-button-danger')]")
	WebElement btnDeleteAssignment;
	
	public ManageAssignmentPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getPageHeader() {
		return lblAssignmentHeader.getText();
	}

	public void selectAssignment(String assignment) {
		// Select first found entry
		WebElement checkBox = driver.findElement(
				By.xpath("//td[text()='" + assignment + "']/preceding-sibling::td//div[@role='checkbox']"));
		if (checkBox != null)
			checkBox.click();

	}

	public boolean validateAssignmentSelection(String assignment) {
		WebElement checkBox = driver.findElement(
				By.xpath("//td[text()='" + assignment + "']/preceding-sibling::td//div[@role='checkbox']"));

		String checkBoxAttribute = checkBox.getAttribute("aria-checked");
		return checkBoxAttribute.equalsIgnoreCase("true");
	}

	public ModifyAssignmentDetailsPage editAssignment() {
		// Select first found entry
		btnEdit.click();
		return new ModifyAssignmentDetailsPage();
	}

	public String getDate() {
		// check date of first row since we are updating the same
		int dateColumn = 4;
		WebElement updatedDate = driver.findElement(By.xpath("//tr/td[" + dateColumn + "]"));
		return updatedDate.getText();
	}

	public void Sort(String category, String order) {

		switch (category) {
		case "assignment":
			assignmentHeader.click(); // single click - ascending
			if (order.equals("descending"))
				assignmentHeader.click(); // double click - descending
			break;
		case "assignment description":
			assignmentDescHeader.click(); // single click - ascending
			if (order.equals("descending"))
				assignmentDescHeader.click(); // double click - descending
			break;
		}

	}

	public boolean verifySort(String category, String order) {
		String sortOrder = "";
		boolean isSorted = false;
		switch (category) {
		case "assignment":
			sortOrder = assignmentHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "assignment description":
			sortOrder = assignmentDescHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		}
		return isSorted;
	}

	public ModifyAssignmentDetailsPage deleteAssignment() {
		// Select first found entry
		btnDeleteAssignment.click();
		return new ModifyAssignmentDetailsPage();
	}
	
	public ModifyAssignmentDetailsPage deleteSelectedAssignments() {
		// Select first found entry
		btnDelete.click();
		return new ModifyAssignmentDetailsPage();
	}
	
	public ModifyAssignmentDetailsPage addNewAssignment() {
		btnNewEntry.click();
		return new ModifyAssignmentDetailsPage();
	}
	
	
}
