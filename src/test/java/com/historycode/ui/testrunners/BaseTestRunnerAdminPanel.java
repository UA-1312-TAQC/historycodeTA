package com.historycode.ui.testrunners;

import com.historycode.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTestRunnerAdminPanel {

    protected static TestValueProvider testValueProvider;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
    }

    @Step("Init ChromeDriver.")
    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));

        moveToAdminPanel();
    }

    @Step("Move to Admin Panel.")
    public void moveToAdminPanel() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("AccessToken", testValueProvider.getAccessToken());
        localStorage.setItem("RefreshToken", testValueProvider.getRefreshToken());

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
    }

    @Step("Quit driver.")
    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

}
