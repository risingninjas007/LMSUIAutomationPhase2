package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lms.base.LMSBasePage;

public class ManageBatchPage extends LMSBasePage {
	WebDriver driver;

	@FindBy(xpath = "//div[contains(text(),'Manage Batch')]")
	WebElement batchPageHeading;

	@FindBy(id = "new")
	WebElement btnAddNewBatch;

	@FindBy(css = ".p-inputtext")
	WebElement txtSearchBox;

	@FindBy(xpath = "//th[@psortablecolumn='batchName']")
	WebElement batchHeader;

	@FindBy(xpath = "//th[@psortablecolumn='batchDescription']")
	WebElement batchDescHeader;

	@FindBy(xpath = "//th[@psortablecolumn='batchStatus']")
	WebElement batchStatusHeader;

	public ManageBatchPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getbatchPageHeading() {

		return batchPageHeading.getText();
	}

	public ModifyBatchDetailsPage editBatch() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btnEdit.click();
		return new ModifyBatchDetailsPage();
	}

	public ModifyBatchDetailsPage addNewBatchAndNavigateToBatchDetails() {
		btnAddNewBatch.click();
		return new ModifyBatchDetailsPage();
	}

	public String verifyEntryUpdate() {
		wait.until(ExpectedConditions.visibilityOf(lblSucessfulUpdate));
		return lblSucessfulUpdate.getText();
	}

	public boolean verifyEntryUpdate(String newName) throws InterruptedException {
		searchData(newName);
		boolean isUpdated = loopThroughEntries(newName);
		return isUpdated;
	}

	public void enterDataInSearchBox(String batchName) throws InterruptedException {

		txtSearchBox.clear();
		txtSearchBox.sendKeys(batchName);
		Thread.sleep(1000);
	}

	public void selectBatch(String batchName) {
		// Select first found entry
		WebElement checkBox = driver
				.findElement(By.xpath("//td[text()='" + batchName + "']/preceding-sibling::td//div[@role='checkbox']"));
		if (checkBox != null)
			checkBox.click();
	}

	public ModifyBatchDetailsPage deleteSelectedBatch() {
		// Select first found entry
		btnDelete.click();
		return new ModifyBatchDetailsPage();
	}

	public void Sort(String category, String order) {

		switch (category) {
		case "batch name":
			batchHeader.click(); // single click - ascending
			if (order.equals("descending"))
				batchHeader.click(); // double click - descending
			break;
		case "batch description":
			batchDescHeader.click(); // single click - ascending
			if (order.equals("descending"))
				batchDescHeader.click(); // double click - descending
			break;
		case "batch status":
			batchStatusHeader.click(); // single click - ascending
			if (order.equals("descending"))
				batchStatusHeader.click(); // double click - descending
			break;
		}

	}

	public boolean verifySort(String category, String order) {
		String sortOrder = "";
		boolean isSorted = false;
		switch (category) {
		case "batch name":
			sortOrder = batchHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "batch description":
			sortOrder = batchDescHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "batch status":
			sortOrder = batchStatusHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		}
		return isSorted;
	}

}
