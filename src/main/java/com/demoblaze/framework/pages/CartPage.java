package com.demoblaze.framework.pages;

import com.demoblaze.framework.utils.ElementActions;
import com.demoblaze.framework.utils.Waits;
import org.openqa.selenium.By;

public class CartPage {

    private final By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private final By deleteItemLink = By.xpath("//a[text()='Delete']");
    private final By totalPrice = By.id("totalp");
    private final By cartItems = By.xpath("//tbody[@id='tbodyid']/tr");

    public void deleteFirstItem() {
        // Check if there are items to delete
        if (getCartItemCount() > 0) {
            ElementActions.click(deleteItemLink);
            // Wait for the item to be removed
            Waits.invisible(deleteItemLink);
        }
    }

    public String getTotalPrice() {
        try {
            String total = ElementActions.text(totalPrice);
            return total != null ? total.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    public int getCartItemCount() {
        try {
            return Waits.visible(cartItems).findElements(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }

    public void clickPlaceOrder() {
        ElementActions.click(placeOrderBtn);
    }
}