package com.lms.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.LMSBasePage;

public class ManageUserPage extends LMSBasePage {
	WebDriver driver;

	@FindBy(xpath = "//div[contains(text(),'Manage User')]")
	WebElement lblHeader;

	@FindBy(xpath = "//table//th[@role='columnheader']")
	List<WebElement> userTableHeaders;

	@FindBy(xpath = "//th[@psortablecolumn='user_id']")
	WebElement userIdHeader;

	@FindBy(xpath = "//th[@psortablecolumn='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//th[@psortablecolumn='emailAddress']")
	WebElement emailAddress;

	@FindBy(xpath = "//th[@psortablecolumn='phoneNumber']")
	WebElement phoneNumber;

	@FindBy(xpath = "//th[@psortablecolumn='batch']")
	WebElement batch;

	@FindBy(xpath = "//th[@psortablecolumn='skill']")
	WebElement skill;
	
	@FindBy(css = ".p-button-link")
	WebElement userId;

	
	public ManageUserPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getPageHeader() {
		return lblHeader.getText();
	}

	public ModifyUserDetailsPage retrieveUserInfo() {
		userId.click();
		return new ModifyUserDetailsPage();
	}

	public ModifyUserDetailsPage addNewUser() {
		btnNewEntry.click();
		return new ModifyUserDetailsPage();
	}

	public List<String> getUserTableHeaders() {
		List<String> headers = userTableHeaders.stream().map(element -> element.getAttribute("textContent").trim())
				.collect(Collectors.toList());
		return headers;
	}

	public void Sort(String category, String order) {

		switch (category) {
		case "ID":
			userIdHeader.click(); // single click - ascending
			if (order.equals("descending"))
				userIdHeader.click(); // double click - descending
			break;
		case "Name":
			firstName.click(); // single click - ascending
			if (order.equals("descending"))
				firstName.click(); // double click - descending
			break;
		case "Email Address":
			emailAddress.click(); // single click - ascending
			if (order.equals("descending"))
				emailAddress.click(); // double click - descending
			break;
		case "Contact Number":
			phoneNumber.click(); // single click - ascending
			if (order.equals("descending"))
				phoneNumber.click(); // double click - descending
			break;
		case "Batch":
			batch.click(); // single click - ascending
			if (order.equals("descending"))
				batch.click(); // double click - descending
			break;
		case "Skill":
			skill.click(); // single click - ascending
			if (order.equals("descending"))
				skill.click(); // double click - descending
			break;
		}

	}

	public boolean verifySort(String category, String order) {
		String sortOrder = "";
		boolean isSorted = false;
		switch (category) {

		case "ID":
			sortOrder = userIdHeader.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "Name":
			sortOrder = firstName.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "Email Address":
			sortOrder = emailAddress.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "Contact Number":
			sortOrder = phoneNumber.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "Batch":
			sortOrder = batch.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		case "Skill":
			sortOrder = skill.getAttribute("aria-sort");
			if (sortOrder.equals(order))
				isSorted = true;
			break;
		}
		return isSorted;
	}

	public void selectUser(String user) {
		// Select first found entry
		WebElement checkBox = driver
				.findElement(By.xpath("//td[text()='" + user + "']/preceding-sibling::td//div[@role='checkbox']"));
		if (checkBox != null)
			checkBox.click();

	}
	
	public ModifyUserDetailsPage deleteSelectedUser() {
		// Select first found entry
		btnDelete.click();
		return new ModifyUserDetailsPage();
	}	
	
	public ModifyUserDetailsPage editUser() {
		// Select first found entry
		btnEdit.click();
		return new ModifyUserDetailsPage();
	}

	public ModifyUserDetailsPage deleteUser() {
		// Select first found entry
		btnDeleteEntry.click();
		return new ModifyUserDetailsPage();
	}

	
	public void getTextAt(String cssId) {
	   String val= (String) ((JavascriptExecutor) driver).executeScript("return $('" + cssId + "').val();");
	   System.out.println(val);
	}
	
}
