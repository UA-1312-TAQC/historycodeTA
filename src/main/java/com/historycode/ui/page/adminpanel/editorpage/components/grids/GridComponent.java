package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GridComponent extends BaseGridComponent {

    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> rowNodes;
    @FindBy(xpath = "//thead//th")
    private List<WebElement> headerItemsNodes;
    @FindBy(xpath = "//div[@class='underTableElement']")
    private List<WebElement> paginationNodes;

    @Getter
    private WebElement paginationNode;

    @Getter
    private List<WebElement> headerItems;
    protected PaginationAdminPanelComponent pagination;

    public GridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        headerItems = new ArrayList<>();
        initHeaderItems();
        setPaginationNode();
        pagination = new PaginationAdminPanelComponent(driver, getPaginationNode());
    }

    private void initHeaderItems() {
        for (WebElement item : headerItemsNodes) {
            if (item.isDisplayed()) {
                headerItems.add(item);
            }
        }
    }

    private void setPaginationNode() {
        this.paginationNode = paginationNodes.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No visible element found"));
    }

    @Step("Check Grid Headers Are Displayed.")
    public boolean isHeadersDisplayed() {
        for (WebElement header : getHeaderItems()) {
            if (!header.isDisplayed()) { return false; }
        }
        return true;
    }

    @Step("Get Grid Headers As List Of Strings.")
    public List<String> getHeaderItemsString() {
        return headerItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Grid checks if the table has next page.")
    public boolean tableHasNextPage() {
        return pagination.hasNextPage();
    }

    @Step("Grid checks if the table has previous page.")
    public boolean tableHasPrevPage() {
        return pagination.hasPrevPage();
    }

}
