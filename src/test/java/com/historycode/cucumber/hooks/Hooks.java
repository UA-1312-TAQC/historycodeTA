package com.historycode.cucumber.hooks;

import com.historycode.TestValueProvider;
import com.historycode.cucumber.contexts.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {

    protected TestValueProvider provider = new TestValueProvider();

    @Before
    public void beforeScenario() {
        ScenarioContext.setDriver(initDriver());
    }

    @After()
    public void afterScenario() {
        if (ScenarioContext.getDriver() != null) {
            ScenarioContext.getDriver().quit();
        }
    }

    @Step("init ChromeDriver")
    public WebDriver initDriver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(provider.getImplicitlyWait()));
        return driver;
    }

}
