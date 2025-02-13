package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesGridComponent extends GridComponent {

    @Getter
    List<CategoriesRowComponent> rows;

    public CategoriesGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowNodes) {
            if (rowElement.isDisplayed()) {
                rows.add(new CategoriesRowComponent(driver, rowElement));
            }
        }
    }

    @Step("Grid checks that displayed correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Grid checks that it's rows are displayed.")
    public boolean isRowsDisplayed() {
        for (CategoriesRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    @Step("Grid returns rows titles as list of strings.")
    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (CategoriesRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    @Step("Grid returns row by number: {num}.")
    public CategoriesRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    @Step("Grid returns row by it's title: '{title}'.")
    public CategoriesRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    @Step("Grid returns row where is the '{part}' in the title.")
    public List<CategoriesRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    @Step("Grid takes Row and returns it's title as string.")
    public String getRowTitleString(CategoriesRowComponent row){
        return row.getTitleString();
    }

    @Step("Grid takes Row and returns it's title web element.")
    public WebElement getRowTitle(CategoriesRowComponent row){
        return row.getTitle();
    }

    @Step("Grid takes Row and returns it's edit web element.")
    public WebElement getRowEditAction(CategoriesRowComponent row) {
        return row.getEditAction();
    }

    @Step("Grid takes Row and returns it's delete web element.")
    public WebElement getRowDeleteAction(CategoriesRowComponent row) {
        return row.getDeleteAction();
    }

    @Step("Grid takes Row and returns it's picture web element.")
    public WebElement getRowPicture (CategoriesRowComponent row) {
        return row.getPicture();
    }

    @Step("Grid takes Row and calls it's click edit method.")
    public void editRow(CategoriesRowComponent row) {
        row.clickEdit();
    }

    @Step("Grid takes Row and calls it's click delete method.")
    public void deleteRow(CategoriesRowComponent row) {
        row.clickDelete();
    }

    public void clickNextPage() {
        pagination.clickNextPage();
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
    }

    public void clickPrevFivePages() {
        pagination.clickPrevFivePages();
    }

    public void clickNextFivePages() {
        pagination.clickNextFivePages();
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
    }
}
