package com.historycode.ui;


import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTestRunner {

    @Test
    public void testOpenPage(){
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]")).isDisplayed());

    }
}
