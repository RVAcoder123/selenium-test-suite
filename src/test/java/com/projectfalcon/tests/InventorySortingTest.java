package com.projectfalcon.tests;

import com.projectfalcon.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventorySortingTest extends BaseTest {

    @Test
    public void testSortByPriceLowToHigh() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        Select sortDropdown = new Select(getDriver().findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("lohi");

        List<Double> prices = getDriver()
                .findElements(By.className("inventory_item_price"))
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());

        List<Double> sorted = prices.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(sorted, prices, "Prices should be sorted low to high.");
    }

    @Test
    public void testSortByNameZToA() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        Select sortDropdown = new Select(getDriver().findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("za");

        List<String> names = getDriver()
                .findElements(By.className("inventory_item_name"))
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());

        List<String> sorted = names.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        Assertions.assertEquals(sorted, names, "Items should be sorted Z to A.");
    }
}
