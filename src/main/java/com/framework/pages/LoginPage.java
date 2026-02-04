package com.framework.pages;

import com.framework.driver.DriverFactory;
import com.framework.utils.WaitUtils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage
 * ---------
 * ThreadLocal compatible Page Object.
 * Driver is fetched directly from DriverFactory.
 */
public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//p[contains(@class,'alert-content-text')]")
    private WebElement errorMsg;

    /**
     * Constructor
     * -----------
     * Fetches ThreadLocal driver from DriverFactory.
     */

    public LoginPage() {

        this.driver = DriverFactory.getDriver();

        if (this.driver == null) {
            throw new RuntimeException("Driver is NULL inside LoginPage constructor");
        }

        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public DashboardPage login(String user, String pass) {

        waitUtils.waitForVisibility(username);

        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();

        return new DashboardPage();
    }

    public String getErrorMessage() {

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(errorMsg));

        return errorMsg.getText();
    }

}
