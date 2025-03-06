package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.PositionsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.CategoriesModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.PositionsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PositionsPage extends BaseEditorPage {

    private AddButtonElement addPositionButton;
    private PositionsGridComponent grid;

    public PositionsPage(WebDriver driver) {
        super(driver);
        addPositionButton = new AddButtonElement(driver, getAddButtonNode());
        grid = new PositionsGridComponent(driver, gridNode);
    }

    @Step("Check Positions Grid Is Displayed Correctly.")
    public boolean isGridDisplayed() {
        return grid.isDisplayed();
    }

    public PositionsModalComponent clickAddPosition() {
        addPositionButton.clickButton();
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new PositionsModalComponent(driver, getDisplayedModalRoot());
    }

    public int getTableHeadersCount(){
        return grid.getHeaderItems().size();
    }

    @Step("Get Table Headers.")
    public List<String> getTableHeadersString() {
        return grid.getHeaderItemsString();
    }

    public List<WebElement> getTableHeaders(){
        return grid.getHeaderItems();
    }

    public int getTableRowsCount() {
        return grid.getRows().size();
    }

    public List<String> getTableRowsTitles(){
        return grid.getRowsTitles();
    }

    public List<PositionsRowComponent> getTableRows() {
        return grid.getRows();
    }

    public List<PositionsRowComponent> getTableRowsByTitlePart(String part) {
        return grid.getRowsByTitlePart(part);
    }

    public PositionsRowComponent getTableRowByNumber(int num) {
        return grid.getRowByNum(num);
    }

    public PositionsRowComponent getTableRowByTitle(String title) {
        return grid.getRowByTitle(title);
    }

    public String getAddButtonText(){
        return addPositionButton.getButtonText();
    }

    public PositionsModalComponent editTableRow(PositionsRowComponent row) {
        grid.editRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new PositionsModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteTableRow(PositionsRowComponent row) {
        grid.deleteRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new DeleteItemModal(driver, getDisplayedModalRoot());
    }

    public WebElement getTableRowEditAction(PositionsRowComponent row) {
        return grid.getRowEditAction(row);
    }

    public WebElement getTableRowDeleteAction(PositionsRowComponent row) {
        return grid.getRowDeleteAction(row);
    }

    public WebElement getTableRowTitle(PositionsRowComponent row) {
        return grid.getRowTitle(row);
    }

    public String getTableRowTitleString(PositionsRowComponent row) {
        return grid.getRowTitleString(row);
    }

    public PositionsPage clickNextPage() {
        grid.clickNextPage();
        sleep(1000); //ToDo Change it to better solution
        return new PositionsPage(driver);
    }

    public PositionsPage clickPrevPage() {
        grid.clickPrevPage();
        sleep(1000); //ToDo Change it to better solution
        return new PositionsPage(driver);
    }

    public PositionsPage clickPrevFivePages() {
        grid.clickPrevFivePages();
        sleep(1000); //ToDo Change it to better solution
        return new PositionsPage(driver);
    }

    public PositionsPage clickNextFivePages() {
        grid.clickNextFivePages();
        sleep(1000); //ToDo Change it to better solution
        return new PositionsPage(driver);
    }

    public PositionsPage clickPaginationItem(int index) {
        grid.clickPaginationItem(index);
        sleep(1000); //ToDo Change it to better solution
        return new PositionsPage(driver);
    }

    public boolean tableHasNextPage() {
        return grid.tableHasNextPage();
    }

    public boolean tableHasPrevPage() {
        return grid.tableHasPrevPage();
    }

    public PositionsPage moveToPageWithRow(String title) {

        driver.navigate().refresh();
        PositionsPage currentPage = new CategoriesPage(driver).moveToPositions();

        while (currentPage.getTableRowByTitle(title) == null) {
            if (!currentPage.tableHasNextPage()) {
                break;
            }
            currentPage = currentPage.clickNextPage();
        }

        return currentPage;

    }

    public PositionsPage deletePosition(String title) {
        PositionsPage currentPage = moveToPageWithRow(title);
        PositionsRowComponent row = currentPage.getTableRowByTitle(title);
        if (row != null) {
            currentPage.deleteTableRow(row).clickOkButton();
        }
        return currentPage;
    }

    public PositionsModalComponent editPosition(String name) {
        PositionsPage currentPage = moveToPageWithRow(name);
        PositionsRowComponent row = currentPage.getTableRowByTitle(name);
        return editTableRow(row);
    }

}
