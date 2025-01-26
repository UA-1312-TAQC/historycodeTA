package com.historycode.ui;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Objects;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;
    protected static final Logger logger = LoggerFactory.getLogger(Base.class);

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Scroll to the element")
    public void scrollToElement(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        } catch (Exception e) {
            logger.error("Error scrolling to element", e);
            throw e;
        }
        waitUntilElementClickable(element);
    }

    @Step("Scroll to the end of the page")
    public void scrollToEndOfPage() {
        sleep(1000);
        try {
            threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        } catch (Exception e) {
            logger.error("Error scrolling to to the end of the page", e);
            throw e;
        }
    }

    protected boolean isContentTruncatedOrOverflow(WebElement element) {
        String script = "var element = arguments[0];" +
                "var computedStyle = window.getComputedStyle(element);" +
                "var isOverflowing = element.scrollHeight > element.clientHeight || element.scrollWidth > element.clientWidth;" +
                "var isTextOverflowing = computedStyle.overflow === 'hidden' || computedStyle.textOverflow === 'ellipsis' || computedStyle.whiteSpace === 'nowrap';" +
                "return isOverflowing && !isTextOverflowing;";

        Boolean isOverflowing;

        try {
            isOverflowing = (Boolean) threadJs.executeScript(script, element);
        } catch (Exception ex) {
            logger.error("Error checking if content is truncated or overflowing", ex);
            throw ex;
        }
        return isOverflowing != null && isOverflowing;
    }

    protected void clickDynamicElement(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            logger.error("Error clicking on element", e);
            throw e;
        }
    }

    public Point getCenterRelativeToBlock(WebElement block, WebElement element) {
        double blockLeft, blockTop, blockWidth, blockHeight, elementLeft, elementTop, elementWidth, elementHeight;
        int scrollX, scrollY;

        try {
            blockLeft = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().left;", block)))
                    .doubleValue();
            blockTop = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().top;", block)))
                    .doubleValue();
            blockWidth = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().width;", block)))
                    .doubleValue();
            blockHeight = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().height;", block)))
                    .doubleValue();

            elementLeft = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().left;", element)))
                    .doubleValue();
            elementTop = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().top;", element)))
                    .doubleValue();
            elementWidth = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().width;", element)))
                    .doubleValue();
            elementHeight = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().height;", element)))
                    .doubleValue();

            scrollX = ((Long) Objects.requireNonNull(threadJs.executeScript("return window.scrollX;"))).intValue();
            scrollY = ((Long) Objects.requireNonNull(threadJs.executeScript("return window.scrollY;"))).intValue();
        } catch (Exception e) {
            logger.error("Error getting center relative to block", e);
            throw e;
        }

        double blockCenterX = blockLeft + blockWidth / 2 - scrollX;
        double blockCenterY = blockTop + blockHeight / 2 - scrollY;
        double elementCenterX = elementLeft + elementWidth / 2 - scrollX;
        double elementCenterY = elementTop + elementHeight / 2 - scrollY;

        return new Point((int) (elementCenterX - blockCenterX), (int) (elementCenterY - blockCenterY));
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
