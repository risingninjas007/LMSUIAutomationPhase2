package com.lms.hooks;

import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.lms.base.TestBase;
import com.lms.testrunners.TestRunner;
//import com.lms.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class CucumberHooks extends TestBase {

	public static final Logger logger = LogManager.getLogger();
	
	@Before(order=1)
	public void setuplog(Scenario scenario) {
		logger.info("Execution for Scenario - Start ->"+ scenario.getName());
	}
	
	@Before(order=1)
	public void NavigatetoLMS() {
		
		// String browser = ConfigReader.getConfigValue("browser");
		String browser = TestRunner.Browser.get(); // get the browser value for current thread
		initialize_driver(browser);
		NavigateToLMSPortal();
	}

	@After(order = 0)
	public void teardown() {
		getDriver().quit();
	}

	@After(order = 1)
	public void TakeScreenshotAfterScenario(Scenario scenario) {
		String screenshotName;
		if (scenario.isFailed()) {
			logger.info("Failed Execution for Scenario  ->"+ scenario.getName());
			screenshotName = "FailedScreenshot_for_" + scenario.getName();
			scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			Allure.addAttachment(screenshotName, new ByteArrayInputStream(sourcePath));
		}

	}

}
