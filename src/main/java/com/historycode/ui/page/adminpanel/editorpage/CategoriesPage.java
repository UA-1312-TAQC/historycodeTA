package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.grids.CategoriesGridComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.CategoriesModalComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.AddButtonElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoriesPage extends BaseEditorPage {

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-table-wrapper')]")
    private WebElement gridNode;

    private AddButtonElement addCategoryButton;
    private CategoriesGridComponent grid;

    public CategoriesPage(WebDriver driver) {
        super(driver);
        addCategoryButton = new AddButtonElement(driver, getAddButtonNode());
        grid = new CategoriesGridComponent(driver, gridNode);
    }

    @Step("Check Categories Grid Is Displayed Correctly.")
    public boolean isGridDisplayed() {
        return grid.isDisplayed();
    }

    public CategoriesModalComponent clickAddCategory() {
        addCategoryButton.clickButton();
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new CategoriesModalComponent(driver, getDisplayedModalRoot());
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

    public CategoriesModalComponent editTableRow(CategoriesRowComponent row) {
        grid.editRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new CategoriesModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteTableRow(CategoriesRowComponent row) {
        grid.deleteRow(row);
        wait.until(driver -> getDisplayedModalRoot() != null);
        return new DeleteItemModal(driver, getDisplayedModalRoot());
    }

    public WebElement getTableRowEditAction(CategoriesRowComponent row) {
        return grid.getRowEditAction(row);
    }

    public WebElement getTableRowDeleteAction(CategoriesRowComponent row) {
        return grid.getRowDeleteAction(row);
    }

    public WebElement getTableRowPicture(CategoriesRowComponent row) {
        return grid.getRowPicture(row);
    }

    public WebElement getTableRowTitle(CategoriesRowComponent row) {
        return grid.getRowTitle(row);
    }

    public String getTableRowTitleString(CategoriesRowComponent row) {
        return grid.getRowTitleString(row);
    }

    public void clickNextPage() {
        grid.clickNextPage();
        grid = new CategoriesGridComponent(driver, gridNode);
    }

    public void clickPrevPage() {
        grid.clickPrevPage();
        grid = new CategoriesGridComponent(driver, gridNode);
    }

    public void clickPrevFivePages() {
        grid.clickPrevFivePages();
        grid = new CategoriesGridComponent(driver, gridNode);
    }

    public void clickNextFivePages() {
        grid.clickNextFivePages();
        grid = new CategoriesGridComponent(driver, gridNode);
    }

    public void clickPaginationItem(int index) {
        grid.clickPaginationItem(index);
        grid = new CategoriesGridComponent(driver, gridNode);
    }
}
