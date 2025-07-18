package com.projectfalcon.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.UUID;


import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Use a unique, truly fresh profile path each time
        String tempProfile = System.getProperty("java.io.tmpdir") + "chrome-profile-" + UUID.randomUUID();
        options.addArguments("--user-data-dir=" + tempProfile);

        // Disable password manager and all notifications
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("credentials_enable_autosignin", false);
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        prefs.put("profile.default_content_setting_values.geolocation", 2);

        options.setExperimentalOption("prefs", prefs);

        // Hardcore "leave me alone" mode
        options.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--disable-infobars",
                "--disable-extensions",
                "--disable-save-password-bubble",
                "--disable-notifications",
                "--no-default-browser-check",
                "--no-first-run",
                "--password-store=basic",
                "--disable-popup-blocking",
                "--incognito"
        );

        WebDriver driver = new ChromeDriver(options);
        threadDriver.set(driver);

        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            threadDriver.remove();
        }
    }
}
