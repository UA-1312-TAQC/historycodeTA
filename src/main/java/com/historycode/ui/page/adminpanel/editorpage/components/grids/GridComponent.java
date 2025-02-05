package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GridComponent extends BaseGridComponent {

    @FindBy(xpath = "//div[@id='loadingGif']")
    public WebElement loading;
    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> rowNodes;
    @FindBy(xpath = "//thead//th")
    private List<WebElement> headerItemsNodes;
    @FindBy(xpath = "//div[@class='underTableElement']")
    private WebElement paginationNode;

    @Getter
    private List<WebElement> headerItems;
    protected PaginationAdminPanelComponent pagination;

    public GridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        waitUntilElementInvisible(loading);
        headerItems = new ArrayList<>();
        pagination = new PaginationAdminPanelComponent(driver, paginationNode);
        initHeaderItems();
        System.out.println("Grid was created");
    }

    private void initHeaderItems() {
        for (WebElement item : headerItemsNodes) {
            if (item.isDisplayed()) {
                headerItems.add(item);
            }
        }
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

    @Step("Click On The Next Page Pictogram.")
    public GridComponent clickNextPage() {
        pagination.clickNextPage();
        waitUntilElementInvisible(loading);
        return createInstance();
    }

    @Step("Click On The Previous Page Pictogram.")
    public GridComponent clickPrevPage() {
        pagination.clickPrevPage();
        waitUntilElementInvisible(loading);
        return createInstance();
    }

    @Step("Click On The Previous Five Pages Pictogram.")
    public GridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        waitUntilElementInvisible(loading);
        return createInstance();
    }

    @Step("Click On The Next Five Pages Pictogram.")
    public GridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        waitUntilElementInvisible(loading);
        return createInstance();
    }

    @Step("Click On The Page #{index} Pictogram.")
    public GridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        waitUntilElementInvisible(loading);
        return createInstance();
    }

    protected GridComponent createInstance() {
        try {
            return this
                    .getClass()
                    .getDeclaredConstructor(WebDriver.class, WebElement.class)
                    .newInstance(driver, rootElement);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + this.getClass().getSimpleName(), e);
        }
    }
}
