package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest extends BaseTest {

    @Test(description = "TC_NEG_001: Verify login with invalid username - Essential negative login test")
    public void testLoginWithInvalidUsername() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Attempt login with invalid username
        homePage.openLogin();
        LoginModal loginModal = new LoginModal();
        loginModal.login("invaliduser123", "password123");
        
        // Verify user is not logged in
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in with invalid username!");
    }
}