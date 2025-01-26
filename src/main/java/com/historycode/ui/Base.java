package com.historycode.ui;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;
    private static final int SCROLL_STABILIZATION_DELAY = 500;
    private static final Logger logger = LoggerFactory.getLogger(Base.class);

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
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            Thread.sleep(SCROLL_STABILIZATION_DELAY); // Коротка пауза для стабільності
        } catch (Exception e) {
            logger.error("Error scrolling to element", e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Scroll to the middle of the page")
    public void scrollToMiddlePage() {
        Number startY = (Number) threadJs.executeScript("return window.pageYOffset;");
        threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

        wait.until(driver -> {
            Number currentY = (Number) threadJs.executeScript("return window.pageYOffset;");
            return currentY.doubleValue() != startY.doubleValue();
        });
    }

    @Step("Scroll to the end of the page")
    public void scrollToEndOfPage() {
        sleep(1000);
        threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
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
        threadJs.executeScript("arguments[0].click();", element);
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

    public void waitUntilElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
