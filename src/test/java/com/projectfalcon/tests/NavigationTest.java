package com.projectfalcon.tests;

import com.projectfalcon.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationTest extends BaseTest {

    @Test
    public void testNavigateToAboutPage() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        getDriver().findElement(By.id("react-burger-menu-btn")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("about_sidebar_link")))
                .click();

        String currentUrl = getDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("saucelabs.com"), "Expected to be redirected to Sauce Labs About page.");
    }

    @Test
    public void testLogoutReturnsToLoginPage() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        getDriver().findElement(By.id("react-burger-menu-btn")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();

        // Instead of URL check, wait for the login field to reappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));

        Assertions.assertTrue(
                getDriver().findElement(By.id("login-button")).isDisplayed(),
                "Expected to be on login page after logout."
        );
    }



    @Test
    public void testProductDetailPageOpens() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        getDriver().findElement(By.className("inventory_item_name")).click();

        String currentUrl = getDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("inventory-item"), "Expected to be on a product detail page.");
    }
}
