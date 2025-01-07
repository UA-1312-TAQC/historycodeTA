package com.historycode.ui.testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestRunnerWithAdmin extends BaseTestRunner{

    @BeforeMethod
    public void login(){
        driver.get(testValueProvider.getBaseUIUrl()+"/admin-panel");
        driver.get("https://frontend.historycode.online/admin-panel/login");
        WebElement passwordLabel = driver.findElement(By.xpath("//*[@id='root']/div/div[4]/div[2]/form/div[2]/div/div[1]/label"));
        driver.findElement(By.xpath("//*[@id='login']")).sendKeys(testValueProvider.getAdminEmail());
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testValueProvider.getAdminPass());
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        new Actions(driver).moveToElement(passwordLabel).moveByOffset(30, 70).click().perform();

        driver.findElement(By.xpath("//*[@id='root']/div/div[4]/div[2]/form/div[4]/div/div/div/div/button")).click();
    }
}
