package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;

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

public class EditorTagsTests extends BaseTestRunnerWithAdmin {

    @Step("Go to Editor page.")
    @BeforeMethod
    public void moveToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
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
        boolean actualGridState = tagsPage.isGridDisplayed();

        Assert.assertEquals(actualGridHeaders, expectedGridHeaders,
                "Current headers and expected are not same.");

        Assert.assertTrue(actualGridState,
                "Current rows are not displayed or are displayed incorrectly.");

    }

}
