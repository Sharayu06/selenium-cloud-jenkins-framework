package com.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.framework.utils.ConfigReader;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (ConfigReader.getHeadless().equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }

            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            if (ConfigReader.getHeadless().equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }

            driver.set(new EdgeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (ConfigReader.getHeadless().equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }

            driver.set(new FirefoxDriver(options));
        }
    }


    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

