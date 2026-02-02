package com.framework.pages;

import com.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/*
 * DashboardPage represents the home page after successful login.
 * This class follows proper Page Object Model design:
 * - Receives WebDriver via constructor (no DriverFactory usage here)
 * - Initializes WaitUtils using same driver
 * - Contains locators + actions for Dashboard
 */
public class DashboardPage {

    // WebDriver instance coming from LoginPage
    private WebDriver driver;

    // Explicit wait utility (uses same driver)
    private WaitUtils waitUtils;

    // Locator for Dashboard header text
    private By header = By.xpath("//h6");

    /*
     * Constructor
     * Receives driver from previous page (LoginPage)
     * Initializes WaitUtils and PageFactory
     */
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);   // IMPORTANT: same driver
        PageFactory.initElements(driver, this);
    }

    /*
     * Fetches Dashboard header text.
     * Waits until header becomes visible before reading text.
     */
    public String getHeaderText() {
        return waitUtils.waitForVisibility(header).getText();
    }
}
