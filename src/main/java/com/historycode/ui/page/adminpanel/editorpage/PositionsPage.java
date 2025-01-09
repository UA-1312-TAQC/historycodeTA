package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.PositionsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.PositionsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PositionsPage extends EditorBasePage {
    private final AddButtonElement addPositionButton = new AddButtonElement(driver, getRootAddButton());
    private final PositionsGridComponent grid = new PositionsGridComponent(driver, getRootGrid());

    public PositionsPage(WebDriver driver) {
        super(driver);
    }

    public PositionsModalComponent addPosition() {
        addPositionButton.clickButton();
        sleep(1000);
        return new PositionsModalComponent(driver, getDisplayedModalRoot());
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

    public PositionsModalComponent editTableRow(PositionsRowComponent row) throws InterruptedException {
        grid.editRow(row);
        Thread.sleep(500);
        return new PositionsModalComponent(driver, getDisplayedModalRoot());
    }

    public void deleteTableRow(PositionsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
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
}
