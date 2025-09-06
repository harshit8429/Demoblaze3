package com.demoblaze.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demoblaze.framework.constants.FrameworkConstants;

public final class ExtentManager {
    private static ExtentReports extent;
    private ExtentManager() {}

    public static synchronized ExtentReports get() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.REPORT_PATH);
            spark.config().setDocumentTitle("Demoblaze Automation Report");
            spark.config().setReportName("Demoblaze UI Tests");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}