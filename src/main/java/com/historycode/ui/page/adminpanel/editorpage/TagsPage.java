package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.TagsGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.TagsModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TagsPage extends BaseEditorPage {

    private AddButtonElement addTagButton;
    @Getter
    private TagsGridComponent grid;

    public TagsPage(WebDriver driver) {
        super(driver);
        addTagButton = new AddButtonElement(driver, getAddButtonNode());
        grid = new TagsGridComponent(driver, gridNode);
    }

    @Step("Check Tags Grid Is Displayed Correctly.")
    public boolean isGridDisplayed() {
        return grid.isDisplayed();
    }

    public TagsModalComponent clickAddTag() {
        addTagButton.clickButton();
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new TagsModalComponent(driver, getDisplayedModalRoot());
    }

    public int getTableHeadersCount() {
        return grid.getHeaderItems().size();
    }

    @Step("Get Table Headers.")
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

    public TagsModalComponent editTableRow(TagsRowComponent row) {
        grid.editRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new TagsModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteTableRow(TagsRowComponent row) {
        grid.deleteRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new DeleteItemModal(driver, getDisplayedModalRoot());
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

    public TagsPage clickNextPage() {
        grid.clickNextPage();
        sleep(1000); //ToDo Change it to better solution
        return new TagsPage(driver);
    }

    public TagsPage clickPrevPage() {
        grid.clickPrevPage();
        sleep(1000); //ToDo Change it to better solution
        return new TagsPage(driver);
    }

    public TagsPage clickPrevFivePages() {
        grid.clickPrevFivePages();
        sleep(1000); //ToDo Change it to better solution
        return new TagsPage(driver);
    }

    public TagsPage clickNextFivePages() {
        grid.clickNextFivePages();
        sleep(1000); //ToDo Change it to better solution
        return new TagsPage(driver);
    }

    public TagsPage clickPaginationItem(int index) {
        grid.clickPaginationItem(index);
        sleep(1000); //ToDo Change it to better solution
        return new TagsPage(driver);
    }

    public boolean tableHasNextPage() {
        return grid.tableHasNextPage();
    }

    public TagsPage moveToPageWithRow(String title) {

        driver.navigate().refresh();
        TagsPage currentPage = new CategoriesPage(driver).moveToTags();

        while (currentPage.getTableRowByTitle(title) == null) {
            if (!currentPage.tableHasNextPage()) {
                break;
            }
            currentPage = currentPage.clickNextPage();
        }

        return currentPage;

    }

    public TagsPage deletePosition(String title) {

        TagsPage currentPage = moveToPageWithRow(title);
        TagsRowComponent row = currentPage.getTableRowByTitle(title);
        if (row != null) {
            currentPage.deleteTableRow(row).clickOkButton();
        }

        return currentPage;

    }

}
