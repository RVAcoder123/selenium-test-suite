package com.projectfalcon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private By inventoryContainer = By.id("inventory_container");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardVisible() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }
}
