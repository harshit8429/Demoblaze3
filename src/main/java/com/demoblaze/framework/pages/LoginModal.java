package com.demoblaze.framework.pages;

import com.demoblaze.framework.base.DriverFactory;
import com.demoblaze.framework.utils.ElementActions;
import com.demoblaze.framework.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModal {

    private final By usernameInput = By.id("loginusername");
    private final By passwordInput = By.id("loginpassword");
    private final By loginBtn = By.xpath("//button[text()='Log in']");
    private final By closeBtn = By.xpath("//div[@id='logInModal']//button[text()='Close']");

    public void login(String username, String password) {
        // Clear any existing input
        ElementActions.type(usernameInput, username);
        ElementActions.type(passwordInput, password);
        
        // Click login button
        ElementActions.click(loginBtn);
        
        // Wait for alert and accept it
        waitForAlertIfPresentAndAccept(5);
        
        // Wait for modal to close (indicates login process completed)
        waitForModalToClose();
    }

    private void waitForAlertIfPresentAndAccept(int timeoutSeconds) {
        try {
            new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent())
                .accept();
        } catch (TimeoutException ignored) {
            // No alert present, continue
        }
    }

    private void waitForModalToClose() {
        try {
            // Wait for the login modal to become invisible
            Waits.invisible(By.id("logInModal"));
        } catch (Exception e) {
            // Modal might already be closed, continue
        }
    }

    public void close() {
        ElementActions.click(closeBtn);
    }
}