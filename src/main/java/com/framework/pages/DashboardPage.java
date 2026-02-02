package com.framework.pages;

import org.openqa.selenium.By;

import com.framework.driver.DriverFactory;
import com.framework.utils.WaitUtils;

/*
 * DashboardPage represents the home page after successful login.
 * This page object contains locators and actions related to Dashboard.
 */
public class DashboardPage {

    // Initialize WaitUtils using ThreadLocal driver from DriverFactory
    // This allows Explicit Waits for dashboard elements
    private WaitUtils wait = new WaitUtils(DriverFactory.getDriver());

    // Locator for Dashboard header text
    private By header = By.xpath("//h6");

    /*
     * Fetches Dashboard header text.
     * Explicit wait ensures header is visible before reading value.
     */
    public String getHeaderText() {
        return wait.waitForVisibility(header).getText();
    }
}
