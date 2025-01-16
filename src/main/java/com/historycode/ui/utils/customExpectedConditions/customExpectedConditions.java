package com.historycode.ui.utils.customExpectedConditions;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class customExpectedConditions {

    public static ExpectedCondition<Boolean> StalenessOfElementLocatedBy(By locator) {
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    //TODO change this
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                    System.out.println("applying");
                    driver.findElement(locator);
                }catch(Exception ex){
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    return true;
                }
                return false;
            }
        };
    }
}
