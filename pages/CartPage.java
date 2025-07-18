package com.projectfalcon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartItems = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstCartItemName() {
        return driver.findElement(cartItems).getText();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}
