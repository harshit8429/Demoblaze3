package com.demoblaze.framework.pages;

import com.demoblaze.framework.utils.ElementActions;
import org.openqa.selenium.By;

public class PlaceOrderModal {

    private final By nameInput = By.id("name");
    private final By countryInput = By.id("country");
    private final By cityInput = By.id("city");
    private final By cardInput = By.id("card");
    private final By monthInput = By.id("month");
    private final By yearInput = By.id("year");

    private final By purchaseBtn = By.xpath("//button[text()='Purchase']");
    private final By closeBtn = By.xpath("//div[@id='orderModal']//button[text()='Close']");

    private final By confirmationText = By.xpath("//div[@class='sweet-alert  showSweetAlert visible']/h2");
    private final By okBtn = By.xpath("//button[text()='OK']");

    public void fillForm(String name, String country, String city,
                         String card, String month, String year) {
        ElementActions.type(nameInput, name);
        ElementActions.type(countryInput, country);
        ElementActions.type(cityInput, city);
        ElementActions.type(cardInput, card);
        ElementActions.type(monthInput, month);
        ElementActions.type(yearInput, year);
    }

    public void purchase() {
        ElementActions.click(purchaseBtn);
    }

    public String getConfirmationMessage() {
        return ElementActions.text(confirmationText);
    }

    public void confirmOk() {
        ElementActions.click(okBtn);
    }

    public void close() {
        ElementActions.click(closeBtn);
    }
}