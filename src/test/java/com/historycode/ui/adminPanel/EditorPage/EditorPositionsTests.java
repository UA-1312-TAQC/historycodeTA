package com.historycode.ui.adminPanel.EditorPage;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import com.historycode.utils.editorScenarious.EditorPageRowSearcher;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;

public class EditorPositionsTests extends BaseTestRunnerWithAdmin {

    private final String TEST_POSITION = "testPosition23";
    private final String TEST_POSITION_NEW = "testPosition32";
    private final String TEST_POSITION_VALID = "testPositionValid";
    private final String TEST_POSITION_TOO_LONG = "testPositionAddingWithATooLongNameMoreThan50Symbols";

    @Step("Prepare data.")
    @BeforeClass
    public void prepareData() {
        beforeMethod();
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToPositions()
                .clickAddPosition()
                .enterPosition(TEST_POSITION)
                .save()
                .close();
    }

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

        PositionsPage positionsPage = new CategoriesPage(driver)
                .moveToPositions();

        positionsPage
                .editTableRow(new PositionsPage(driver).getTableRowByTitle(TEST_POSITION))
                .enterPosition(TEST_POSITION_NEW)
                .save()
                .close();

        Assert.assertNull(new EditorPageRowSearcher(driver)
                .searchPositionRow(TEST_POSITION)
                .getPositionRow());

        Assert.assertNotNull(new EditorPageRowSearcher(driver)
                .searchPositionRow(TEST_POSITION_NEW)
                .getPositionRow());

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

        new CategoriesPage(driver)
                .moveToPositions()
                .clickAddPosition()
                .enterPosition(TEST_POSITION_VALID)
                .save()
                .close();

        PositionsRowComponent actual = new EditorPageRowSearcher(driver)
                .searchPositionRow(TEST_POSITION_VALID)
                .getPositionRow();

        Assert.assertNotNull(actual,
                "The position was not created.");

    }

    @Step("Cleanup data.")
    @AfterClass(alwaysRun = true)
    void cleanup() {

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        PositionsPage positionsPage = new CategoriesPage(driver)
                .moveToPositions();

        new EditorPageRowSearcher(driver)
                .deletePositionRow(TEST_POSITION)
                .deletePositionRow(TEST_POSITION_NEW)
                .deletePositionRow(TEST_POSITION_VALID)
                .deletePositionRow(TEST_POSITION_TOO_LONG);

    }

    //ToDo Add Before Class method to add new position than move back to Base Admin Page and move to the editor
}
