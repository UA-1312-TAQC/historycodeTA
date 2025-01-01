package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.ContextsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContextsPage extends BasePage {
    private addButtonElement addContextButton = new addButtonElement(driver, rootAddButton);
    private ContextsGridComponent grid = new ContextsGridComponent(driver, rootGrid);

    public ContextsPage(WebDriver driver) {
        super(driver);
    }

    public void addContext() {
        //TODO Update to return modal
        addContextButton.clickButton();
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

    public void editTableRow(ContextsRowComponent row) {
        //TODO Implement return of modal
        grid.editRow(row);
    }

    public void deleteTableRow(ContextsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }
}
