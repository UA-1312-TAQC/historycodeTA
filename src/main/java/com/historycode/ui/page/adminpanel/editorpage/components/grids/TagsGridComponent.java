package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagsGridComponent extends GridComponent {

    @Getter
    List<TagsRowComponent> rows;

    public TagsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        rows = new ArrayList<>();
        initRows(driver);
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowNodes) {
            if (rowElement.isDisplayed()) {
                rows.add(new TagsRowComponent(driver, rowElement));
            }
        }
    }

    @Step("Grid checks that displayed correctly.")
    public boolean isDisplayed() {
        return (isHeadersDisplayed() && isRowsDisplayed());
    }

    @Step("Grid checks that it's rows are displayed.")
    public boolean isRowsDisplayed() {
        for (TagsRowComponent row : rows) {
            if (!row.isExist()) { return false; }
        }
        return true;
    }

    @Step("Grid returns rows titles as list of strings.")
    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (TagsRowComponent row : rows){
            titles.add(row.getTitleString());
        }
        return titles;
    }

    @Step("Grid returns row by number: {num}.")
    public TagsRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    @Step("Grid returns row by it's title: '{title}'.")
    public TagsRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    @Step("Grid returns row where is the '{part}' in the title.")
    public List<TagsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    @Step("Grid takes Row and returns it's title as string.")
    public String getRowTitleString(TagsRowComponent row){
        return row.getTitleString();
    }

    @Step("Grid takes Row and returns it's title web element.")
    public WebElement getRowTitle(TagsRowComponent row){
        return row.getTitle();
    }

    @Step("Grid takes Row and returns it's edit web element.")
    public WebElement getRowEditAction(TagsRowComponent row) {
        return row.getEditAction();
    }

    @Step("Grid takes Row and returns it's delete web element.")
    public WebElement getRowDeleteAction(TagsRowComponent row) {
        return row.getDeleteAction();
    }

    @Step("Grid takes Row and calls it's click edit method.")
    public void editRow(TagsRowComponent row) {
        row.clickEdit();
    }

    @Step("Grid takes Row and calls it's click delete method.")
    public void deleteRow(TagsRowComponent row) {
        row.clickDelete();
    }
}
