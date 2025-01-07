package com.historycode.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Base {

    protected WebDriver driver;
    private WebDriverWait threadWait;
    private JavascriptExecutor threadJs;


    public Base(WebDriver driver) {
        this.driver = driver;
        this.threadWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    public void scrollToElement(WebElement element) {

        threadWait.until(ExpectedConditions.visibilityOf(element));
        threadJs.executeScript("arguments[0].scrollIntoView(true);", element);
        threadWait.until(ExpectedConditions.visibilityOf(element));

    }
    @Step("scroll to end of page")
    public void scrollToEndOfPage() {

        threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        sleep(1000);
    }

    public void sleep(long millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
