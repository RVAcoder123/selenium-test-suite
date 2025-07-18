package com.projectfalcon.tests;

import com.projectfalcon.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        String currentUrl = getDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("inventory"), "Expected to land on inventory page.");
    }

    @Test
    public void testInvalidPasswordShowsError() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("wrong_password");
        login.submitLogin();

        String errorText = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
        Assertions.assertTrue(errorText.contains("Username and password do not match"), "Expected login error message to be shown.");
    }

    @Test
    public void testEmptyFieldsShowError() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("");
        login.enterPassword("");
        login.submitLogin();

        String errorText = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
        Assertions.assertTrue(errorText.contains("Username is required"), "Expected error for empty username.");
    }

    @Test
    public void testLockedOutUser() {
        LoginPage login = new LoginPage(getDriver());
        login.enterUsername("locked_out_user");
        login.enterPassword("secret_sauce");
        login.submitLogin();

        String errorText = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
        Assertions.assertTrue(errorText.contains("locked out"), "Expected locked out message.");
    }
}
