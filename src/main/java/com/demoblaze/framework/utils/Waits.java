package com.demoblaze.framework.utils;

import com.demoblaze.framework.base.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waits {
    private Waits() {}

    private static int timeoutSeconds() {
        String t = Config.get("explicitTimeoutSec");
        if (t == null || t.isBlank()) return 10;
        try { return Integer.parseInt(t); } catch (NumberFormatException e) { return 10; }
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds()));
    }

    public static WebElement visible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean invisible(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean urlContains(String fragment) {
        return getWait().until(ExpectedConditions.urlContains(fragment));
    }

    public static Alert alertPresent() {
        return getWait().until(ExpectedConditions.alertIsPresent());
    }
}

