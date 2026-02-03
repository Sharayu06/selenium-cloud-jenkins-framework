package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.driver.DriverFactory;
import com.framework.utils.ConfigReader;
import com.framework.utils.WaitUtils;

/**
 * BaseTest
 * --------
 * Contains common setup and teardown logic
 * for all test classes.
 */

public class BaseTest {
	protected WaitUtils wait;
	protected WebDriver driver;

    /**
     * This method runs before each test method.
     * It initializes the WebDriver and launches the application URL.
     */
	@BeforeMethod
	public void setUp() {

	    // 1. Create driver
	    DriverFactory.initDriver();

	    // 2. Get driver from ThreadLocal
	    driver = DriverFactory.getDriver();

	    // 3. Open URL
	    driver.get(ConfigReader.getBaseUrl());

	    System.out.println("Current URL = " + driver.getCurrentUrl());

	    // 4. Init waits
	    wait = new WaitUtils(driver);
	    
	    System.out.println("Running on Browser = " + ConfigReader.getBrowser());
	    System.out.println("Running on Environment = " + ConfigReader.getEnv());

	}
 
    /**
     * This method runs after each test method.
     * It quits the WebDriver instance and cleans up resources.
     */
    @AfterMethod
    public void tearDown() {

        // Quit WebDriver and clean ThreadLocal
        DriverFactory.quitDriver();
    }
}
