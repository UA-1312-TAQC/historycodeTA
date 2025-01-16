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

    protected void clickDynamicElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

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

    public void waitUntilElementInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickWithJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
