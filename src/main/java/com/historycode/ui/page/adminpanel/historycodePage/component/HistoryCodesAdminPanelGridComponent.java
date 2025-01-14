package com.historycode.ui.page.adminpanel.historycodePage.component;


import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryCodesAdminPanelGridComponent extends BaseGridComponent {

    protected List<HistoryCodesAdminPanelRowComponent> rowElements = new ArrayList<>();
    @FindBy(xpath = "./tbody//tr")
    protected List<WebElement> rowElementsNode;

    @FindBy(xpath = "./thead//th")
    protected List<WebElement> headerElementsNode;

    public HistoryCodesAdminPanelGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initHeaderItems();
        initRows(driver);
    }

    private void initHeaderItems() {
        for (WebElement item : headerElementsNode) {
            if (item.isDisplayed()) {
                headerItems.add(item);
            }
        }
    }

    public void initRows(WebDriver driver) {
        for (WebElement rowElement : rowElementsNode) {
            if (rowElement.isDisplayed()) {
                rowElements.add(new HistoryCodesAdminPanelRowComponent(driver, rowElement));
            }
        }
    }

    public List<String> getHeaderItemsString() {
        return headerItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getRowsTitles() {
        List<String> titles = new ArrayList<>();
        for (HistoryCodesAdminPanelRowComponent row : rowElements) {
            titles.add(row.getTitleString());
        }
        return titles;
    }

    public HistoryCodesAdminPanelRowComponent getRowByNum(int num) {
        return rowElements.get(num);
    }

    public HistoryCodesAdminPanelRowComponent getRowByTitle(String title) {
        return rowElements.stream().filter(row -> row.getTitleString().equals(title))
                .findFirst().orElse(null);
    }

    public List<HistoryCodesAdminPanelRowComponent> getRowsByTitlePart(String part) {
        return rowElements.stream()
                .filter(row -> row.getTitleString().contains(part))
                .collect(Collectors.toList());
    }

    public void editRow(HistoryCodesAdminPanelRowComponent row) {
        row.clickEdit();
    }

    public void deleteRow(HistoryCodesAdminPanelRowComponent row) {
        row.clickDelete();
    }

    public void statisticRow(HistoryCodesAdminPanelRowComponent row) {
        row.clickStatistics();
    }

    public int getRowsCount() {
        return rowElements.size();
    }

    public HistoryCodesAdminPanelGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }

    public HistoryCodesAdminPanelGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }

    public HistoryCodesAdminPanelGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }
}
