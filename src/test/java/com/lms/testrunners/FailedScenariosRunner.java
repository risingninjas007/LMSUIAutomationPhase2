package com.lms.testrunners;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"rerun:rerun/failed_scenarios.txt" }, monochrome = true, tags="not @defect",
				glue = { "com.lms.stepdefinitions","com.lms.hooks" }, 
				features = { "@rerun/failed_scenarios.txt"}) //Cucumber picks the failed scenarios from this file

public class FailedScenariosRunner extends AbstractTestNGCucumberTests {

	// static thread-safe container to keep the browser value
		public final static ThreadLocal<String> Browser = new ThreadLocal<>();

		@BeforeTest
		@Parameters("Browser")
		public void defineBrowser(@Optional("chrome") String browser) {

			// put browser value to thread-safe container
			TestRunner.Browser.set(browser);
		}
		
}
