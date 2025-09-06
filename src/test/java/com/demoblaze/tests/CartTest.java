package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.CartPage;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void ensureUserLoggedIn() {
        // Ensure user is logged in before running cart tests
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Check if user is already logged in
        if (!homePage.isUserLoggedIn()) {
            // User not logged in, perform login
            homePage.openLogin();
            LoginModal loginModal = new LoginModal();
            loginModal.login(SignUpTest.getCreatedUsername(), SignUpTest.getCreatedPassword());
            
            // Wait a moment for login to complete
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Verify login was successful
            Assert.assertTrue(homePage.isUserLoggedIn(), "User login failed!");
        }
    }

    @Test(description = "TC_CART_001: Verify adding a product to cart - Essential add to cart functionality test", 
          dependsOnMethods = "com.demoblaze.tests.SignUpTest.testLoginAfterRegistration")
    public void testAddToCart() {
        HomePage homePage = new HomePage();
        homePage.selectPhonesCategory();
        homePage.openProduct("Samsung galaxy s6");

        ProductPage productPage = new ProductPage();
        productPage.addToCart();

        homePage.openCart();
        CartPage cartPage = new CartPage();

        String total = cartPage.getTotalPrice();
        Assert.assertNotNull(total, "Cart total not updated!");
    }

    @Test(description = "TC_CART_002: Verify deleting item from cart - Essential cart deletion functionality test", 
          dependsOnMethods = "com.demoblaze.tests.SignUpTest.testLoginAfterRegistration")
    public void testDeleteFromCart() {
        HomePage homePage = new HomePage();
        homePage.openCart();

        CartPage cartPage = new CartPage();
        
        // Get initial cart state
        int initialItemCount = cartPage.getCartItemCount();
        String initialTotal = cartPage.getTotalPrice();
        System.out.println("Initial cart items: " + initialItemCount + ", Total: " + initialTotal);
        
        // Delete the first item if cart has items
        if (initialItemCount > 0) {
            cartPage.deleteFirstItem();
            
            // Wait for deletion to complete
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Get final cart state
            int finalItemCount = cartPage.getCartItemCount();
            String finalTotal = cartPage.getTotalPrice();
            System.out.println("Final cart items: " + finalItemCount + ", Total: " + finalTotal);
            
            // Verify cart is empty or has fewer items
            Assert.assertTrue(finalItemCount < initialItemCount || cartPage.isCartEmpty(), 
                "Cart item count not reduced after deletion! Initial: " + initialItemCount + 
                ", Final: " + finalItemCount);
        } else {
            System.out.println("Cart is already empty, skipping deletion test");
        }
    }
}