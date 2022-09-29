package com.lms.pages;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lms.base.TestBase;

public class ManageBatchPage2 extends TestBase  {
	WebDriver driver;
	
	@FindBy(xpath = "//button[contains(@class,'p-paginator-prev')]")
	WebElement prevPaginator;
	
	@FindBy(css = ".p-paginator-page")
	List<WebElement> pages;
	
	@FindBy(xpath = "//button[contains(@class,'p-paginator-next')]")
	WebElement nextPaginator;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-first')]")
	WebElement firstPaginator;
	
	@FindBy(xpath = "//button[contains(@class,'p-paginator-last')]")
	WebElement lastPaginator;
	
	@FindBy(xpath = "//th[@psortablecolumn='batchName']")
	WebElement batchHeader;

	@FindBy(xpath = "//th[@psortablecolumn='batchDescription']")
	WebElement batchDescHeader;

	@FindBy(xpath = "//th[@psortablecolumn='batchStatus']")
	WebElement batchStatusHeader;


	@FindBy(id="new")
	WebElement btnAddNewBatch;
	
	@FindBy(xpath="//div[@class='box']//button[@icon='pi pi-trash']")
	WebElement btnDelete;

	@FindBy(xpath="//button[@ng-reflect-label='No']")
	WebElement noBtn;

	@FindBy(xpath="//button[@ng-reflect-label='Yes']")
	WebElement yesBtn;

	@FindBy(xpath="//span[contains(text(),'Confirm')]")
	WebElement confirmDeletionFormHeader;;

	@FindBy(xpath="//td/following-sibling::td//button[contains(@icon,'pi-pencil')]")
	WebElement editBatchBtn;

	@FindBy(xpath = "//tbody/tr")
	List<WebElement> totalEntries;

	@FindBy(css =".p-inputtext")
	WebElement txtSearchBox;

	@FindBy(xpath="//div[@class='box']//button[contains(@class,'p-button-danger')]")
	WebElement deleteBtnNearSearchBox;

	@FindBy(xpath="//div[contains(text(),'Manage Batch')]")
	WebElement batchPageHeading;


	public ManageBatchPage2() {
		this.driver = getDriver(); // get the driver instance for active thread
		PageFactory.initElements(driver, this);
	}


	public String getbatchPageHeading() {

		return batchPageHeading.getText();
	}

	public boolean isDeleteBtnDisabled() {

		return deleteBtnNearSearchBox.isEnabled();
	}

	public int verifyNumberOfRecordsOnPage() {

		wait.until(ExpectedConditions.visibilityOfAllElements(totalEntries));

		int actualRowCount = totalEntries.size();

		System.out.println("Actual COUNT:"+ actualRowCount);

		return actualRowCount;
	}

	public String validateSearchBoxData() {

		return txtSearchBox.getAttribute("placeholder");
	}

	public void enterDataInSearchBox(String batchName) {

		txtSearchBox.clear();
		txtSearchBox.sendKeys(batchName);	

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}



	public boolean validateSearchResultsByBatches(String searchText) {

		boolean isSearchResultsValid = true;

		// locating next button
		String nextButtonClass = nextPaginator.getAttribute("class");
		if (totalEntries.size() <= 5 && nextButtonClass.contains("p-disabled")) {
			isSearchResultsValid = loopThroughBatches(searchText);

		} else {

			while (!nextButtonClass.contains("p-disabled") && isSearchResultsValid) { // While there are no more
				isSearchResultsValid = loopThroughBatches(searchText);

				ScrollTo(nextPaginator);
				nextPaginator.click();

				totalEntries = driver.findElements(By.xpath("//tbody/tr"));
				nextButtonClass = nextPaginator.getAttribute("class");
			}
		}
		return isSearchResultsValid;
	}

	private boolean loopThroughBatches(String searchText) {
		boolean isSearchResultsValid = true;
		String batchName = ""; 
		int BatchCellNumber = 2;

		for (int i = 1; i <= totalEntries.size(); i++) {

			batchName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + BatchCellNumber + "]")).getText();

			if (doesContain(batchName, searchText))
				continue;
			else {
				isSearchResultsValid = false;
				break;
			}

		}

		return isSearchResultsValid;
	}


	private boolean doesContain(String source, String searchText) {
		return Pattern.compile(Pattern.quote(searchText), Pattern.CASE_INSENSITIVE).matcher(source).find();
	}

	public ModifyBatchDetailsPage editBatchAndNavigateToModifyBatchDetailsPage() {

		editBatchBtn.click();

		return new ModifyBatchDetailsPage();
	}


	public void selectBatch(String batchName) {
		// Select first found entry
		WebElement checkBox = driver
				.findElement(By.xpath("//td[text()='" + batchName + "']/preceding-sibling::td//div[@role='checkbox']"));
		if (checkBox != null)
			checkBox.click();
	}

	

	public ModifyBatchDetailsPage deleteSelectedBatch() {

		System.out.println("INSIDE deleteSelectedBatch");
			
		btnDelete.click();
		
		return new ModifyBatchDetailsPage();
		
		
	}

	public String VerifyConfirmDeletionFormHeader() {

		wait.until(ExpectedConditions.visibilityOf(confirmDeletionFormHeader));
		return confirmDeletionFormHeader.getText();
	}
	
	public ModifyBatchDetailsPage addNewBatchAndNavigateToBatchDetails() {
		
		btnAddNewBatch.click();
		
		return new ModifyBatchDetailsPage();
	}

	public boolean verifyPrevLinks() {
		boolean isDisabled = false;
		isDisabled = (!prevPaginator.isEnabled() && !firstPaginator.isEnabled());
		return isDisabled;
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
	
	public boolean verifyCurrentPage(String nextPage) {
		boolean isCurrentPageHighlighted = false;
		for (WebElement page : pages) {
			if (page.getAttribute("class").contains("p-highlight") && page.getText().equals(nextPage)) {
				isCurrentPageHighlighted = true;
				break;
			}
		}
		return isCurrentPageHighlighted;
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
