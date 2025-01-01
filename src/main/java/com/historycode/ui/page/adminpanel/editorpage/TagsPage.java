package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.TagsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TagsPage extends BasePage {
    private addButtonElement addTagButton;
    private TagsGridComponent grid;

    public TagsPage(WebDriver driver) {
        super(driver);
        this.addTagButton = new addButtonElement(driver, rootAddButton);
        this.grid = new TagsGridComponent(driver, rootGrid);
        System.out.println("Tags page was created!");
    }

    public void addTag() {
        //TODO Update to return modal
        addTagButton.clickButton();
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

    public List<TagsRowComponent> getTableRows() {
        return grid.getRows();
    }

    public List<TagsRowComponent> getTableRowsByTitlePart(String part) {
        return grid.getRowsByTitlePart(part);
    }

    public TagsRowComponent getTableRowByNumber(int num) {
        return grid.getRowByNum(num);
    }

    public TagsRowComponent getTableRowByTitle(String title) {
        return grid.getRowByTitle(title);
    }

    public void editTableRow(TagsRowComponent row) {
        //TODO Implement return of modal
        grid.editRow(row);
    }

    public void deleteTableRow(TagsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }
}
