package com.historycode.ui.adminPanel.EditorPage;


import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;

import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorCategoriesTests extends BaseTestRunnerWithAdmin {

    private final String TEST_CATEGORY = "test_Category23";

    @Test
    @Issue("114")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify admin can create new category")
    public void verifyAdminCanCreateCategory() {

        CategoriesPage categoriesPage = new CategoriesPage(driver);
        boolean actual = false;

        categoriesPage
                .clickAddCategory()
                .enterCategory(TEST_CATEGORY)
                .save()
                .close();

        while (!actual && categoriesPage.tableHasNextPage()) {
            if (categoriesPage.getTableRowByTitle(TEST_CATEGORY) == null) {
                categoriesPage.clickNextPage();
            } else {
                actual = true;
            }
        }

        Assert.assertTrue(actual,
                "New category is not created.");

        //ToDo Add Photo uploading
        //ToDo Check will it work or not)
    }

    //ToDo Add After Class method to delete new category
}
