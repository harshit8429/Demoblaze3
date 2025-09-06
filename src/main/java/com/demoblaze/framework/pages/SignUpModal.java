package com.demoblaze.framework.pages;

import com.demoblaze.framework.utils.ElementActions;
import com.demoblaze.framework.utils.Waits;
import org.openqa.selenium.By;

public class SignUpModal {

    private final By usernameInput = By.id("sign-username");
    private final By passwordInput = By.id("sign-password");
    private final By signupBtn = By.xpath("//button[text()='Sign up']");
    private final By closeBtn = By.xpath("//div[@id='signInModal']//button[text()='Close']");

    public void signUp(String username, String password) {
        ElementActions.type(usernameInput, username);
        ElementActions.type(passwordInput, password);
        ElementActions.click(signupBtn);
        Waits.alertPresent().accept(); // alert: "Sign up successful." or error
    }

    public void close() {
        ElementActions.click(closeBtn);
    }
}