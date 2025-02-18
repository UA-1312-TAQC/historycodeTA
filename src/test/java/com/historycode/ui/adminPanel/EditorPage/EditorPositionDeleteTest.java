package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditorPositionDeleteTest extends BaseTestRunnerWithAdmin {

    private String newPosition;
    private static final String POSITION_PREFIX = "A_newPosition_";

    @BeforeMethod
    private void createPosition() {
        int lengthOfNewName = 10;
        this.newPosition = POSITION_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName);

        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToPositions()
                .clickAddPosition()
                .enterPosition(newPosition)
                .save()
                .close();
    }

    @Test
    @Issue("108")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that admin can delete existing position")
    public void deletePositionTest() {
        PositionsPage positionsPage = new PositionsPage(driver);
        PositionsRowComponent row = positionsPage.getTableRowByTitle(newPosition);
        Assert.assertNotNull(row, "The positions was not successfully created");

        positionsPage.deleteTableRow(row).clickOkButton();
        Assert.assertNull(new PositionsPage(driver).getTableRowByTitle(newPosition), "The positions was not deleted");
    }
}
