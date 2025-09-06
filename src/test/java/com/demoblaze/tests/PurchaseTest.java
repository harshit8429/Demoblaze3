package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.CartPage;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.pages.PlaceOrderModal;
import com.demoblaze.framework.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    @BeforeMethod
    public void ensureUserLoggedIn() {
        // Ensure user is logged in before running purchase tests
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Check if user is already logged in
        try {
            String loggedInUser = homePage.getLoggedInUsername();
            if (loggedInUser == null || loggedInUser.isEmpty()) {
                // User not logged in, perform login
                homePage.openLogin();
                LoginModal loginModal = new LoginModal();
                loginModal.login(SignUpTest.getCreatedUsername(), SignUpTest.getCreatedPassword());
            }
        } catch (Exception e) {
            // If any error occurs, perform login
            homePage.openLogin();
            LoginModal loginModal = new LoginModal();
            loginModal.login(SignUpTest.getCreatedUsername(), SignUpTest.getCreatedPassword());
        }
    }

    @Test(description = "TC_PURCHASE_001: Verify complete purchase flow - Essential purchase functionality test", 
          dependsOnMethods = "com.demoblaze.tests.SignUpTest.testLoginAfterRegistration")
    public void testPurchaseProduct() {
        HomePage homePage = new HomePage();
        homePage.selectLaptopsCategory();
        homePage.openProduct("Sony vaio i5");

        ProductPage productPage = new ProductPage();
        productPage.addToCart();

        homePage.openCart();
        CartPage cartPage = new CartPage();
        cartPage.clickPlaceOrder();

        PlaceOrderModal orderModal = new PlaceOrderModal();
        orderModal.fillForm("John Doe", "USA", "New York",
                            "1234567812345678", "12", "2025");
        orderModal.purchase();

        String confirmation = orderModal.getConfirmationMessage();
        Assert.assertTrue(confirmation.contains("Thank you for your purchase!"), "Purchase failed!");

        orderModal.confirmOk();
    }
}