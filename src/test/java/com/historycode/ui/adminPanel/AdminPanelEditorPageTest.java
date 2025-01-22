package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.testrunners.TestRunnerWithAdminEditor;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AdminPanelEditorPageTest extends TestRunnerWithAdminEditor {

    @Test
    @Issue("110")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verifyOpenAddContextModal() {

        boolean actual = new CategoriesPage(driver)
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

        ContextsPage contextsPage = new CategoriesPage(driver)
                .moveToContexts();
        List<String> expectedGridHeaders = Arrays.asList("Назва", "Дії");
        List<String> actualGridHeaders = contextsPage.getTableHeadersString();
        boolean actual = contextsPage.isGridDisplayed();
        Assert.assertEquals(expectedGridHeaders, actualGridHeaders,
                "Current headers and expected are not same.");
        Assert.assertTrue(actual,
                "Current rows are not displayed or are displayed incorrectly.");

    }

    @Test
    @Issue("104")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that tag list is displayed")
    public void verifyTagGridIsCorrectDisplayed() {

        TagsPage tagsPage = new CategoriesPage(driver)
                .moveToTags();
        List<String> expectedGridHeaders = Arrays.asList("Назва", "Дії");
        List<String> actualGridHeaders = tagsPage.getTableHeadersString();
        boolean actual = tagsPage.isGridDisplayed();
        Assert.assertEquals(expectedGridHeaders, actualGridHeaders,
                "Current headers and expected are not same.");
        Assert.assertTrue(actual,
                "Current rows are not displayed or are displayed incorrectly.");

    }
}
