package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ContextsGridComponent extends GridComponent {
    List<ContextsRowComponent> rows = new ArrayList<>();

    public ContextsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initRows(driver);
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
            titles.add(row.getTitle());
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
        return rows.stream().filter(row -> row.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public List<ContextsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitle().contains(part))
                .collect(Collectors.toList());
    }

    public void editRow(ContextsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(ContextsRowComponent row) {
        row.clickDelete();
    }

    //TODO Update edit/deleteRow methods to return modals
}


