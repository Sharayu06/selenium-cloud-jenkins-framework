package com.framework.pages;

import com.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.framework.pages.DashboardPage;
import org.openqa.selenium.By;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    @FindBy(xpath = "//p[contains(@class,'alert-content-text')]")
    private WebElement errorMsg;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);   // <<< IMPORTANT
        PageFactory.initElements(driver, this);
    }

    // OrangeHRM uses NAME attribute
    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public DashboardPage login(String user, String pass) {

        waitUtils.waitForVisibility(username);

        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();

        return new DashboardPage();
    }
    public String getErrorMessage() {
        return waitUtils.waitForVisibility(errorMsg).getText();
    }

}
