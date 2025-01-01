package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriesGridComponent extends GridComponent {
    List<CategoriesRowComponent> rows = new ArrayList<>();

    public CategoriesGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initRows(driver);
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowElements) {
            if (rowElement.isDisplayed()) {
                rows.add(new CategoriesRowComponent(driver, rowElement));
            }
        }
    }

    public List<String> getRowsTitles(){
        List<String> titles = new ArrayList<>();
        for (CategoriesRowComponent row : rows){
            titles.add(row.getTitle());
        }
        return titles;
    }

    public List<CategoriesRowComponent> getRows() {
        return rows;
    }

    public CategoriesRowComponent getRowByNum(int num) {
        return rows.get(num);
    }

    public CategoriesRowComponent getRowByTitle(String title) {
        return rows.stream().filter(row -> row.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public List<CategoriesRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitle().contains(part))
                .collect(Collectors.toList());
    }

    public void editRow(CategoriesRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(CategoriesRowComponent row) {
        row.clickDelete();
    }

    //TODO Update edit/deleteRow methods to return modals
    //TODO Implement methods to work with row picture
}



