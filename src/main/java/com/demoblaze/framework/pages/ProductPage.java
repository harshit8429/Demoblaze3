package com.demoblaze.framework.pages;

import com.demoblaze.framework.utils.ElementActions;
import com.demoblaze.framework.utils.Waits;
import org.openqa.selenium.By;

public class ProductPage {

    private final By addToCartBtn = By.xpath("//a[text()='Add to cart']");
    private final By productTitle = By.xpath("//div[@class='name']/h2");

    public void addToCart() {
        ElementActions.click(addToCartBtn);
        Waits.alertPresent().accept(); // "Product added"
    }

    public String getProductTitle() {
        return ElementActions.text(productTitle);
    }
}