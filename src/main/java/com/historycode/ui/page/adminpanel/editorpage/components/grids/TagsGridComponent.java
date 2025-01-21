package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagsGridComponent extends GridComponent {
    List<TagsRowComponent> rows;

    public TagsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    @Step("Check Tags Grid Components Are Displayed Correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Check Tags Rows Are Displayed Correctly.")
    public boolean isRowsDisplayed() {
        for (TagsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowElements) {
            if (rowElement.isDisplayed()) {
                rows.add(new TagsRowComponent(driver, rowElement));
            }
        }
    }

    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (TagsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    public List<TagsRowComponent> getRows() {
        return rows;
    }

    public TagsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    public TagsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    public List<TagsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    public WebElement getRowEditAction(TagsRowComponent row) {
        return row.getEditAction();
    }

    public WebElement getRowDeleteAction(TagsRowComponent row) {
        return row.getDeleteAction();
    }

    public String getRowTitleString(TagsRowComponent row){
        return row.getTitleString();
    }

    public WebElement getRowTitle(TagsRowComponent row){
        return row.getTitle();
    }

    public void editRow(TagsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(TagsRowComponent row) {
        row.clickDelete();
    }

    public TagsGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new TagsGridComponent(driver, rootElement);
    }

    public TagsGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new TagsGridComponent(driver, rootElement);
    }

    public TagsGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        return new TagsGridComponent(driver, rootElement);
    }

    public TagsGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        return new TagsGridComponent(driver, rootElement);
    }

    public TagsGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new TagsGridComponent(driver, rootElement);
    }
    //TODO Update edit/deleteRow methods to return modals
}
