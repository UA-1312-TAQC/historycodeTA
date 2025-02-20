package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;

import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EditorTagsTests extends BaseTestRunnerWithAdmin {

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
        Assert.assertEquals(actualGridHeaders, expectedGridHeaders,
                "Current headers and expected are not same.");
        Assert.assertTrue(actual,
                "Current rows are not displayed or are displayed incorrectly.");

    }

}
