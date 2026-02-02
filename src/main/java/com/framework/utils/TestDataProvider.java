package com.framework.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {

        return new Object[][] {
                {"admin", "admin123"},
                {"invalid", "invalid123"}
        };
    }
}
