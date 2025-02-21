package com.historycode.ui.adminPanel.EditorPage;


import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;

import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditorCategoriesTests extends BaseTestRunnerWithAdmin {

    private final String TEST_CATEGORY = "test_Category23";

    @Step("Go to Editor page.")
    @BeforeMethod
    public void moveToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
    }

    @Test
    @Issue("114")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify admin can create new category")
    public void verifyAdminCanCreateCategory() {

        new CategoriesPage(driver)
                .clickAddCategory()
                .enterCategory(TEST_CATEGORY)
                .enterImage("uploadfiles/cat.png")
                .save()
                .close();

        Assert.assertNotNull(new CategoriesPage(driver)
                .moveToPageWithRow(TEST_CATEGORY)
                .getTableRowByTitle(TEST_CATEGORY));

        //ToDo Add assert for image
    }

    @Step("Cleanup data")
    @AfterClass
    public void cleanUp() {
        new CategoriesPage(driver)
                .deleteCategory(TEST_CATEGORY);
    }

}
