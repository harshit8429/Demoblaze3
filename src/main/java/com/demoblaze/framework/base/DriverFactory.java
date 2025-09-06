package com.demoblaze.framework.base;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {
    private DriverFactory() {}
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    public static void initDriver(String browser) {
        if (getDriver() != null) return;

        String br = (browser == null) ? "chrome" : browser.toLowerCase().trim();
        switch (br) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOpts = new EdgeOptions();
                eOpts.addArguments("--start-maximized");
                TL_DRIVER.set(new EdgeDriver(eOpts));
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOpts = new ChromeOptions();
                cOpts.addArguments("--start-maximized");
                TL_DRIVER.set(new ChromeDriver(cOpts));
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            try { getDriver().quit(); } catch (Exception ignored) {}
            TL_DRIVER.remove();
        }
    }
}
