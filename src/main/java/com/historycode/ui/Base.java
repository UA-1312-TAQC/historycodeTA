package com.historycode.ui;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Scroll to the element")
    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        threadJs.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Scroll to the end of the page")
    public void scrollToEndOfPage() {
        threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        sleep(1000);
    }

    protected boolean isContentTruncatedOrOverflow(WebElement element) {
        String script = "var element = arguments[0];" +
                "var computedStyle = window.getComputedStyle(element);" +
                "var isOverflowing = element.scrollHeight > element.clientHeight || element.scrollWidth > element.clientWidth;" +
                "var isTextOverflowing = computedStyle.overflow === 'hidden' || computedStyle.textOverflow === 'ellipsis' || computedStyle.whiteSpace === 'nowrap';" +
                "return isOverflowing && !isTextOverflowing;";
        Boolean isOverflowing = (Boolean) threadJs.executeScript(script, element);
        return isOverflowing != null && isOverflowing;
    }

    /**
     * Clicks a dynamic web element using JavaScript execution.
     *
     * @param element The WebElement to be clicked
     * @throws WebDriverException if the element cannot be clicked via JavaScript
     */
    protected void clickDynamicElement(WebElement element) {
        threadJs.executeScript("arguments[0].click();", element);
    }

    /**
     * Pauses the current thread execution for a specified duration.
     *
     * @param millisSeconds the number of milliseconds to pause the thread
     * @throws InterruptedException if the thread is interrupted during sleep (caught and handled internally)
     */
    public void sleep(long millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
