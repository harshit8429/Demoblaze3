package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.pages.SignUpModal;
import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {
    
    private static String createdUsername;
    private static String createdPassword;
    
    @BeforeClass
    public void setUp() {
        // Initialize credentials for other tests to use
        createdUsername = "testuser_" + System.currentTimeMillis();
        createdPassword = "password123";
    }
    
    @Test(description = "TC_REG_001: Valid User Registration - Essential user registration functionality test", 
          groups = "signup")
    public void testValidUserRegistration() {
        // Precondition: User is on the home page
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Step 1: Click on "Sign up" link
        homePage.openSignUp();
        
        // Step 2 & 3: Enter valid username (unique) and password (minimum 6 characters)
        SignUpModal signUpModal = new SignUpModal();
        signUpModal.signUp(createdUsername, createdPassword);
        
        // Step 4: Click "Sign up" button and handle alert
        // The signUp method already handles the alert acceptance
        
        // Step 5 & 6: Verify success message appears and click "OK" on success alert
        // We can verify the registration was successful by checking if we can login with the new credentials
    }
    
    @Test(description = "TC_LOGIN_001: Login with newly created user - Essential login after registration test", 
          dependsOnMethods = "testValidUserRegistration", groups = "signup")
    public void testLoginAfterRegistration() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Login with the newly created credentials
        homePage.openLogin();
        LoginModal loginModal = new LoginModal();
        loginModal.login(createdUsername, createdPassword);
        
        // Verify successful login by checking if username appears in navbar
        String loggedInUser = ElementActions.text(By.id("nameofuser"));
        Assert.assertTrue(loggedInUser.contains(createdUsername), 
            "User login failed after registration!");
    }
    
    // Static methods to provide credentials to other test classes
    public static String getCreatedUsername() {
        return createdUsername;
    }
    
    public static String getCreatedPassword() {
        return createdPassword;
    }
}
