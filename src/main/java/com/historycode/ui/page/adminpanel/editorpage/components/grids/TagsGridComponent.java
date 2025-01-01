package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.TagsRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TagsGridComponent extends GridComponent {
    List<TagsRowComponent> rows = new ArrayList<>();

    public TagsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initRows(driver);
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
            titles.add(row.getTitle());
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
        return rows.stream().filter(row -> row.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public List<TagsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitle().contains(part))
                .collect(Collectors.toList());
    }

    public void editRow(TagsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(TagsRowComponent row) {
        row.clickDelete();
    }

    //TODO Update edit/deleteRow methods to return modals
}


