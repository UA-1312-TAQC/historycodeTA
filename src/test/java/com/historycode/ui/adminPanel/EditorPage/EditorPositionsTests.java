package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.testrunners.TestRunnerWithAdminEditor;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class EditorPositionsTests extends TestRunnerWithAdminEditor {

    private final String TEST_POSITION = "test_Position23";
    private final String TEST_POSITION_NEW = "test_Position32";

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

    //ToDo Add Before Class method to add new position than move back to Base Admin Page and move to the editor
    //ToDo Add After Class method to remove new position
}
