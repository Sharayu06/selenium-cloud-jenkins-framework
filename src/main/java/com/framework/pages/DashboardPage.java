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
 * DashboardPage
 */
public class DashboardPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(tagName = "h6")
    private WebElement dashboardHeader;

    public DashboardPage() {

        this.driver = DriverFactory.getDriver();

        if (driver == null) {
            throw new RuntimeException("Driver is NULL inside DashboardPage");
        }

        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }
    
    public String getDashboardTitle() {

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(dashboardHeader));

        return dashboardHeader.getText();
    }

}
