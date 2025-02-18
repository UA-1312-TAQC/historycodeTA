package com.historycode.ui.adminPanel.EditorPage;

import com.beust.ah.A;
import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditorPositionsTests extends BaseTestRunnerWithAdmin {

    private final String TEST_POSITION = "testPosition23";
    private final String TEST_POSITION_NEW = "testPosition32";
    private final String TEST_POSITION_VALID = "testPositionValid";
    private final String TEST_POSITION_TOO_LONG = "testPositionAddingWithATooLongNameMoreThan50Symbols";

    @Step("Go to Editor page.")
    @BeforeMethod
    public void moveToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
    }

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
                .getInputFieldText();

        Assert.assertTrue(actualMessage.length() <= 50,
                "The position name is more then 50 symbols.");

    }

    @Test
    @Issue("112")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that the admin can add a new position with valid data")
    public void verifyAdminCanAddNewValidPosition() {

        PositionsPage positionsPage = new CategoriesPage(driver)
                .moveToPositions();

        positionsPage
                .clickAddPosition()
                .enterPosition(TEST_POSITION_VALID)
                .save()
                .close();

        positionsPage = new PositionsPage(driver);
        while (positionsPage.getTableRowByTitle(TEST_POSITION_VALID) == null) {
            if (!positionsPage.tableHasNextPage()) {
                break;
            }
            positionsPage = positionsPage.clickNextPage();
        }

        boolean actual = positionsPage.getTableRowByTitle(TEST_POSITION_VALID) != null;
        Assert.assertTrue(actual,
                "The position was not created.");

    }

    @Step("Cleanup data.")
    @AfterClass
    void cleanup() {

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        PositionsPage positionsPage = new CategoriesPage(driver)
                .moveToPositions();

        while (positionsPage.getTableRowByTitle(TEST_POSITION_VALID) == null) {
            if (!positionsPage.tableHasNextPage()) {
                break;
            }
            positionsPage = positionsPage.clickNextPage();
        }

        PositionsRowComponent row = positionsPage.getTableRowByTitle(TEST_POSITION_VALID);
        if (row != null) {
            positionsPage.deleteTableRow(row).clickOkButton();
        }

    }

    //ToDo Add Before Class method to add new position than move back to Base Admin Page and move to the editor
}
