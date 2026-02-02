package com.framework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 Day 2: Sample Selenium + TestNG test
 Purpose:
 - Validate Selenium and TestNG setup
 - Ensure browser automation works locally
 - Acts as smoke test for framework
*/

public class GoogleTest {

    // WebDriver instance used to control browser
    WebDriver driver;

    /*
     @BeforeMethod
     This method runs before every test method
     Used for browser initialization
    */
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();   // Launch Chrome browser
        driver.manage().window().maximize();
    }

    /*
     @Test
     Actual test case
     Opens Google website and prints page title
    */
    @Test
    public void openGoogleTest() {
        driver.get("https://www.google.com");
        System.out.println("Title is: " + driver.getTitle());
    }

    /*
     @AfterMethod
     This method runs after every test method
     Used for cleanup and closing browser
    */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();   // Close browser and end session
        }
    }
}
