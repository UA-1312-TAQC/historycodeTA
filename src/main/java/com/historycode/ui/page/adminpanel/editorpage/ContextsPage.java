package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.Base;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.ContextsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContextsPage extends BaseEditorPage {

    @FindBy(xpath = "//div[contains(@class, 'ant-table-wrapper')]")
    private WebElement rootGrid;

    private AddButtonElement addContextButton;
    private ContextsGridComponent grid;

    public ContextsPage(WebDriver driver) {
        super(driver);
        addContextButton = new AddButtonElement(driver, getRootAddButton());
        grid = new ContextsGridComponent(driver, rootGrid);
    }

    @Step("Check Contexts Grid Is Displayed Correctly.")
    public boolean isGridDisplayed() {
        return grid.isDisplayed();
    }

    public ContextsModalComponent clickAddContext() {
        addContextButton.clickButton();
        sleep(1000);
        return new ContextsModalComponent(driver, getDisplayedModalRoot());
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

    public List<ContextsRowComponent> getTableRows() {
        return grid.getRows();
    }

    public List<ContextsRowComponent> getTableRowsByTitlePart(String part) {
        return grid.getRowsByTitlePart(part);
    }

    public ContextsRowComponent getTableRowByNumber(int num) {
        return grid.getRowByNum(num);
    }

    public ContextsRowComponent getTableRowByTitle(String title) {
        return grid.getRowByTitle(title);
    }

    public String getAddButtonText(){
        return addContextButton.getButtonText();
    }

    public ContextsModalComponent editTableRow(ContextsRowComponent row) throws InterruptedException {
        grid.editRow(row);
        Thread.sleep(500);
        return new ContextsModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteTableRow(ContextsRowComponent row) {
        grid.deleteRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new DeleteItemModal(driver, getDisplayedModalRoot());
    }

    public WebElement getTableRowEditAction(ContextsRowComponent row) {
        return grid.getRowEditAction(row);
    }

    public WebElement getTableRowDeleteAction(ContextsRowComponent row) {
        return grid.getRowDeleteAction(row);
    }

    public WebElement getTableRowTitle(ContextsRowComponent row) {
        return grid.getRowTitle(row);
    }

    public String getTableRowTitleString(ContextsRowComponent row) {
        return grid.getRowTitleString(row);
    }

    public ContextsPage clickNextPage() {
        grid.clickNextPage();
        return new ContextsPage(driver);
    }

    public ContextsPage clickPrevPage() {
        grid.clickPrevPage();
        return new ContextsPage(driver);
    }

    public ContextsPage clickPrevFivePages() {
        grid.clickPrevFivePages();
        return new ContextsPage(driver);
    }

    public ContextsPage clickNextFivePages() {
        grid.clickNextFivePages();
        return new ContextsPage(driver);
    }

    public ContextsPage clickPaginationItem(int index) {
        grid.clickPaginationItem(index);
        return new ContextsPage(driver);
    }
}

//    public DeleteItemModal deleteTableRow(ContextsRowComponent row) {
//        waitUntilElementVisible(gridRootElement);
//        return gridComponent.deleteRow(row);
//    }
//    @Step("Add new context")
//    public ContextsModalComponent addContext() {
//        addNewContextButton.click();
//        waitUntilElementVisible(createModalRootElement);
//        return new ContextsModalComponent(driver, createModalRootElement);
//    }
//    public ContextsRowComponent getTableRowByTitle(String title) {
//        waitUntilElementVisible(gridRootElement);
//        return gridComponent.getRowByTitle(title);
//    }
