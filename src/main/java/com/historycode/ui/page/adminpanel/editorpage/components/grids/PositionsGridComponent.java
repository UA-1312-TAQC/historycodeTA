package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PositionsGridComponent extends GridComponent {
    List<PositionsRowComponent> rows;

    public PositionsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    @Step("Check Positions Grid Components Are Displayed Correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Check Positions Rows Are Displayed Correctly.")
    public boolean isRowsDisplayed() {
        for (PositionsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowElements) {
            if (rowElement.isDisplayed()) {
                rows.add(new PositionsRowComponent(driver, rowElement));
            }
        }
    }

    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (PositionsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    public List<PositionsRowComponent> getRows() {
        return rows;
    }

    public PositionsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    public PositionsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    public List<PositionsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    public WebElement getRowEditAction(PositionsRowComponent row) {
        return row.getEditAction();
    }

    public WebElement getRowDeleteAction(PositionsRowComponent row) {
        return row.getDeleteAction();
    }

    public String getRowTitleString(PositionsRowComponent row){
        return row.getTitleString();
    }

    public WebElement getRowTitle(PositionsRowComponent row){
        return row.getTitle();
    }

    public void editRow(PositionsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(PositionsRowComponent row) {
        row.clickDelete();
    }

    public PositionsGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new PositionsGridComponent(driver, rootElement);
    }

    public PositionsGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new PositionsGridComponent(driver, rootElement);
    }

    public PositionsGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        return new PositionsGridComponent(driver, rootElement);
    }

    public PositionsGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        return new PositionsGridComponent(driver, rootElement);
    }

    public PositionsGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new PositionsGridComponent(driver, rootElement);
    }
    //TODO Update edit/deleteRow methods to return modals
}
