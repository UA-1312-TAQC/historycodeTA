package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.CategoriesGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.CategoriesModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoriesPage extends BasePage {
    private final addButtonElement addCategoryButton = new addButtonElement(driver, getRootAddButton());
    private final CategoriesGridComponent grid = new CategoriesGridComponent(driver, getRootGrid());

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public CategoriesModalComponent addCategory() throws InterruptedException {
        addCategoryButton.clickButton();
        Thread.sleep(1000);
        return new CategoriesModalComponent(driver, getDisplayedModalRoot());
    }

    public int getTableHeadersCount() {
        return grid.getHeaderItems().size();
    }

    public List<String> getTableHeadersString() {
        return grid.getHeaderItemsString();
    }

    public List<WebElement> getTableHeaders() {
        return grid.getHeaderItems();
    }

    public int getTableRowsCount() {
        return grid.getRows().size();
    }

    public List<String> getTableRowsTitles() {
        return grid.getRowsTitles();
    }

    public List<CategoriesRowComponent> getTableRows() {
        return grid.getRows();
    }

    public List<CategoriesRowComponent> getTableRowsByTitlePart(String part) {
        return grid.getRowsByTitlePart(part);
    }

    public CategoriesRowComponent getTableRowByNumber(int num) {
        return grid.getRowByNum(num);
    }

    public CategoriesRowComponent getTableRowByTitle(String title) {
        return grid.getRowByTitle(title);
    }

    public String getAddButtonText() {
        return addCategoryButton.getButtonText();
    }

    public CategoriesModalComponent editTableRow(CategoriesRowComponent row) throws InterruptedException {
        grid.editRow(row);
        Thread.sleep(500);
        return new CategoriesModalComponent(driver, getDisplayedModalRoot());
    }

    public void deleteTableRow(CategoriesRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }
}
