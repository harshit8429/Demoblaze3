package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "TC_NAV_001: Verify home page navigation - Essential home page navigation test")
    public void testHomePageNavigation() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Verify we're on the home page by checking for home page elements
        boolean homePageVisible = ElementActions.isDisplayed(By.xpath("//a[text()='Home ']"));
        Assert.assertTrue(homePageVisible, "Home page navigation failed!");
    }

    @Test(description = "TC_NAV_002: Verify category navigation - Essential category navigation test")
    public void testCategoryNavigation() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Test Phones category
        homePage.selectPhonesCategory();
        boolean phonesCategoryVisible = ElementActions.isDisplayed(By.xpath("//a[text()='Samsung galaxy s6']"));
        Assert.assertTrue(phonesCategoryVisible, "Phones category navigation failed!");
        
        // Test Laptops category
        homePage.selectLaptopsCategory();
        boolean laptopsCategoryVisible = ElementActions.isDisplayed(By.xpath("//a[text()='Sony vaio i5']"));
        Assert.assertTrue(laptopsCategoryVisible, "Laptops category navigation failed!");
    }
}
