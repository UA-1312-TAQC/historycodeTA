package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.ContextsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContextsPage extends EditorBasePage {

    @FindBy(xpath = "//div[contains(@class, 'ant-table-wrapper')]")
    private WebElement rootGrid;

    private AddButtonElement addContextButton;
    private ContextsGridComponent grid;

    public ContextsPage(WebDriver driver) {
        super(driver);
        addContextButton = new AddButtonElement(driver, getRootAddButton());
        grid = new ContextsGridComponent(driver, rootGrid);
    }

    public ContextsModalComponent clickAddContext() {
        addContextButton.clickButton();
        sleep(1000);
        return new ContextsModalComponent(driver, getDisplayedModalRoot());
    }

    public int getTableHeadersCount(){
        return grid.getHeaderItems().size();
    }

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

    public void deleteTableRow(ContextsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
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
}
