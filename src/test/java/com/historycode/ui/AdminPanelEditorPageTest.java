package com.historycode.ui;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminPanelEditorPageTest extends BaseTestRunner {

    public CategoriesPage admin_panel_login(WebDriver driver) {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/login");
        WebElement login = driver.findElement(By.xpath("//input[@id='login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        String oldUrl = driver.getCurrentUrl();
        login.sendKeys(testValueProvider.getAdminEmail());

        password.sendKeys(testValueProvider.getAdminPass());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
        driver.get(testValueProvider.getBaseUIUrl() +"/admin-panel/editor");
        sleep(3);
        return new CategoriesPage(driver);
    }

    @Test
    @Issue("110")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verify_create_context() {
        boolean result = admin_panel_login(driver)
                .moveToContexts()
                .clickAddContext()
                .isExist();
        Assert.assertTrue(result);
    }

    public void sleep(int scnd) {
        try {
            Thread.sleep((long) (scnd) * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

