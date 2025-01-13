package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeletePositionEditor extends TestRunnerWithAdmin {
    @BeforeMethod
    public void setupForDeleteJob() throws InterruptedException {
        login();
        driver.get(testValueProvider.getBaseUIUrl() + "admin-panel/editor");
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        categoriesPage.moveToPositions()
                .addPosition()
                .inputNewPosition("Дизайнер")
                .saveNewPosition()
                .closeModal();
    }

    @Test
    @Issue("108")
    @Description("Verify that the admin can delete position using the trash bin button")
    public void testDeleteJob() {
//        PositionsPage positionsPage = new PositionsPage(driver);
//        PositionsRowComponent positionToDelete = positionsPage.getTableRowByTitle("Дизайнер");
//        positionsPage.deleteTableRow(positionToDelete)
//                .clickCancelButton();
    }
}
