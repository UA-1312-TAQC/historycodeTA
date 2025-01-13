package com.historycode.ui.testrunners;

import com.historycode.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class TestRunnerAdminWithUserProfile {

    protected WebDriver driver;
    protected static TestValueProvider testValueProvider;

    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
    }

    @Step("init ChromeDriver with logged profile")
    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        String userDataDir = "C:\\Users\\Viktor\\AppData\\Local\\Google\\Chrome\\User Data\\";
        String profileDir = "Profile 3";
        options.addArguments("--disable-extensions");
        options.addArguments("--user-data-dir=C:/Users/Viktor/AppData/Local/Google/Chrome/User Data/");
        //options.addArguments("--user-data-dir=" + userDataDir);
        options.addArguments("--profile-directory=" + profileDir);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
        driver.get(testValueProvider.getBaseUIUrl() + "admin-panel");
    }

    @AfterMethod
    public void afterMethod(){
        if (driver != null) {
            driver.quit();
        }
    }

}
