package com.framework.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This class contains reusable explicit wait methods.
 * Purpose:
 * - Centralize all waits
 * - Avoid Thread.sleep()
 * - Improve test stability
 */
public class WaitUtils {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor initializes WebDriverWait with timeout
    public WaitUtils(WebDriver driver) {
        this.driver = driver;

        String waitTime = ConfigReader.getProperty("explicitWait");

        if (waitTime == null) {
            throw new RuntimeException("explicitWait is missing in config.properties");
        }

        wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(waitTime)));
        System.out.println("ExplicitWait value = " + waitTime);
    }


    // Wait until element is visible
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is clickable
    public WebElement waitForClick(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait until title contains text
    public void waitForTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
    
 // Wait until WebElement is visible (PageFactory support)
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until WebElement is clickable (PageFactory support)
    public WebElement waitForClick(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
