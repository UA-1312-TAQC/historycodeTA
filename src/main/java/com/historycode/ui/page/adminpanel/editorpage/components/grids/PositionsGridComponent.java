package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PositionsGridComponent extends GridComponent {

    @Getter
    List<PositionsRowComponent> rows;

    public PositionsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowNodes) {
            if (rowElement.isDisplayed()) {
                rows.add(new PositionsRowComponent(driver, rowElement));
            }
        }
    }

    @Step("Grid checks that displayed correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Grid checks that it's rows are displayed.")
    public boolean isRowsDisplayed() {
        for (PositionsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    @Step("Grid returns rows titles as list of strings.")
    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (PositionsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    @Step("Grid returns row by number: {num}.")
    public PositionsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    @Step("Grid returns row by it's title: '{title}'.")
    public PositionsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    @Step("Grid returns row where is the '{part}' in the title.")
    public List<PositionsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    @Step("Grid takes Row and returns it's title as string.")
    public String getRowTitleString(PositionsRowComponent row){
        return row.getTitleString();
    }

    @Step("Grid takes Row and returns it's title web element.")
    public WebElement getRowTitle(PositionsRowComponent row){
        return row.getTitle();
    }

    @Step("Grid takes Row and returns it's edit web element.")
    public WebElement getRowEditAction(PositionsRowComponent row) {
        return row.getEditAction();
    }

    @Step("Grid takes Row and returns it's delete web element.")
    public WebElement getRowDeleteAction(PositionsRowComponent row) {
        return row.getDeleteAction();
    }

    @Step("Grid takes Row and calls it's click edit method.")
    public void editRow(PositionsRowComponent row) {
        row.clickEdit();
    }

    @Step("Grid takes Row and calls it's click delete method.")
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

}
