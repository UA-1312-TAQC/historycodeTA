package com.historycode.ui;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AdminPanelEditorPageTest extends TestRunnerWithAdmin {

    //TODO Add BeforeClass with login to the admin panel
    //TODO Add AfterMethod to move back to the main admin panel page
    @BeforeMethod
    public void admin_panel_login() {
        login();
        sleep(1);
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        sleep(2);
    }

    @Test
    @Issue("110")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verifyOpenAddContextModal() {

        CategoriesPage categoriesPage = new CategoriesPage(driver);//TODO Change add Click Editor step

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
        CategoriesPage categoriesPage = new CategoriesPage(driver);//TODO Change add Click Editor step

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
        CategoriesPage categoriesPage = new CategoriesPage(driver);//TODO Change add Click Editor step

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

