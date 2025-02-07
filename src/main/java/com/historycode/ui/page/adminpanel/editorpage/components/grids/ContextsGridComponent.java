package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContextsGridComponent extends GridComponent {

    List<ContextsRowComponent> rows;

    public ContextsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    @Step("Check Contexts Grid Components Are Displayed Correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Check Contexts Rows Are Displayed Correctly.")
    public boolean isRowsDisplayed() {
        for (ContextsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowElements) {
            if (rowElement.isDisplayed()) {
                rows.add(new ContextsRowComponent(driver, rowElement));
            }
        }
    }

    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (ContextsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    public List<ContextsRowComponent> getRows() {
        return rows;
    }

    public ContextsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    public ContextsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    public List<ContextsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    public WebElement getRowEditAction(ContextsRowComponent row) {
        return row.getEditAction();
    }

    public WebElement getRowDeleteAction(ContextsRowComponent row) {
        return row.getDeleteAction();
    }

    public String getRowTitleString(ContextsRowComponent row){
        return row.getTitleString();
    }

    public WebElement getRowTitle(ContextsRowComponent row){
        return row.getTitle();
    }

    public void editRow(ContextsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(ContextsRowComponent row) {
        row.clickDelete();
    }

    public ContextsGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new ContextsGridComponent(driver, rootElement);
    }

    public ContextsGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new ContextsGridComponent(driver, rootElement);
    }

    public ContextsGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        return new ContextsGridComponent(driver, rootElement);
    }

    public ContextsGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        return new ContextsGridComponent(driver, rootElement);
    }

    public ContextsGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new ContextsGridComponent(driver, rootElement);
    }

    //TODO Update edit/deleteRow methods to return modals
}
