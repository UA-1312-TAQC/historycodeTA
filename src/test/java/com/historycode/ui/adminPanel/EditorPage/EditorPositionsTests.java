package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.testrunners.TestRunnerWithAdminEditor;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorPositionsTests extends TestRunnerWithAdminEditor {

    private final String TEST_POSITION = "testPosition23";
    private final String TEST_POSITION_NEW = "testPosition32";
    private final String TEST_POSITION_TOO_LONG = "testPositionAddingWithATooLongNameMoreThan50Symbols";

    @Test
    @Issue("100")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the admin can edit existing positions using the \"pencil\" button")
    public void verifyAdminCanEditPosition() {

        new CategoriesPage(driver)
                .moveToPositions()
                .editTableRow(new PositionsPage(driver).getTableRowByTitle(TEST_POSITION))
                .enterPosition(TEST_POSITION_NEW)
                .save()
                .close();

        //ToDo Add Search in grids to the get table row
        //ToDo Add Assert is new position where updated
    }

    @Test
    @Issue("109")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the system blocks the admin's attempt to add more than 50 symbols in the \"Назва\" field")
    public void verifyAdminCantAddLongPositionName() {

        String actualMessage = new CategoriesPage(driver)
                .moveToPositions()
                .clickAddPosition()
                .enterPosition(TEST_POSITION_TOO_LONG)
                .getError()
                .getText();

        String expectedMessage = "Filling out no more than 50 symbols is available.";
        Assert.assertEquals(actualMessage, expectedMessage);

        //ToDo Update modals error messages
        //ToDo Check whether expected message is the same on web page
    }

    //ToDo Add Before Class method to add new position than move back to Base Admin Page and move to the editor
    //ToDo Add After Class method to remove new position
}
