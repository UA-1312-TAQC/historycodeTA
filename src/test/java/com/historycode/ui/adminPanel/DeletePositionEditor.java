package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.random.RandomGenerator;

public class DeletePositionEditor extends TestRunnerWithAdmin {
    String positionName;

    @BeforeMethod
    public void setupForDeleteJob() throws InterruptedException {
        login();
        positionName = "Бухгалтер_" + RandomStringUtils.randomAlphanumeric(10);
        driver.get(testValueProvider.getBaseUIUrl() + "admin-panel/editor");
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        categoriesPage.moveToPositions()
                .addPosition()
                .inputNewPosition(positionName)
                .saveNewPosition()
                .closeModal();
    }

    @Test
    @Issue("108")
    @Description("Verify that the admin can delete position using the trash bin button")
    public void testDeleteJob() throws InterruptedException {
        PositionsPage positionsPage = new PositionsPage(driver);
        PositionsRowComponent positionToDelete = positionsPage.getTableRowByTitle(positionName);
        positionsPage.deleteTableRow(positionToDelete)
                .clickOkButton();
    }
}
