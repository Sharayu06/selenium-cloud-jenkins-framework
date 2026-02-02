package com.framework.utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertUtils {

    private static SoftAssert softAssert = new SoftAssert();

    // Hard assert
    public static void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    // Soft assert
    public static void softAssertEquals(String actual, String expected) {
        softAssert.assertEquals(actual, expected);
    }

    // Call at end of test when using soft asserts
    public static void assertAll() {
        softAssert.assertAll();
    }
}
