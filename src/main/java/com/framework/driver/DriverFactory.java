package com.framework.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.framework.utils.ConfigReader;

/**
 * DriverFactory is responsible for:
 *  - Creating WebDriver instances
 *  - Managing WebDriver lifecycle
 *  - Supporting parallel execution using ThreadLocal
 *
 * All tests will get WebDriver instance from this class.
 */
public class DriverFactory {

    /**
     * ThreadLocal ensures that each test thread
     * gets its own separate WebDriver instance.
     *
     * This is mandatory for parallel execution
     * to avoid thread-safety issues.
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return WebDriver for current test thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Initializes WebDriver based on browser value
     * provided in config.properties.
     *
     * Supported browsers:
     *  - chrome
     *  - firefox
     *  - edge
     */
    public static void initDriver() {

        // Read browser name from configuration
        String browser = ConfigReader.getProperty("browser");

        // Fail fast if browser is not defined
        if (browser == null) {
            throw new RuntimeException("Browser value is not set in config.properties");
        }

        // Create driver based on browser type
        switch (browser.toLowerCase()) {

            case "chrome":
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                driver.set(new FirefoxDriver());
                break;

            case "edge":
                driver.set(new EdgeDriver());
                break;

            default:
                throw new RuntimeException("Invalid browser name: " + browser);
        }

        // Maximize browser window
        getDriver().manage().window().maximize();

        // Apply implicit wait from configuration
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.getProperty("implicitWait")
                        )
                )
        );
    }

    /**
     * Quits the WebDriver instance and
     * removes it from ThreadLocal.
     *
     * This prevents memory leaks and
     * ensures clean execution.
     */
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
