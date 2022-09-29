package com.lms.pages;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lms.base.LMSBasePage;

public class ModifyUserDetailsPage extends LMSBasePage {

	WebDriver driver;

	@FindBy(css = ".p-dialog-title")
	WebElement lblDialogTitle;

	@FindBy(xpath = "//input[@formcontrolname='firstName']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@formcontrolname='lastName']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@formcontrolname='emailAddress']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@formcontrolname='phoneNumber']")
	WebElement txtPhone;

	@FindBy(xpath = "//input[@formcontrolname='skill']")
	WebElement txtBatch;

	@FindBy(xpath = "//input[@formcontrolname='batch']")
	WebElement txtSkill;

	@FindBy(xpath = "//input[@formcontrolname='postalCode']")
	WebElement txtPostalCode;

	@FindBy(xpath = "//button/span[text()='Cancel']")
	WebElement btnCancel;

	@FindBy(xpath = "//button/span[text()='Submit']")
	WebElement btnSubmit;

	@FindBy(xpath = "//mat-select[@formcontrolname='state']//div[contains(@class,'mat-select-arrow-wrapper')]")
	WebElement stateDropdown;

	@FindBy(xpath = "//mat-card-content/div[@class='row']/div[@class='col']//label")
	List<WebElement> labelsUserDetails;

	@FindBy(xpath = "//mat-card-content/div[@class='row']/div[@class='col']//input")
	List<WebElement> txtUserDetails;

	@FindBy(xpath = "//button/span[contains(text(),'Add C/O')]")
	WebElement btnAdd;

	@FindBy(xpath = "//textarea[@placeholder='Address 2']")
	WebElement txtAddress2;

	public ModifyUserDetailsPage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public void setFirstName(String newFirstName) {
		txtFirstName.clear();
		txtFirstName.sendKeys(newFirstName);
	}

	public String getProgramName() {
		String programName = txtFirstName.getAttribute("ng-reflect-model");
		return programName;
	}

	public boolean isCancelAvailable() {
		ScrollTo(btnCancel);
		return btnCancel.isDisplayed();
	}

	public boolean isSubmitAvailable() {
		ScrollTo(btnSubmit);
		return btnSubmit.isDisplayed();
	}

	public void submit() throws InterruptedException {
		ScrollTo(btnSubmit);
		btnSubmit.click();
		Thread.sleep(1000);
	}

	public boolean isAddAddressAvailable(String expectedLabel) {
		ScrollTo(btnAdd);
		return btnAdd.getText().equalsIgnoreCase(expectedLabel);
	}

	public void addAddress() {
		ScrollTo(btnAdd);
		btnAdd.click();
	}

	public void clickPostalCode() {
		ScrollTo(txtPostalCode);
		txtPostalCode.click();
		
	}

	public boolean isArrowsShown() {
		txtPostalCode.click();
		String classAttribute = txtPostalCode.getAttribute("class");
		return classAttribute.contains("touched");
	}

	public void cancelUserEdit() {
		ScrollTo(btnCancel);
		btnCancel.click();
	}

	public void getUserDetailsData() {
		Actions action = new Actions(driver);

		for (WebElement txt : txtUserDetails) {
			action.moveToElement(txt).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "c")).build().perform();

		}

	}

	public List<String> getUserDetailsPageLabels() {
		List<String> list = labelsUserDetails.stream().map(element -> element.getAttribute("textContent"))
				.collect(Collectors.toList());

		Actions action = new Actions(driver);

		for (WebElement txt : txtUserDetails) {
			action.moveToElement(txt).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "c")).build().perform();

			System.out.println(txt.getAttribute("value"));
		}

		return list;
	}

	public void clickStateDropdown() {
		ScrollTo(stateDropdown);
		stateDropdown.click();
	}

	public boolean isAddress2TextAvailable(String expectedLabel) {
		WebElement txtAdd2 = driver.findElement(By.xpath("//textarea[@placeholder='" + expectedLabel + "']"));
		return txtAdd2.isDisplayed();
	}

	public void enterUserDetails(List<Map<String, String>> userDetails) {
		for (Map<String, String> user : userDetails) {
			txtFirstName.sendKeys(user.get("First Name"));
			txtLastName.sendKeys(user.get("Last Name"));
			txtEmail.sendKeys(user.get("Email"));
			txtPhone.sendKeys(user.get("Contact Number"));
			txtBatch.sendKeys(user.get("Batch"));
			txtSkill.sendKeys(user.get("Skill"));
		}

	}

	public boolean verifyUserDetails(List<Map<String, String>> userDetails) {

		boolean isValid = false;
		for (Map<String, String> user : userDetails) {

			WebElement firstName = driver
					.findElement(By.xpath("//tbody/tr/td[3][contains(text(),'" + user.get("First Name") + "')]"));

			WebElement lastName = driver
					.findElement(By.xpath("//tbody/tr/td[3][contains(text(),'" + user.get("Last Name") + "')]"));
			WebElement email = driver
					.findElement(By.xpath("//tbody/tr/td[4][contains(text(),'" + user.get("Email") + "')]"));
			WebElement phone = driver
					.findElement(By.xpath("//tbody/tr/td[5][contains(text(),'" + user.get("Contact Number") + "')]"));

			WebElement batch = driver
					.findElement(By.xpath("//tbody/tr/td[6][contains(text(),'" + user.get("Batch") + "')]"));

			WebElement skill = driver
					.findElement(By.xpath("//tbody/tr/td[7][contains(text(),'" + user.get("Skill") + "')]"));

			if (firstName != null && lastName != null && email != null && phone != null && batch != null
					&& skill != null)
				isValid = true;
		}
		return isValid;

	}
}
