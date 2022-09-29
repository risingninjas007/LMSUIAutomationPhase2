package com.lms.base;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LMSBasePage extends TestBase {

	WebDriver driver;

	@FindBy(css = ".p-inputtext")
	protected WebElement txtSearch;

	@FindBy(xpath = "//tbody/tr")
	protected List<WebElement> totalEntries;

	@FindBy(css = ".p-paginator-current")
	protected WebElement lblPaginationText; // Showing _ to _ of _ entries

	@FindBy(css = ".p-ai-center")
	protected WebElement lblFooterText; // In total there are _ programs.

	@FindBy(xpath = "//button[contains(@class,'p-paginator-next')]")
	protected WebElement nextPaginator;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-prev')]")
	protected WebElement prevPaginator;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-first')]")
	protected WebElement firstPaginator;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-last')]")
	protected WebElement lastPaginator;

	@FindBy(css = ".p-paginator-page")
	protected List<WebElement> pages;

	@FindBy(xpath = "//button[contains(@class,'p-button-danger')]")
	protected WebElement btnDelete;

	@FindBy(xpath = "//div[contains(@class,'p-toast-detail')]")
	protected WebElement lblSucessfulUpdate;

	@FindBy(xpath = "//div[contains(@class,'p-toast-summary')]")
	protected WebElement lblSucessfulUpdateSummary;

	@FindBy(css = ".p-confirm-dialog-message")
	protected WebElement deleteConfirmMsg;

	@FindBy(xpath = "//button[@ng-reflect-label='Yes']")
	protected WebElement btnYes;

	@FindBy(id = "new")
	protected WebElement btnNewEntry;

	@FindBy(xpath = "//button[@ng-reflect-label='No']")
	protected WebElement btnNo;

	@FindBy(css = ".p-dialog-title")
	protected WebElement lblDialogTitle;

	@FindBy(xpath = "//th//div[@role='checkbox']")
	protected WebElement headerCheckBox;
	
	@FindBy(xpath = "//tbody//div[@role='checkbox']")
	protected List<WebElement> checkBoxes;

	@FindBy(css=".pi-times")
	protected WebElement close;
	
	@FindBy(xpath = "//td/following-sibling::td//button[contains(@icon,'pi-pencil')]")
	protected WebElement btnEdit;
	
	@FindBy(xpath = "//tbody//button[contains(@class,'p-button-danger')]")
	protected WebElement btnDeleteEntry;
	
	
	
	public LMSBasePage() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}

	public String validateSearchText() {
		return txtSearch.getAttribute("placeholder");
	}

	public void searchData(String searchText) throws InterruptedException {
		txtSearch.clear();
		txtSearch.sendKeys(searchText);
		Thread.sleep(1000);
	}

	public void checkHeaderCheckBox() {
		headerCheckBox.click();
	}

	public void closeDialog() {
		close.click();
	}

	public boolean checkIfAllCheckBoxChecked()
	{
		boolean isAllCheckBoxChecked=true;
		
		for(WebElement checkBox:checkBoxes)
		{
			String status=checkBox.getAttribute("aria-checked");
			if(status.equalsIgnoreCase("false"))
			{
				isAllCheckBoxChecked=false;
				break;
			}
		}
		return isAllCheckBoxChecked;
	}
	
	public boolean isDeleteDisabled() {
		boolean isDisabled = (boolean) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].hasAttribute(\"disabled\");", btnDelete);
		// return btnDelete.isEnabled();
		return isDisabled;
	}
	
	public int getTotalEntries() {
		// String totalPrograms=totalEntries.getText().replaceAll("[^0-9]", "");
		//grab only the total entries
		int totalRowCount = 0;

		totalRowCount = totalEntries.size();
		// locating next button
		String nextButtonClass = nextPaginator.getAttribute("class");

		while (!nextButtonClass.contains("p-disabled")) {
			nextPaginator.click();
			totalRowCount += totalEntries.size();
			nextButtonClass = nextPaginator.getAttribute("class");
		}
		try {
			if (firstPaginator.isEnabled())
				firstPaginator.click();
		} catch (Exception e) {
		}
		return totalRowCount;
	}

	public int getEntriesPerPage() {
		return totalEntries.size();
	}

	public String getFooterText() {
		return lblFooterText.getText();
	}

	public String getCurrentPaginationText() {
		return lblPaginationText.getText();
	}

	public boolean validateSearchResultsByEntries(String searchText) {

		boolean isSearchResultsValid = true;

		// locating next button
		String nextButtonClass = nextPaginator.getAttribute("class");
		if (totalEntries.size() <= 5 && nextButtonClass.contains("p-disabled")) {
			isSearchResultsValid = loopThroughEntries(searchText);

		} else {

			while (!nextButtonClass.contains("p-disabled") && isSearchResultsValid) { // While there are no more
				isSearchResultsValid = loopThroughEntries(searchText);

				ScrollTo(nextPaginator);
				nextPaginator.click();

				totalEntries = driver.findElements(By.xpath("//tbody/tr"));
				nextButtonClass = nextPaginator.getAttribute("class");
			}
		}
		return isSearchResultsValid;
	}

	protected boolean loopThroughEntries(String searchText) {
		boolean isSearchResultsValid = true;
		String program = "", programDesc = "";
		int programCellNumber = 2, programDescCellNumber = 3;

		for (int i = 1; i <= totalEntries.size(); i++) {
			program = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + programCellNumber + "]")).getText();
			programDesc = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + programDescCellNumber + "]"))
					.getText();

			if (doesContain(program, searchText) || doesContain(programDesc, searchText))
				continue;
			else {
				isSearchResultsValid = false;
				break;
			}

		}

		return isSearchResultsValid;
	}

	public boolean validateSearchResultsByStatus(String searchText) {
		boolean isSearchResultsValid = true;

		// locating next button
		String nextButtonClass = nextPaginator.getAttribute("class");

		if (totalEntries.size() <= 5 && nextButtonClass.contains("p-disabled")) {
			isSearchResultsValid = loopThroughProgramsForStatus(searchText);

		} else {

			while (!nextButtonClass.contains("p-disabled") && isSearchResultsValid) { // While there are no more
				isSearchResultsValid = loopThroughProgramsForStatus(searchText);

				ScrollTo(nextPaginator);
				nextPaginator.click();

				totalEntries = driver.findElements(By.xpath("//tbody/tr"));
				nextButtonClass = nextPaginator.getAttribute("class");
			}
		}
		return isSearchResultsValid;
	}

	private boolean loopThroughProgramsForStatus(String searchText) {
		boolean isSearchResultsValid = true;
		String status = "";
		int statusCellNumber = 4;

		for (int i = 1; i <= totalEntries.size(); i++) {
			status = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + statusCellNumber + "]")).getText();

			if (status.equalsIgnoreCase(searchText))
				continue;
			else {
				isSearchResultsValid = false;
				break;
			}

		}

		return isSearchResultsValid;
	}

	protected boolean doesContain(String source, String searchText) {
		return Pattern.compile(Pattern.quote(searchText), Pattern.CASE_INSENSITIVE).matcher(source).find();
	}

	public boolean verifyEntryUpdate(String newName) throws InterruptedException {
		searchData(newName);
		boolean isUpdated = loopThroughEntries(newName);
		return isUpdated;
	}

	public String verifyEntryUpdate() {
		wait.until(ExpectedConditions.visibilityOf(lblSucessfulUpdate));
		return lblSucessfulUpdate.getText();
	}

	public String verifyConfirmationMsg() {
		String successMsg = lblSucessfulUpdateSummary.getText() + " " + lblSucessfulUpdate.getText();
		return successMsg;
	}

	public String getDeleteConfirmMsg() {
		return deleteConfirmMsg.getText();
	}

	public void selectDeleteConfirmation(String option) {
		switch (option.toLowerCase()) {
		case "yes":
			btnYes.click();
			break;
		case "no":
			btnNo.click();
			break;
		}

	}

	public void clickYes() {
		btnYes.click();
	}

	public boolean verifyPrevLinks() {
		boolean isDisabled = false;
		isDisabled = (!prevPaginator.isEnabled() && !firstPaginator.isEnabled());
		return isDisabled;
	}

	public boolean verifyNextLinks() {
		boolean isDisabled = false;
		isDisabled = (!nextPaginator.isEnabled() && !lastPaginator.isEnabled());
		return isDisabled;
	}

	public void navigateToLastPage() {
		if (lastPaginator.isEnabled())
			lastPaginator.click();
	}

	public void navigate(String navigation) {
		switch (navigation) {
		case "<":
			ScrollTo(prevPaginator);
			if (prevPaginator.isEnabled())
				prevPaginator.click();
			break;

		case ">":
			ScrollTo(nextPaginator);
			if (nextPaginator.isEnabled())
				nextPaginator.click();
			break;
		}

	}

	public void goToPage(String prevPage) {
		for (WebElement page : pages) {
			if (page.getText().equals(prevPage) && page.isEnabled()) {
				ScrollTo(page);
				page.click();
				break;
			}
		}
	}

	public String getDialogTitle() {
		return lblDialogTitle.getText();
	}

	public boolean verifyNextLink(Integer entriesCnt) {

		boolean isDisabled = false;
		if (totalEntries.size() <= entriesCnt) {
			isDisabled = (!nextPaginator.isEnabled() && !lastPaginator.isEnabled());
		} else
			isDisabled = (nextPaginator.isEnabled() && lastPaginator.isEnabled());
		return isDisabled;

	}

	public boolean verifyCurrentPage(String nextPage) {
		boolean isCurrentPageHighlighted = false;
		if (totalEntries.size() > 5) {
			for (WebElement page : pages) {
				if (page.getAttribute("class").contains("p-highlight") && page.getText().equals(nextPage)) {
					isCurrentPageHighlighted = true;
					break;
				}
			}
		} else
			isCurrentPageHighlighted = true;

		return isCurrentPageHighlighted;
	}

	public String getNewEntryLabel() {
		return btnNewEntry.getAttribute("label");
	}
	
	public boolean isCloseXAvailable()
	{
		return close.isDisplayed();
	}
}
