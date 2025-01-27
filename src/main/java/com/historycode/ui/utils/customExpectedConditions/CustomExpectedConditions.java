package com.historycode.ui.utils.customExpectedConditions;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> stalenessOfElementLocatedBy(By locator) {
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                log.debug("Applying custom expected condition");
                try{
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                    driver.findElement(locator);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }catch(StaleElementReferenceException | NoSuchElementException ex){
                    log.debug("Expected condition is true: " + ex.getClass().toString() + " is thrown");
                    return true;
                }
                return false;
            }
        };
    }
}
