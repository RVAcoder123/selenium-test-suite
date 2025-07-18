package com.projectfalcon.tests;

import com.projectfalcon.pages.CartPage;
import com.projectfalcon.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void testAddSingleItemToCart() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_item button"))).click();

        getDriver().findElement(By.className("shopping_cart_link")).click();

        CartPage cart = new CartPage(getDriver());
        int count = cart.getCartItemCount();
        Assertions.assertEquals(1, count, "Expected 1 item in the cart.");
    }

    @Test
    public void testAddMultipleItemsToCart() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".inventory_item button")));
        List<WebElement> buttons = getDriver().findElements(By.cssSelector(".inventory_item button"));
        buttons.get(0).click();
        buttons.get(1).click();

        getDriver().findElement(By.className("shopping_cart_link")).click();

        CartPage cart = new CartPage(getDriver());
        int count = cart.getCartItemCount();
        Assertions.assertEquals(2, count, "Expected 2 items in the cart.");
    }

    @Test
    public void testRemoveItemFromCart() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_item button")));
        addButton.click();

        getDriver().findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart_button"))).click();

        CartPage cart = new CartPage(getDriver());
        Assertions.assertEquals(0, cart.getCartItemCount(), "Cart should be empty after removal.");
    }

    @Test
    public void testCartItemNameMatches() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement firstItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".inventory_item")));
        String expectedName = firstItem.findElement(By.className("inventory_item_name")).getText();

        firstItem.findElement(By.tagName("button")).click();
        getDriver().findElement(By.className("shopping_cart_link")).click();

        CartPage cart = new CartPage(getDriver());
        String actualName = cart.getFirstCartItemName();

        Assertions.assertEquals(expectedName, actualName, "Item name in cart should match item added.");
    }

    @Test
    public void testEmptyCartShowsNoItems() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        getDriver().findElement(By.className("shopping_cart_link")).click();

        CartPage cart = new CartPage(getDriver());
        Assertions.assertEquals(0, cart.getCartItemCount(), "Cart should be empty on fresh login.");
    }
}
