package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.ContextsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import com.sun.source.tree.Tree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContextsPage extends BasePage {
    private final addButtonElement addContextButton = new addButtonElement(driver, getRootAddButton());
    private final ContextsGridComponent grid = new ContextsGridComponent(driver, getRootGrid());

    public ContextsPage(WebDriver driver) {
        super(driver);
    }

    public ContextsModalComponent addContext() throws InterruptedException {
        addContextButton.clickButton();
        Thread.sleep(1000);
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
}
