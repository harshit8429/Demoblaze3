package com.demoblaze.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class ElementActions {
    private ElementActions() {}

    public static void click(By locator) {
        WebElement el = Waits.clickable(locator);
        el.click();
    }

    public static void type(By locator, String text) {
        WebElement el = Waits.visible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public static String text(By locator) {
        return Waits.visible(locator).getText().trim();
    }
    

    public static boolean isDisplayed(By locator) {
        try {
            return Waits.visible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}