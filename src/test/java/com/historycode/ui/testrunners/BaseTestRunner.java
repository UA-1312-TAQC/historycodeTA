package com.historycode.ui.testrunners;

import com.historycode.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTestRunner {
    protected WebDriver driver;
    protected static TestValueProvider testValueProvider;

    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
    }

    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
        driver.get(testValueProvider.getBaseUIUrl());
    }

    @AfterMethod
    public void afterMethod(){
        if (driver != null) {
            driver.quit();
        }
    }
}
