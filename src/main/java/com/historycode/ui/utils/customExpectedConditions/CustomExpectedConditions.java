package com.historycode.ui.utils.customExpectedConditions;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> StalenessOfElementLocatedBy(By locator) {
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    driver.findElement(locator);
                }catch(StaleElementReferenceException | NoSuchElementException ex){
                    return true;
                }
                return false;
            }
        };
    }
}
