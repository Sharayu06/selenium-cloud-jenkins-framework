package com.framework.utils;

import java.io.InputStream;
import java.util.Properties;

import com.framework.constants.FrameworkConstants;

/**
 * ConfigReader
 * ------------
 * This utility class is responsible for loading and reading
 * configuration values from config.properties file.
 *
 * The properties file is loaded from the classpath
 * (src/main/resources), which makes this solution
 * compatible with IDE, Maven, Jenkins, and CI/CD pipelines.
 */
public class ConfigReader {

    // Properties object to hold key-value pairs from config.properties
    private static Properties properties = new Properties();

    /**
     * Static block
     * ------------
     * This block executes once when the class is loaded.
     * It loads config.properties into the Properties object.
     */
    static {
        try {
            // Load config.properties from classpath
            InputStream inputStream =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream(FrameworkConstants.CONFIG_FILE);


            // If file is not found in classpath, fail fast
            if (inputStream == null) {
                throw new RuntimeException(
                        "config.properties not found in classpath (src/main/resources)"
                );
            }

            // Load properties into memory
            properties.load(inputStream);

        } catch (Exception e) {
            // Fail test execution if configuration cannot be loaded
            throw new RuntimeException(
                    "Failed to load config.properties", e
            );
        }
    }

    /**
     * getProperty
     * -----------
     * Returns the value for a given key from config.properties.
     *
     * @param key Property key (e.g., baseUrl, browser)
     * @return value corresponding to the key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
