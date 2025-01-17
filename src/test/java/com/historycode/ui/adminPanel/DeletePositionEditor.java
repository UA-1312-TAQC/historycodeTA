package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;


public class DeletePositionEditor extends TestRunnerWithAdmin {
    String positionName;

    @BeforeMethod
    public void setupForDeleteJob() {
        login();
        positionName = "Бухгалтер_" + UUID.randomUUID().toString().substring(0, 2);
        HistoryCodesAdminPanelPage baseHistoryCode = new HistoryCodesAdminPanelPage(driver);
        baseHistoryCode.getAdminMenuBar()
                .goToEditorPage()
                .moveToPositions()
                .addPosition()
                .inputNewPosition(positionName)
                .saveNewPosition()
                .closeModal();
    }

    @Test
    @Issue("108")
    @Description("Verify that the admin can delete position using the trash bin button")
    public void testDeleteJob() {
        PositionsPage positionsPage = new PositionsPage(driver);
        PositionsRowComponent positionToDelete = positionsPage.getTableRowByTitle(positionName);
        positionsPage.deleteTableRow(positionToDelete)
                .clickOkButton();
        PositionsRowComponent deletedPosition = positionsPage.getTableRowByTitle(positionName);

        Assert.assertNull(deletedPosition, "Position should not exist after deletion");
    }
}
