package com.demoblaze.framework.base;

import com.demoblaze.framework.utils.Config;
import org.testng.annotations.*;

@Listeners(com.demoblaze.framework.utils.TestListener.class)
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        String br = (browser == null || browser.isBlank()) ? Config.get("browser") : browser;
        if (br == null || br.isBlank()) br = "chrome";
        DriverFactory.initDriver(br);
        DriverFactory.getDriver().get(Config.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}