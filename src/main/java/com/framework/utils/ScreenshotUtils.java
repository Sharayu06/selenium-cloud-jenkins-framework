package com.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {

        try {
        	System.out.println(">>> Screenshot method entered");
        	System.out.println(">>> Driver = " + driver);


            // Take screenshot from browser
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define screenshot path
            String path = System.getProperty("user.dir")
                    + "/screenshots/" + testName + ".png";

            File dest = new File(path);

            // Create screenshots directory if not exists
            dest.getParentFile().mkdirs();

            // Copy screenshot (overwrite if exists)
            System.out.println(">>> Saving screenshot to: " + path);

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
