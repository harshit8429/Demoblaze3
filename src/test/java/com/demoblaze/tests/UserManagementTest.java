package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.pages.SignUpModal;
import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserManagementTest extends BaseTest {

    private String testUsername;
    private String testPassword;

    @BeforeMethod
    public void setUp() {
        // Generate unique credentials for each test
        testUsername = "testuser_" + System.currentTimeMillis();
        testPassword = "password123";
    }

    @AfterMethod
    public void tearDown() {
        // Logout after each test
        HomePage homePage = new HomePage();
        homePage.logout();
    }

    @Test(description = "TC_USER_001: Verify user registration with unique username - Essential user registration test")
    public void testUserRegistrationWithUniqueUsername() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Open signup modal
        homePage.openSignUp();
        
        // Fill registration form
        SignUpModal signUpModal = new SignUpModal();
        signUpModal.signUp(testUsername, testPassword);
        
        // Verify registration was successful by attempting to login
        homePage.openLogin();
        LoginModal loginModal = new LoginModal();
        loginModal.login(testUsername, testPassword);
        
        // Verify user is logged in
        String loggedInUser = ElementActions.text(By.id("nameofuser"));
        Assert.assertTrue(loggedInUser.contains(testUsername), "User registration failed!");
    }

    @Test(description = "TC_USER_002: Verify user logout functionality - Essential user logout test")
    public void testUserLogout() {
        // First login a user
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        homePage.openSignUp();
        SignUpModal signUpModal = new SignUpModal();
        signUpModal.signUp(testUsername, testPassword);
        
        homePage.openLogin();
        LoginModal loginModal = new LoginModal();
        loginModal.login(testUsername, testPassword);
        
        // Verify user is logged in
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in before logout test!");
        
        // Now test logout
        homePage.logout();
        
        // Verify user is logged out
        Assert.assertFalse(homePage.isUserLoggedIn(), "User logout failed!");
    }
}
