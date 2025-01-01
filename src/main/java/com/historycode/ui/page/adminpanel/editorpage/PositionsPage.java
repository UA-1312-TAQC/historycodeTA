package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.PositionsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PositionsPage extends BasePage {
    private addButtonElement addPositionButton = new addButtonElement(driver, rootAddButton);
    private PositionsGridComponent grid = new PositionsGridComponent(driver, rootGrid);

    public PositionsPage(WebDriver driver){
        super(driver);
    }

    public void addPosition() {
        //TODO Update to return modal
        addPositionButton.clickButton();
    }

    public int getTableHeadersCount(){
        return grid.headerItems.size();
    }

    public List<String> getTableHeadersString() {
        return grid.getHeaderItemsString();
    }

    public List<WebElement> getTableHeaders(){
        return grid.headerItems;
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

    public void editTableRow(PositionsRowComponent row) {
        //TODO Implement return of modal
        grid.editRow(row);
    }

    public void deleteTableRow(PositionsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }
}
