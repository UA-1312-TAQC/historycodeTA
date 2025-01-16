package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeletePositionEditor extends TestRunnerWithAdmin {
    @BeforeMethod
    public void setupForDeleteJob() {
        login();
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        categoriesPage.moveToPositions()
                .addPosition()
                .enterPosition("Accountant").close();
    }

    @Test
    @Issue("108")
    @Description("Verify that the admin can delete position using the trash bin button")
    public void testDeleteJob() {
        // 1) Get the last created position from list
        // 2) Delete it
        System.out.println("testDeleteJob");
    }
}
