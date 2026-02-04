package com.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.framework.driver.DriverFactory;
import com.framework.reports.ExtentManager;
import com.framework.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getExtent();

    // ThreadLocal ExtentTest for parallel execution
    private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extent.createTest(result.getMethod().getMethodName());

        // Store ExtentTest per thread
        tlTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        tlTest.get().pass("Test Passed");

        // IMPORTANT: clean ThreadLocal
        tlTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        tlTest.get().fail(result.getThrowable());

        // Get ThreadLocal driver
        WebDriver driver = DriverFactory.getDriver();
        System.out.println(">>> Listener received driver: " + driver);

        if (driver != null) {

            String path = ScreenshotUtils.takeScreenshot(
                    driver,
                    result.getMethod().getMethodName()
            );

            if (path != null) {
                try {
                    tlTest.get().addScreenCaptureFromPath(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // IMPORTANT: clean ThreadLocal
        tlTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
