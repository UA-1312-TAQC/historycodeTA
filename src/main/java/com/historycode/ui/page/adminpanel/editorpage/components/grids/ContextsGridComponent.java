package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContextsGridComponent extends GridComponent {

    @Getter
    List<ContextsRowComponent> rows;

    public ContextsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowNodes) {
            if (rowElement.isDisplayed()) {
                rows.add(new ContextsRowComponent(driver, rowElement));
            }
        }
    }

    @Step("Grid checks that displayed correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Grid checks that it's rows are displayed.")
    public boolean isRowsDisplayed() {
        for (ContextsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    @Step("Grid returns rows titles as list of strings.")
    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (ContextsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    @Step("Grid returns row by number: {num}.")
    public ContextsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    @Step("Grid returns row by it's title: '{title}'.")
    public ContextsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    @Step("Grid returns row where is the '{part}' in the title.")
    public List<ContextsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    @Step("Grid takes Row and returns it's title as string.")
    public String getRowTitleString(ContextsRowComponent row){
        return row.getTitleString();
    }

    @Step("Grid takes Row and returns it's title web element.")
    public WebElement getRowTitle(ContextsRowComponent row){
        return row.getTitle();
    }

    @Step("Grid takes Row and returns it's edit web element.")
    public WebElement getRowEditAction(ContextsRowComponent row) {
        return row.getEditAction();
    }

    @Step("Grid takes Row and returns it's delete web element.")
    public WebElement getRowDeleteAction(ContextsRowComponent row) {
        return row.getDeleteAction();
    }

    @Step("Grid takes Row and calls it's click edit method.")
    public void editRow(ContextsRowComponent row) {
        row.clickEdit();
    }

    @Step("Grid takes Row and calls it's click delete method.")
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
}
