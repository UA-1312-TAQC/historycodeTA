package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.testrunners.TestRunnerWithAdminEditor;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class EditorCategoriesTests extends TestRunnerWithAdminEditor {

    private final String TEST_CATEGORY = "test_Category23";

    @Test
    @Issue("114")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify admin can create new category")
    public void verifyAdminCanCreateCategory() {

        new CategoriesPage(driver)
                .clickAddCategory()
                .enterCategory(TEST_CATEGORY)
                .save()
                .close();

        //ToDo Add Pagination
        //ToDo Add Search in grids
        //ToDo Add Assert is new category exists
    }

    //ToDo Add After Class method to delete new category
}
