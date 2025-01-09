package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.editorpage.components.grids.TagsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.TagsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.addButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TagsPage extends BasePage {
    private final addButtonElement addTagButton = new addButtonElement(driver, getRootAddButton());
    private final TagsGridComponent grid = new TagsGridComponent(driver, getRootGrid());

    public TagsPage(WebDriver driver) {
        super(driver);
    }

    public TagsModalComponent addTag() {
        addTagButton.clickButton();
        sleep(1000);
        return new TagsModalComponent(driver, getDisplayedModalRoot());
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

    public String getAddButtonTitleString() {
        return addTagButton.getButtonText();
    }

    public TagsModalComponent editTableRow(TagsRowComponent row) throws InterruptedException {
        grid.editRow(row);
        Thread.sleep(500);
        return new TagsModalComponent(driver, getDisplayedModalRoot());
    }

    public void deleteTableRow(TagsRowComponent row) {
        //TODO Implement return of modal
        grid.deleteRow(row);
    }

    public WebElement getTableRowEditAction(TagsRowComponent row) {
        return grid.getRowEditAction(row);
    }

    public WebElement getTableRowDeleteAction(TagsRowComponent row) {
        return grid.getRowDeleteAction(row);
    }

    public WebElement getTableRowTitle(TagsRowComponent row) {
        return grid.getRowTitle(row);
    }

    public String getTableRowTitleString(TagsRowComponent row) {
        return grid.getRowTitleString(row);
    }
}
