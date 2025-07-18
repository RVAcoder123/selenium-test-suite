package com.projectfalcon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public DashboardPage submitLogin() {
        driver.findElement(loginButton).click();
        return new DashboardPage(driver);

    }
    public String getErrorMessage() {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }

}
