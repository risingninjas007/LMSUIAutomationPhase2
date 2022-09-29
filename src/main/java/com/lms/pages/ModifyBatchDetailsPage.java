package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lms.base.LMSBasePage;

public class ModifyBatchDetailsPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(css = ".p-dialog-title")
	WebElement lblDialogTitle;

	@FindBy(id = "batchName")
	WebElement txtBatchName;

	@FindBy(id = "batchDescription")
	WebElement txtBatchDesc;

	@FindBy(xpath = "//button[@label='Save']")
	WebElement btnSave;

	@FindBy(xpath = "//div[@role='button']")
	WebElement dropdownlist;

	@FindBy(xpath = "//li[@role='option']//span")
	WebElement dropdownItem;

	@FindBy(id = "batchNoOfClasses")
	WebElement batchNoOfClassesTxt;

	@FindBy(xpath = "//div/p-radiobutton[@ng-reflect-input-id='ACTIVE']")
	WebElement radioBtnActive;

	@FindBy(xpath = "//div/p-radiobutton[@ng-reflect-input-id='INACTIVE']")
	WebElement radioBtnInactive;

	@FindBy(xpath = "//button[@label='Cancel']")
	WebElement btnCancel;

	@FindBy(id = "batchNoOfClasses")
	WebElement txtBatchNoOfClasses;
	
	@FindBy(xpath = "//input[@id='batchName']/following-sibling::small")
	WebElement batchNameErrMsg;

	public ModifyBatchDetailsPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getDialogTitle() {
		return lblDialogTitle.getText();
	}

	public void updateBatchName(String newBatchName) {
		setBatchName(newBatchName);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setBatchName(String newBatchName) {
		txtBatchName.clear();
		txtBatchName.sendKeys(newBatchName);
	}

	public void updateBatchDescription(String newBatchDesc) {
		setBatchDescription(newBatchDesc);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setBatchDescription(String newBatchDesc) {
		txtBatchDesc.clear();
		txtBatchDesc.sendKeys(newBatchDesc);
	}

	public void updateProgramName() {

		dropdownlist.click();
		dropdownItem.click();
		btnSave.click();

	}

	public void updateBatchStatus(String status) {
		setBatchStatus(status);
		ScrollTo(btnSave);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setBatchStatus(String status) {
		switch (status.toLowerCase()) {
		case "active":
			radioBtnActive.click();
			break;
		case "inactive":
			radioBtnInactive.click();
			break;
		}
	}

	public String getBatchStatus() {
		String programStatus = radioBtnActive.getAttribute("ng-reflect-model").equalsIgnoreCase("Active") ? "Active"
				: "Inactive";
		;
		return programStatus;
	}

	public boolean verifyBatchRecordsColumnTxt(String searchText, int BatchCellNumber) {
		String batchData = "";

		for (int i = 1; i <= totalEntries.size(); i++) {
			batchData = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + BatchCellNumber + "]")).getText();
			if (doesContain(batchData, searchText)) {
				return true;
			}
		}
		return false;

	}

	public void updateBatchNumberOfClasses(String numOfClasses) throws InterruptedException {

		batchNoOfClassesTxt.clear();
		batchNoOfClassesTxt.sendKeys(numOfClasses);

		btnSave.click();
		Thread.sleep(3000);
	}

	public String verifySuccessAlertMsg() {
		String successAlertMsg = lblSucessfulUpdateSummary.getText() + " " + lblSucessfulUpdate.getText();
		return successAlertMsg;
	}

	public void clickCancelEditRecord() {
		btnCancel.click();
	}

	public String getBatchName() {

		String batchName = txtBatchName.getAttribute("ng-reflect-model");
		return batchName;
	}

	public void setBatchDesc(String batchDesc) {

		txtBatchDesc.sendKeys(batchDesc);
	}

	public String getBatchDesc() {

		String batchDesc = txtBatchDesc.getAttribute("ng-reflect-model");
		return batchDesc;
	}

	public void setBatchClassesCount(String classesCount) {
		txtBatchNoOfClasses.sendKeys(classesCount);
	}

	public String getBatchClassesCount() {
		String numOfClasses = txtBatchNoOfClasses.getAttribute("ng-reflect-model");
		return numOfClasses;
	}

	public void setProgramName(String programName) throws InterruptedException {

		dropdownlist.click();
		Thread.sleep(1000);
		WebElement pName = driver
				.findElement(By.xpath("//ul[@role='listbox']/p-dropdownitem/li[@aria-label='" + programName + "']"));

		wait.until(ExpectedConditions.elementToBeClickable(pName));

		pName.click();
	}

	public String saveBatchDetails() {	
		ScrollTo(btnSave);
		btnSave.click();
		String lblProgNameErrMsg = "";
		try {
			if (batchNameErrMsg != null && batchNameErrMsg.isDisplayed())
				lblProgNameErrMsg = batchNameErrMsg.getText();
		} catch (Exception exception) {

		}
		return lblProgNameErrMsg;
	}

}