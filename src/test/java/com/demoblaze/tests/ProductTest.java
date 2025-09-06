package com.demoblaze.tests;

import com.demoblaze.framework.base.BaseTest;
import com.demoblaze.framework.pages.CartPage;
import com.demoblaze.framework.pages.HomePage;
import com.demoblaze.framework.pages.LoginModal;
import com.demoblaze.framework.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void ensureUserLoggedIn() {
        // Ensure user is logged in before running product tests
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
        }
    }

    @Test(description = "TC_PROD_001: Verify add to cart functionality - Essential product add to cart test")
    public void testAddToCartFromProductPage() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Navigate to a product and add to cart
        homePage.selectLaptopsCategory();
        homePage.openProduct("Sony vaio i5");
        
        ProductPage productPage = new ProductPage();
        productPage.addToCart();
        
        // Navigate to cart to verify item was added
        homePage.openCart();
        CartPage cartPage = new CartPage();
        
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue(itemCount > 0, "Product was not added to cart!");
    }

    @Test(description = "TC_PROD_002: Verify product categories contain expected products - Essential product category test")
    public void testProductCategoriesContent() {
        HomePage homePage = new HomePage();
        homePage.goToHome();
        
        // Test Phones category
        homePage.selectPhonesCategory();
        boolean samsungPhoneVisible = homePage.isProductVisible("Samsung galaxy s6");
        Assert.assertTrue(samsungPhoneVisible, "Samsung phone not found in Phones category!");
        
        // Test Laptops category
        homePage.selectLaptopsCategory();
        boolean sonyLaptopVisible = homePage.isProductVisible("Sony vaio i5");
        Assert.assertTrue(sonyLaptopVisible, "Sony laptop not found in Laptops category!");
    }
}
