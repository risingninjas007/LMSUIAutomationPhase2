package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.LMSBasePage;

public class ManageProgramPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(xpath = "//div[contains(text(),'Manage Program')]")
	WebElement lblProgramHeader;

	@FindBy(xpath = "//button/span[text()='Program']")
	WebElement btnProgram;

	@FindBy(xpath = "//td[text()='Java']/preceding-sibling::td/p-tablecheckbox")
	WebElement checkBox;
	
	@FindBy(xpath = "//th[@psortablecolumn='programName']")
	WebElement programHeader;

	@FindBy(xpath = "//th[@psortablecolumn='programDescription']")
	WebElement programDescHeader;

	@FindBy(xpath = "//th[@psortablecolumn='programStatus']")
	WebElement programStatusHeader;

	
		
			
	public ManageProgramPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getPageHeader() {
		return lblProgramHeader.getText();
	}


	public void selectProgram(String program) {
		// Select first found entry
		WebElement checkBox = driver
				.findElement(By.xpath("//td[text()='" + program + "']/preceding-sibling::td//div[@role='checkbox']"));
		if (checkBox != null)
			checkBox.click();
	}

	public boolean validateProgramSelection(String program) {
		WebElement checkBox = driver
				.findElement(By.xpath("//td[text()='" + program + "']/preceding-sibling::td//div[@role='checkbox']"));

		String checkBoxAttribute = checkBox.getAttribute("aria-checked");
		return checkBoxAttribute.equalsIgnoreCase("true");
	}

	public ModifyProgramDetailsPage editProgram() {
		// Select first found entry
		btnEdit.click();
		return new ModifyProgramDetailsPage();
	}

	public ModifyProgramDetailsPage editProgram(String program) throws InterruptedException {
		searchData(program);
		btnEdit.click();
		return new ModifyProgramDetailsPage();
	}

	
	

	public boolean verifyProgramUpdate(String program, String newStatus) throws InterruptedException {
		searchData(program);
		boolean isUpdated = false;
		int statusCellNumber = 4;
		String status = driver.findElement(By.xpath("//tbody/tr[1]/td[" + statusCellNumber + "]")).getText();

		isUpdated = status.equalsIgnoreCase(newStatus);
		return isUpdated;
	}


	public ModifyProgramDetailsPage deleteProgram() {
		// Select first found entry
		btnDeleteEntry.click();
		return new ModifyProgramDetailsPage();
	}

	public ModifyProgramDetailsPage addNewProgram() {
		btnNewEntry.click();
		return new ModifyProgramDetailsPage();
	}

	public ModifyProgramDetailsPage deleteSelectedProgram() {
		// Select first found entry
		btnDelete.click();
		return new ModifyProgramDetailsPage();
	}

	
	public void Sort(String category, String order) {

		switch (category) {
		case "program":
			programHeader.click(); // single click - ascending
			if (order.equals("descending"))
				programHeader.click(); // double click - descending
			break;
		case "program description":
			programDescHeader.click(); // single click - ascending
			if (order.equals("descending"))
				programDescHeader.click(); // double click - descending
			break;
		case "program status":
			programStatusHeader.click(); // single click - ascending
			if (order.equals("descending"))
				programStatusHeader.click(); // double click - descending
			break;
		}

	}

	public boolean verifySort(String category, String order) {
		String sortOrder = "";
		boolean isSorted = false;
		switch (category) {
		case "program":
			sortOrder = programHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "program description":
			sortOrder = programDescHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "program status":
			sortOrder = programStatusHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		}
		return isSorted;
	}

}
