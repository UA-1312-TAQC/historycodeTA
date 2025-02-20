package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EditorPageAddPositionTest extends BaseTestRunnerWithAdmin {

    @Issue("111")
    @Description("Verify that the admin can't add already existing position ")
    @Test
    public void verifyAdminCannotAddExitingPosition() {

        String nameOfRow = new PositionsPage(driver)
                .moveToPositions()
                .getTableRowsTitles()
                .get(0);

        String modalComponent = new PositionsPage(driver)
                .clickAddPosition()
                .enterPosition(nameOfRow)
                .getError()
                .getText();

        Assert.assertEquals(modalComponent, "Позиція з такою назвою вже існує");

    }
}
