package com.historycode.ui.utils.customExpectedConditions;


import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class CustomExpectedConditions implements ExpectedCondition<Boolean> {

    String locator;

    public CustomExpectedConditions(String locator) {
        this.locator = locator;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        log.debug("Applying custom expected condition");
        Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        log.debug("Implicit wait " + timeout);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.findElement(By.cssSelector(this.locator));
        } catch (StaleElementReferenceException | NoSuchElementException ex) {
            log.debug("Expected condition is true: " + ex.getClass().toString() + " is thrown");
            return true;
        }finally {
            driver.manage().timeouts().implicitlyWait(timeout);
        }
        return false;
    }
}

