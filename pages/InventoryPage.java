package com.projectfalcon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String value) {
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByValue(value);
    }

    public List<String> getItemNames() {
        List<WebElement> elements = driver.findElements(By.className("inventory_item_name"));
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

    public List<Double> getItemPrices() {
        List<WebElement> elements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement el : elements) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }
        return prices;
    }
}
