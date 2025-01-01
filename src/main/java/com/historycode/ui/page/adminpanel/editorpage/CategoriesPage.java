package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.CategoriesGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoriesPage extends BasePage {
    private addButtonElement addCategoryButton = new addButtonElement(driver, rootAddButton);
    private CategoriesGridComponent grid = new CategoriesGridComponent(driver, rootGrid);

    public CategoriesPage(WebDriver driver) {
        super(driver);
        System.out.println("Categories page was created!");
    }

    public void addCategory() {
        //TODO Update to return modal
        addCategoryButton.clickButton();
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

    public void editTableRow(CategoriesRowComponent row) {
        //TODO Implement return of modal
        grid.editRow(row);
    }

    public void deleteTableRow(CategoriesRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }
}
