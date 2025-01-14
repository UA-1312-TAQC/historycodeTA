package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.ContextsModalComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getRowsTitles() {
        List<String> titles = new ArrayList<>();
        for (ContextsRowComponent row : rows) {
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

    public ContextsModalComponent editRow(ContextsRowComponent row) {
        row.clickEdit();
        return new ContextsModalComponent(driver, getDisplayedModalRoot());
    }

    public DeleteItemModal deleteRow(ContextsRowComponent row) {
        row.clickDelete();
        return new DeleteItemModal(driver, getDisplayedModalRoot());
    }

    private WebElement getDisplayedModalRoot() {
        return driver.findElement(By.xpath("//div[@role='dialog']/div[2]"));
    }
}
