package com.framework.tests;

import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.framework.constants.FrameworkConstants;
import com.framework.driver.DriverFactory;
import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.utils.AssertUtils;
import com.framework.utils.TestDataProvider;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void loginTest(String user, String pass) {

        LoginPage loginPage = new LoginPage();

        DashboardPage dashboard = loginPage.login(user, pass);

        if (user.equals("admin")) {
            AssertUtils.assertEquals(
                dashboard.getDashboardTitle(),
                FrameworkConstants.DASHBOARD_HEADER
            );
        } else {
            AssertUtils.assertEquals(
                loginPage.getErrorMessage(),
                FrameworkConstants.INVALID_LOGIN_MSG
            );
        }
    }
}
