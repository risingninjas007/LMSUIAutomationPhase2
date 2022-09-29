package com.lms.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lms.base.LMSBasePage;

public class ModifyProgramDetailsPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(id = "programName")
	WebElement txtProgramName;

	@FindBy(id = "programDescription")
	WebElement txtProgramDesc;

	@FindBy(id = "Active")
	WebElement radioBtnActive;

	@FindBy(id = "Inactive")
	WebElement radioBtnInActive;

	@FindBy(xpath = "//button[@label='Save']")
	WebElement btnSave;

	@FindBy(xpath = "//button[@label='Cancel']")
	WebElement btnCancel;


	@FindBy(xpath = "//input[@id='programName']/following-sibling::small")
	WebElement progNameErrMsg;

	

	public ModifyProgramDetailsPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	
	public void updateProgramName(String newProgramName) {
		setProgramName(newProgramName);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setProgramName(String newProgramName) {
		txtProgramName.clear();
		txtProgramName.sendKeys(newProgramName);
	}

	public String getProgramName() {
		String programName = txtProgramName.getAttribute("ng-reflect-model");
		return programName;
	}

	public void updateProgramDescription(String newProgramDesc) {
		setProgramDescription(newProgramDesc);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setProgramDescription(String newProgramDesc) {
		txtProgramDesc.clear();
		txtProgramDesc.sendKeys(newProgramDesc);
	}

	public String getProgramDescription() {
		String programDesc = txtProgramDesc.getAttribute("ng-reflect-model");
		return programDesc;
	}

	public void updateProgramStatus(String status) {
		setProgramStatus(status);
		ScrollTo(btnSave);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setProgramStatus(String status) {
		switch (status.toLowerCase()) {
		case "active":
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioBtnActive);
			break;
		case "inactive":
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioBtnInActive);
			break;
		default:
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioBtnActive);
			break;
		}
	}

	public String getProgramStatus() {
		String programStatus = radioBtnActive.getAttribute("aria-checked").equalsIgnoreCase("true") ? "Active"
				: "Inactive";
		;
		return programStatus;
	}

	public void cancelUpdate() {
		ScrollTo(btnCancel);
		btnCancel.click();
	}
	


	public String saveProgramDetails() {
		ScrollTo(btnSave);
		btnSave.click();
		String lblProgNameErrMsg = "";
		try {
			if (progNameErrMsg != null && progNameErrMsg.isDisplayed())
				lblProgNameErrMsg = progNameErrMsg.getText();
		} catch (Exception exception) {

		}
		return lblProgNameErrMsg;
	}

	


}
