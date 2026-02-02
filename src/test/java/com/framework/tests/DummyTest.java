package com.framework.tests;

import com.framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.framework.listeners.TestListener;

@Listeners(TestListener.class)
public class DummyTest extends BaseTest {

    @Test
    public void failingTest() {

        driver.get("https://google.com");

        Assert.assertTrue(false);
    }

}
