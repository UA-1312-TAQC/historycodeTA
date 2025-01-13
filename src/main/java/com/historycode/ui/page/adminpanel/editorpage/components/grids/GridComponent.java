package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GridComponent extends BaseGridComponent {

    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> rowElements;
    @FindBy(xpath = "//thead//th")
    private List<WebElement> headerItemsAll;
    @FindBy(xpath = "//div[@class='underTableElement']")
    private WebElement rootPagination;

    private List<WebElement> headerItems;
    protected PaginationAdminPanelComponent pagination;

    public GridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        headerItems = new ArrayList<>();
        pagination = new PaginationAdminPanelComponent(driver, rootPagination);
        initHeaderItems();
        System.out.println("Grid was created");
    }

    @Step("Check Grid Headers Are Displayed.")
    public boolean isHeadersDisplayed() {
        for (WebElement header : getHeaderItems()) {
            if (!header.isDisplayed()) { return false; }
        }
        return true;
    }

    private void initHeaderItems() {
        for (WebElement item : headerItemsAll) {
            if (item.isDisplayed()) {
                headerItems.add(item);
            }
        }
    }

    public List<WebElement> getHeaderItems() {
        return headerItems;
    }

    public List<String> getHeaderItemsString() {
        return headerItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
