package com.historycode.ui;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Epic;
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
import java.util.Arrays;
import java.util.List;

public class AdminPanelEditorPageTest extends BaseTestRunner {

    //TODO Add BeforeClass with login to the admin panel
    //TODO Add AfterMethod to move back to the main admin panel page
    public CategoriesPage admin_panel_login(WebDriver driver) {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/login");
        WebElement login = driver.findElement(By.xpath("//input[@id='login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        String oldUrl = driver.getCurrentUrl();
        sleep(4);
        login.sendKeys(testValueProvider.getAdminEmail());
        password.sendKeys(testValueProvider.getAdminPass());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        sleep(2);
        return new CategoriesPage(driver);
    }

    @Test
    @Issue("110")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verifyOpenAddContextModal() {
        CategoriesPage categoriesPage = admin_panel_login(driver); //TODO Change admiin_panel_login to Click Editor

        boolean actual = categoriesPage
                .moveToContexts()
                .clickAddContext()
                .isExist();
        Assert.assertTrue(actual);
    }

    @Test
    @Issue("106")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that context list is displayed")
    public void verifyContextGridIsCorrectDisplayed() {
        CategoriesPage categoriesPage = admin_panel_login(driver); //TODO Change admiin_panel_login to Click Editor

        ContextsPage contextsPage = categoriesPage.moveToContexts();

        List<String> expectedGridHeaders = Arrays.asList("Назва", "Дії");
        List<String> actualGridHeaders = contextsPage.getTableHeadersString();

        Assert.assertEquals(expectedGridHeaders, actualGridHeaders,
                "Current headers and expected are not same.");

        boolean actual = contextsPage.isGridDisplayed();
        Assert.assertTrue(actual,
                "Current rows are not displayed or are displayed incorrectly.");
    }

    @Test
    @Issue("104")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that tag list is displayed")
    public void verifyTagGridIsCorrectDisplayed() {
        CategoriesPage categoriesPage = admin_panel_login(driver); //TODO Change admiin_panel_login to Click Editor

        TagsPage tagsPage = categoriesPage.moveToTags();

        List<String> expectedGridHeaders = Arrays.asList("Назва", "Дії");
        List<String> actualGridHeaders = tagsPage.getTableHeadersString();

        Assert.assertEquals(expectedGridHeaders, actualGridHeaders,
                "Current headers and expected are not same.");

        boolean actual = tagsPage.isGridDisplayed();
        Assert.assertTrue(actual,
                "Current rows are not displayed or are displayed incorrectly.");
    }

    public void sleep(int scnd) {
        try {
            Thread.sleep((long) (scnd) * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

