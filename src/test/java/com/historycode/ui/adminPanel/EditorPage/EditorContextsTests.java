package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EditorContextsTests extends BaseTestRunnerWithAdmin {

    @Step("Go to Editor page.")
    @BeforeMethod
    public void moveToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
    }

    @Test
    @Issue("110")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that a new context can be created in the admin panel editor")
    public void verifyOpenAddContextModal() {

        boolean actual = new CategoriesPage(driver)
                .moveToContexts()
                .clickAddContext()
                .isExist();

        Assert.assertTrue(actual,
                "The modal window for adding a new context is not displayed.");

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

        Assert.assertEquals(expectedGridHeaders, actualGridHeaders,
                "Current headers and expected are not same.");

        Assert.assertTrue(contextsPage.isGridDisplayed(),
                "Current rows are not displayed or are displayed incorrectly.");

    }

}
