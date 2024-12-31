package com.historycode.ui.component.adminPanel.gridAdminPanel;


import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public abstract class BaseGridComponent extends BaseComponent {
    @FindBy(xpath = "//thead[@class = 'ant-table-thead']//th")
    protected List<WebElement> headerItems;

    protected PaginationAdminPanelComponent pagination;

    @FindBy(xpath = "//div[@class = 'underTableElement']//ul")
    protected WebElement rootPaginationNode;

    public BaseGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.pagination = new PaginationAdminPanelComponent(driver, rootPaginationNode);
    }

    public void goToNextPage() {
        pagination.clickNextPage();
    }

    public void goToPreviousPage() {
        pagination.clickPrevPage();
    }

    public void goToSelectedPage(int pageNumber) {
        pagination.clickPaginationItem(pageNumber);
    }

    public void goToPreviousFivePages() {
        pagination.clickPrevFivePages();
    }

    public void goToNextFivePages() {
        pagination.clickNextFivePages();
    }
}
