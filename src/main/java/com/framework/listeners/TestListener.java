package com.framework.listeners;

// Extent report classes
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// Our framework classes
import com.framework.reports.ExtentManager;
import com.framework.utils.ScreenshotUtils;
import com.framework.driver.DriverFactory;

import org.openqa.selenium.WebDriver;
// TestNG listener interfaces
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // Get single ExtentReports instance from ExtentManager
    ExtentReports extent = ExtentManager.getExtent();

    // Holds current running test in report
    ExtentTest test;

    // Executes before each test method starts
    @Override
    public void onTestStart(ITestResult result) {

        // Create new test entry in Extent report using method name
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // Executes when test passes
    @Override
    public void onTestSuccess(ITestResult result) {

        // Mark test as PASSED in report
        test.pass("Test Passed");
    }

    // Executes when test fails
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println(" onTestFailure triggered");


        // Log failure in Extent
        test.fail(result.getThrowable());

        // ✅ Directly get driver from DriverFactory (NO BaseTest change)
        WebDriver driver = DriverFactory.getDriver();

        System.out.println(">>> Listener received driver: " + driver);

        // Take screenshot
        String path = ScreenshotUtils.takeScreenshot(
                driver,
                result.getMethod().getMethodName()
        );

        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Executes after all tests finish
    @Override
    public void onFinish(ITestContext context) {

        // Write report data to HTML file
        extent.flush();
    }
}
