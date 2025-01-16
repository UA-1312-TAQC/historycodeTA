package com.historycode.ui.utils.customExpectedConditions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class customExpectedConditions {

    public static ExpectedCondition<Boolean> StalenessOfElementLocatedBy(By locator) {
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    driver.findElement(locator);
                }catch(Exception ex){
                    return true;
                }
                return false;
            }
        };
    }
}
