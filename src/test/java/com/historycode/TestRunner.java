package com.historycode;

import com.historycode.ui.page.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRunner {

    protected WebDriver driver;
    private static final String BROWSER = System.getProperty("browser", "chrome"); // firefox
    private static final String BASE_URL = "https://frontend.historycode.online/";

    @BeforeAll
    public void setupClass() {
        //System.out.println("Settings before running all tests...");
        if ("firefox".equalsIgnoreCase(BROWSER)) {
            WebDriverManager.firefoxdriver().setup();
        } else if ("chrome".equalsIgnoreCase(BROWSER)) {
            WebDriverManager.chromedriver().setup();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + BROWSER);
        }
    }

    @BeforeEach
    public void setupTest() {
        //System.out.println("Setting up the WebDriver for the test...");
        if ("firefox".equalsIgnoreCase(BROWSER)) {
            driver = new FirefoxDriver();
        } else if ("chrome".equalsIgnoreCase(BROWSER)) {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDownTest() {
        //System.out.println("Closing WebDriver after a test...");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public void tearDownClass() {

        //System.out.println("Ending test session...");
    }
}



