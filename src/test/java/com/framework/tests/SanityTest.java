package com.framework.tests;

// TestNG imports
import org.testng.Assert;
import org.testng.annotations.Test;

// Import base test to inherit setup and teardown
import com.framework.base.BaseTest;
// Import DriverFactory to access WebDriver instance
import com.framework.driver.DriverFactory;

/**
 * SanityTest
 * -----------
 * This class contains sanity-level test cases
 * to verify basic application health.
 */
public class SanityTest extends BaseTest {

    /**
     * Verifies that the application launches successfully
     * and page title is not null.
     */
    @Test
    public void verifyApplicationLaunch() {

        // Get page title after browser launch
        String title = DriverFactory.getDriver().getTitle();
        System.out.println("Page Title is: " + title);

        // Validate that title is not null
        Assert.assertNotNull(title, "Page title should not be null");
    }
}
