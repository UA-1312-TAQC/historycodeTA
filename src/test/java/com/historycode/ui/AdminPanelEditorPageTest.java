package com.historycode.ui;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.loginpage.LoginPageAdminPanel;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminPanelEditorPageTest extends BaseTestRunner {

    public CategoriesPage admin_panel_login(WebDriver driver) {
        WebElement login = driver.findElement(By.xpath("//input[@id='login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        login.sendKeys(testValueProvider.getAdminEmail());
        sleep(5);
        password.sendKeys(testValueProvider.getAdminPass());
        sleep(40);
        button.click();
        sleep(20);
        driver.get("https://frontend.historycode.online/admin-panel/editor");
        sleep(5);
        return new CategoriesPage(driver);
    }

    @Test
    @Issue("110")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verify_create_context() {
        Assert.assertTrue(admin_panel_login(driver).moveToContexts().addContext().isExist());
    }

    public void sleep(int scnd) {
        try {
            Thread.sleep((long) (scnd) * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

