package com.demoblaze.framework.pages;

import com.demoblaze.framework.utils.ElementActions;
import com.demoblaze.framework.utils.Waits;
import org.openqa.selenium.By;

public class HomePage {

    // Locators
    private final By loginLink = By.id("login2");
    private final By signupLink = By.id("signin2");
    private final By cartLink = By.id("cartur");
    private final By homeLink = By.xpath("//a[text()='Home ']");
    private final By loggedInUserElement = By.id("nameofuser");
    private final By logoutLink = By.id("logout2");

    private final By categoryPhones = By.xpath("//a[text()='Phones']");
    private final By categoryLaptops = By.xpath("//a[text()='Laptops']");
    private final By categoryMonitors = By.xpath("//a[text()='Monitors']");

    // Actions
    public void goToHome() {
        ElementActions.click(homeLink);
    }

    public void openLogin() {
        ElementActions.click(loginLink);
    }

    public void openSignUp() {
        ElementActions.click(signupLink);
    }

    public void openCart() {
        ElementActions.click(cartLink);
    }

    public String getLoggedInUsername() {
        try {
            // Wait for the element to be visible with a shorter timeout
            return Waits.visible(loggedInUserElement).getText().trim();
        } catch (Exception e) {
            // If element is not visible, user is not logged in
            return null;
        }
    }

    public boolean isUserLoggedIn() {
        try {
            // Check if logout link is visible (indicates user is logged in)
            boolean logoutVisible = ElementActions.isDisplayed(logoutLink);
            if (logoutVisible) {
                return true;
            }
            
            // Also check the nameofuser element
            String username = getLoggedInUsername();
            return username != null && !username.isEmpty() && !username.equals("Welcome");
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        try {
            if (isUserLoggedIn()) {
                ElementActions.click(logoutLink);
            }
        } catch (Exception e) {
            // Ignore logout errors
        }
    }

    public void selectPhonesCategory() {
        ElementActions.click(categoryPhones);
    }

    public void selectLaptopsCategory() {
        ElementActions.click(categoryLaptops);
    }

    public void selectMonitorsCategory() {
        ElementActions.click(categoryMonitors);
    }

    public void openProduct(String productName) {
        By product = By.xpath("//a[text()='" + productName + "']");
        ElementActions.click(product);
    }

    public boolean isProductVisible(String productName) {
        By product = By.xpath("//a[text()='" + productName + "']");
        return ElementActions.isDisplayed(product);
    }
}