package com.framework.base;

import com.framework.driver.DriverFactory;
import com.framework.utils.ConfigReader;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest
 * --------
 * Responsible for:
 *  - Initializing ThreadLocal WebDriver BEFORE every test
 *  - Navigating to Base URL
 *  - Quitting driver AFTER every test
 */
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        String browser = ConfigReader.getBrowser();

        // Initialize ThreadLocal driver
        DriverFactory.initDriver(browser);

        WebDriver driver = DriverFactory.getDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        System.out.println("Thread Driver = " + driver);

        // Open application
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
