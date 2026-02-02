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

        // Triggered automatically by TestNG when any test method fails
        System.out.println(" onTestFailure triggered");

        // Log the failure exception/stacktrace into Extent Report
        test.fail(result.getThrowable());

        // WebDriver reference to capture screenshot
        WebDriver driver = null;

        try {

            // Get BaseTest class field "driver"
            java.lang.reflect.Field field = result.getTestClass()
                    .getRealClass()
                    .getSuperclass()
                    .getDeclaredField("driver");

            // 🔥 Allow access to protected field
            field.setAccessible(true);

            // Fetch driver from BaseTest instance
            driver = (WebDriver) field.get(result.getInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Debug print to confirm driver is successfully received
        System.out.println(">>> Listener received driver: " + driver);

        // Capture screenshot on failure and return file path
        String path = ScreenshotUtils.takeScreenshot(
                driver,
                result.getMethod().getMethodName()
        );

        // Attach screenshot to Extent Report only if path is valid
        if(path != null){
            try {
                test.addScreenCaptureFromPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Executes after all tests finish
    @Override
    public void onFinish(ITestContext context) {

        // Write report data to HTML file
        extent.flush();
    }
}
