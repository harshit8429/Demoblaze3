package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavbarTest extends BaseTest {

    @Test(description = "TC_NAVBAR_001: Verify banner visibility - Essential banner display test")
    public void testBannerVisibility() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Check if banner is visible on the home page
        boolean bannerVisible = ElementActions.isDisplayed(By.xpath("//div[@id='carouselExampleIndicators']"));
        Assert.assertTrue(bannerVisible, "Banner is not visible on the home page!");
        
        // Additional check for banner content
        boolean bannerContentVisible = ElementActions.isDisplayed(By.xpath("//div[@class='carousel-inner']"));
        Assert.assertTrue(bannerContentVisible, "Banner content is not visible!");
    }

    @Test(description = "TC_NAVBAR_002: Verify Contact button functionality - Essential contact button test")
    public void testContactButton() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Click on Contact button
        ElementActions.click(By.xpath("//a[text()='Contact']"));
        
        // Wait for contact modal to appear
        WebDriverWait wait = new WebDriverWait(com.demoblaze.framework.base.DriverFactory.getDriver(), Duration.ofSeconds(10));
        boolean contactModalVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal"))).isDisplayed();
        
        Assert.assertTrue(contactModalVisible, "Contact modal did not open after clicking Contact button!");
        
        // Verify contact modal title
        String modalTitle = ElementActions.text(By.xpath("//div[@id='exampleModal']//h5"));
        Assert.assertTrue(modalTitle.contains("New message"), "Contact modal title is incorrect!");
    }

    @Test(description = "TC_NAVBAR_003: Verify About us button functionality - Essential about us button test")
    public void testAboutUsButton() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Click on About us button
        ElementActions.click(By.xpath("//a[text()='About us']"));
        
        // Wait for about us modal to appear
        WebDriverWait wait = new WebDriverWait(com.demoblaze.framework.base.DriverFactory.getDriver(), Duration.ofSeconds(10));
        boolean aboutUsModalVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("videoModal"))).isDisplayed();
        
        Assert.assertTrue(aboutUsModalVisible, "About us modal did not open after clicking About us button!");
        
        // Verify about us modal title
        String modalTitle = ElementActions.text(By.xpath("//div[@id='videoModal']//h5"));
        Assert.assertTrue(modalTitle.contains("About us"), "About us modal title is incorrect!");
    }

//    @Test(description = "TC_NAVBAR_004: Verify Contact form validation - Essential contact form test")
//    public void testContactFormValidation() {
//        HomePage homePage = new HomePage();
//        homePage.goToHome();
//        
//        // Open contact modal
//        ElementActions.click(By.xpath("//a[text()='Contact']"));
//        
//        // Wait for contact modal to appear
//        WebDriverWait wait = new WebDriverWait(com.demoblaze.framework.base.DriverFactory.getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
//        
//        // Test 1: Verify contact form fields are present
//        boolean contactEmailField = ElementActions.isDisplayed(By.id("recipient-email"));
//        boolean contactNameField = ElementActions.isDisplayed(By.id("recipient-name"));
//        boolean contactMessageField = ElementActions.isDisplayed(By.id("message-text"));
//        boolean sendMessageButton = ElementActions.isDisplayed(By.xpath("//button[text()='Send message']"));
//        
//        Assert.assertTrue(contactEmailField, "Contact email field is not present!");
//        Assert.assertTrue(contactNameField, "Contact name field is not present!");
//        Assert.assertTrue(contactMessageField, "Contact message field is not present!");
//        Assert.assertTrue(sendMessageButton, "Send message button is not present!");
//        
//        // Test 2: Fill contact form with valid data
//        ElementActions.sendKeys(By.id("recipient-name"), "Test User");
//        ElementActions.sendKeys(By.id("recipient-email"), "test@example.com");
//        ElementActions.sendKeys(By.id("message-text"), "This is a test message for contact form validation.");
//        
//        // Test 3: Verify form data is entered correctly
//        String enteredName = ElementActions.getAttribute(By.id("recipient-name"), "value");
//        String enteredEmail = ElementActions.getAttribute(By.id("recipient-email"), "value");
//        String enteredMessage = ElementActions.getAttribute(By.id("message-text"), "value");
//        
//        Assert.assertEquals(enteredName, "Test User", "Name field value is incorrect!");
//        Assert.assertEquals(enteredEmail, "test@example.com", "Email field value is incorrect!");
//        Assert.assertEquals(enteredMessage, "This is a test message for contact form validation.", "Message field value is incorrect!");
//        
//        // Test 4: Click send message button
//        ElementActions.click(By.xpath("//button[text()='Send message']"));
//        
//        // Wait for success alert
//        boolean alertPresent = wait.until(ExpectedConditions.alertIsPresent()) != null;
//        Assert.assertTrue(alertPresent, "Success alert did not appear after sending message!");
//        
//        // Accept the alert
//        com.demoblaze.framework.base.DriverFactory.getDriver().switchTo().alert().accept();
//    }

    @Test(description = "TC_NAVBAR_005: Verify navbar elements visibility - Essential navbar elements test")
    public void testNavbarElementsVisibility() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Check if all navbar elements are visible
        boolean homeLink = ElementActions.isDisplayed(By.xpath("//a[text()='Home ']"));
        boolean contactLink = ElementActions.isDisplayed(By.xpath("//a[text()='Contact']"));
        boolean aboutUsLink = ElementActions.isDisplayed(By.xpath("//a[text()='About us']"));
        boolean cartLink = ElementActions.isDisplayed(By.xpath("//a[text()='Cart']"));
        boolean loginLink = ElementActions.isDisplayed(By.xpath("//a[text()='Log in']"));
        boolean signUpLink = ElementActions.isDisplayed(By.xpath("//a[text()='Sign up']"));
        
        Assert.assertTrue(homeLink, "Home link is not visible in navbar!");
        Assert.assertTrue(contactLink, "Contact link is not visible in navbar!");
        Assert.assertTrue(aboutUsLink, "About us link is not visible in navbar!");
        Assert.assertTrue(cartLink, "Cart link is not visible in navbar!");
        Assert.assertTrue(loginLink, "Login link is not visible in navbar!");
        Assert.assertTrue(signUpLink, "Sign up link is not visible in navbar!");
    }
}
