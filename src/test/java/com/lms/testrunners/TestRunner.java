package com.lms.testrunners;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"rerun:rerun/failed_scenarios.txt" }, monochrome = true, tags="not @ignore",
				glue = { "com.lms.stepdefinitions","com.lms.hooks" }, 
				features = { "src/test/resources/features/LoginPage.feature" })
public class TestRunner extends AbstractTestNGCucumberTests {

	// static thread-safe container to keep the browser value
	public final static ThreadLocal<String> Browser = new ThreadLocal<>();

	@BeforeTest
	@Parameters("Browser")
	public void defineBrowser(@Optional("chrome") String browser) {

		// put browser value to thread-safe container
		TestRunner.Browser.set(browser);
	}

}