package com.lms.base;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lms.utils.ConfigReader;

/**
 * Base class having the common implementations like driver initialisation &
 * scrolling to the web element
 **/
public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;

	// Make the driver thread safe for cross browser parallel testing
	public static ThreadLocal<WebDriver> threadsafeDriver = new ThreadLocal<>();

	public void initialize_driver(String browser) {

		String driverPath;
		if (browser == null) // When not executing as testng suite, parameters in xml cannot be read
			browser = ConfigReader.getConfigValue("browser");
		switch (browser) {
		case "chrome":
			driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options=new ChromeOptions();
			options.setHeadless(true);
			driver = new ChromeDriver(options);
			break;
		case "edge":
			driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", driverPath);
			EdgeOptions edgeOptions=new EdgeOptions();
			edgeOptions.setHeadless(true);
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			System.out.println("Please pass the correct browser value: " + browser);
			break;
		}

		// Set the explicit web driver wait of 3 secs
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));	

		threadsafeDriver.set(driver);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

	}

	// Gets the current driver instance based on the active thread
	public static synchronized WebDriver getDriver() {
		return threadsafeDriver.get();
	}

	// Method to scroll to the web element using JavaScriptExecutor
	public void ScrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void waitforVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//@Step("Navigate to the Portal page (Url set in the config file)")
	public static void NavigateToLMSPortal() {
		getDriver().get(ConfigReader.getConfigValue("url"));
	}
	

}
