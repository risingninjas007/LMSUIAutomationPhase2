package com.lms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.TestBase;

public class HomePage extends TestBase {

	WebDriver driver;

	@FindBy(xpath = "//app-header//span[contains(text(),'LMS')]")
	WebElement lblPageHeader;

	@FindBy(xpath = "//button/span[text()='Program']")
	WebElement btnProgram;

	@FindBy(xpath = "//button/span[text()='Assignment']")
	WebElement btnAssignment;

	@FindBy(xpath = "//button/span[text()='User']")
	WebElement btnUser;

	@FindBy(xpath = "//button/span[text()='Batch']")
	WebElement btnBatch;

	@FindBy(xpath = "//app-header//button")
	List<WebElement> btnHeaders;

	public HomePage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageHeader() {
		return lblPageHeader.getText();
	}

	public ManageAssignmentPage navigatetoAssignments() {
		btnAssignment.click();
		return new ManageAssignmentPage();
	}

	public ManageProgramPage navigatetoPrograms() {
		btnProgram.click();
		return new ManageProgramPage();
	}

	public ManageUserPage navigatetoUsers() {
		btnUser.click();
		return new ManageUserPage();
	}

	public ManageBatchPage navigateToBatch() {

		btnBatch.click();
		return new ManageBatchPage();

	}

	public boolean verifyMenu(String headerOption) {
		boolean isAvailable = false;
		if (headerOption.equalsIgnoreCase("logout")) {
			WebElement logOut = driver.findElement(By.xpath("//app-header//button/span[text()='Logout']"));
			if (logOut.isDisplayed())
				isAvailable = true;
		} else {
			for (WebElement btnHeader : btnHeaders) {
				String routerLink = btnHeader.getAttribute("routerlink");
				if (routerLink.contains(headerOption.toLowerCase())) {
					isAvailable = true;
					break;
				}
			}
		}
		return isAvailable;

	}

}
