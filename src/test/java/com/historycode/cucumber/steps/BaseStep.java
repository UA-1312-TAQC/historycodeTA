package com.historycode.cucumber.steps;

import com.historycode.TestValueProvider;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseStep {

    protected WebDriver driver;
    protected TestValueProvider provider = new TestValueProvider();


    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(provider.getImplicitlyWait()));
    }


//    @After
//    public void closeDriver(){
//        if (driver != null){
//            driver.quit();
//        }
//    }


}
