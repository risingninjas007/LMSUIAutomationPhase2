package com.lms.pages;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lms.base.LMSBasePage;

public class ModifyAssignmentDetailsPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(css = ".p-dialog-title")
	WebElement lblDialogTitle;

	@FindBy(id = "assignment")
	WebElement txtAssignment;

	@FindBy(id = "assignmentDescription")
	WebElement txtAssignmentDescription;

	@FindBy(id = "assignmentGraderId")
	WebElement txtGrade;

	@FindBy(xpath = "//button[@label='Save']")
	WebElement btnSave;

	@FindBy(xpath = "//button[@label='Cancel']")
	WebElement btnCancel;

	@FindBy(xpath = "//button[@ng-reflect-label='Yes']")	
	WebElement btnYes;

	@FindBy(xpath = "//button[@ng-reflect-label='No']")
	WebElement btnNo;

	@FindBy(css = ".p-confirm-dialog-message")
	WebElement deleteConfirmMsg;

	@FindBy(xpath = "//span[contains(@class,'p-calendar')]")
	WebElement btnCalendar;

	@FindBy(tagName = "p-calendar")
	WebElement dateValue;

	@FindBy(xpath = "//input[@id='assignment']/following-sibling::small")
	WebElement assignmentNameErrMsg;
	
	
	public ModifyAssignmentDetailsPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getDialogTitle() {
		return lblDialogTitle.getText();
	}

	public String getDeleteConfirmMsg() {
		return deleteConfirmMsg.getText();
	}

	public void updateAssignmentName(String newAssignment) {
		setAssignmentName(newAssignment);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setAssignmentName(String newAssignment) {
		txtAssignment.clear();
		txtAssignment.sendKeys(newAssignment);
	}

	public String getAssignmentName() {
		String assignmentName = txtAssignment.getAttribute("ng-reflect-model");
		return assignmentName;
	}

	public void updateAssignmentDescription(String newAssignmentDesc) {
		setAssignmentDescription(newAssignmentDesc);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void updateAssignmentGrade(String newAssignmentGrade) {
		setAssignmentGrade(newAssignmentGrade);
		ScrollTo(btnSave);
		btnSave.click();
	}

	private void setAssignmentGrade(String newAssignmentGrade) {
		txtGrade.clear();
		txtGrade.sendKeys(newAssignmentGrade);
	}

	public void setAssignmentDescription(String newAssignmentDesc) {
		txtAssignmentDescription.clear();
		txtAssignmentDescription.sendKeys(newAssignmentDesc);
	}

	public String getAssignmentDescription() {
		String assignmentDesc = txtAssignmentDescription.getAttribute("ng-reflect-model");
		return assignmentDesc;
	}

	public void cancelUpdate() {
		ScrollTo(btnCancel);
		btnCancel.click();
	}

	/*
	 * public String saveAssignmentDetails() { btnSave.click(); String lblErrMsg =
	 * ""; try { if (assignmentNameErrMsg != null &&
	 * assignmentNameErrMsg.isDisplayed()) lblErrMsg =
	 * assignmentNameErrMsg.getText(); } catch (Exception exception) {
	 * 
	 * } return lblErrMsg; }
	 */
	public void selectDeleteConfirmation(String option) {
		switch (option.toLowerCase()) {
		case "yes":
			ScrollTo(btnYes);
			btnYes.click();
			break;
		case "no":
			ScrollTo(btnNo);
			btnNo.click();
			break;
		}

	}

	public void clickYes() {
		ScrollTo(btnYes);
		btnYes.click();
	}

	public void updateAssignmentDate(String date) {
		setAssignmentDate(date);
		ScrollTo(btnSave);
		btnSave.click();
	}

	public void setAssignmentDate(String date) {

		String[] data = date.split("/"); // Date is in dd/mm/yy format as per the inspect element code i.e.
											// dateformat="dd/mm/yy"
		String day = data[0]; // get dd since the calendar control on website takes only the dd part

		ScrollTo(btnCalendar);
		btnCalendar.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//table[contains(@class,'p-datepicker-calendar')]")));
		driver.findElement(By.xpath("//table[contains(@class,'p-datepicker-calendar')]//td/span[text()='" + day + "']"))
				.click();

	}

	public String getAssignmentDate() throws ParseException {
		String strDate=dateValue.getAttribute("ng-reflect-model");
		return strDate;
	}

	public String saveAssignmentDetails() {
		btnSave.click();
		String lblAssignmentNameErrMsg = "";
		try {
			if (assignmentNameErrMsg != null)
				lblAssignmentNameErrMsg = assignmentNameErrMsg.getText();
		} catch (Exception exception) {

		}
		return lblAssignmentNameErrMsg;	
	}

	public void setAssignmentsGrade(String assignmentGrade) {
		txtGrade.clear();
		txtGrade.sendKeys(assignmentGrade);	

	}

	public String getAssignmentGrade() {
		String assignmentGrade = txtGrade.getAttribute("ng-reflect-model");
		return assignmentGrade;
	}
}
