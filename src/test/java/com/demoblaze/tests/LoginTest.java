package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(description = "TC_LOGIN_002: Verify user login with newly created credentials - Essential login with fresh user test", 
          dependsOnMethods = "com.demoblaze.tests.SignUpTest.testLoginAfterRegistration", groups = "login")
    public void testLoginWithCreatedUser() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Login with the newly created credentials from SignUpTest
        homePage.openLogin();
        LoginModal loginModal = new LoginModal();
        loginModal.login(SignUpTest.getCreatedUsername(), SignUpTest.getCreatedPassword());
        
        // Verify successful login by checking if username appears in navbar
        String loggedInUser = ElementActions.text(By.id("nameofuser"));
        Assert.assertTrue(loggedInUser.contains(SignUpTest.getCreatedUsername()), 
            "User login failed with created credentials!");
    }
}
