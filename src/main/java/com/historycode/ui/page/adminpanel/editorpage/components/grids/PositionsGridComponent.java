package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PositionsGridComponent extends GridComponent {
    List<PositionsRowComponent> rows = new ArrayList<>();

    public PositionsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initRows(driver);
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
            titles.add(row.getTitle());
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
        return rows.stream().filter(row -> row.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public List<PositionsRowComponent> getRowsByTitlePart(String part) {
        return rows.stream()
                .filter(row -> row.getTitle().contains(part))
                .collect(Collectors.toList());
    }

    public void editRow(PositionsRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(PositionsRowComponent row) {
        row.clickDelete();
    }

    //TODO Update edit/deleteRow methods to return modals
}


