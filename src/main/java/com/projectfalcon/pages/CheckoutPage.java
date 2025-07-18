package com.projectfalcon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutPage extends BasePage {

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");
    private By successMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public void mockEnterPaymentDetails(String cardNum, String expDate, String cvv) {
        // This is mocked because SauceDemo doesn’t have payment fields.
        System.out.println("Mock Payment: Card " + cardNum + ", Exp " + expDate + ", CVV " + cvv);
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void cancelCheckout() {
        driver.findElement(cancelButton).click();
    }

    public void captureSuccessScreenshot(String folderPath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File destFile = new File(folderPath + "/order_success_" + timestamp + ".png");

        try {
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}
