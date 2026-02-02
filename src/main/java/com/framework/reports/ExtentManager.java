package com.framework.reports;

// Main Extent report class (controls report lifecycle)
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    // Static variable so only ONE ExtentReports instance exists (Singleton)
    private static ExtentReports extent;

    // Public method to return ExtentReports object
    // This method will be called from Listener
    public static ExtentReports getExtent() {

        // Create report object only once
        // If already created, reuse same instance
        if (extent == null) {

            // Get project root directory and define report path
            // Example: yourProject/reports/ExtentReport.html
            String reportPath = System.getProperty("user.dir")
                    + "/reports/ExtentReport.html";

            // Create Spark reporter (generates modern HTML report)
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            // Set report heading shown inside HTML report
            reporter.config().setReportName("Automation Test Results");

            // Set browser tab title
            reporter.config().setDocumentTitle("Framework Execution");

            // Create main ExtentReports object
            extent = new ExtentReports();

            // Attach Spark reporter to ExtentReports
            // Without this, report will not be generated
            extent.attachReporter(reporter);
        }

        // Return same ExtentReports instance everywhere in framework
        return extent;
    }
}
