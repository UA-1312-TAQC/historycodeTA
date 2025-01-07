package com.historycode.ui.component.adminPanel.gridAdminPanel;


import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BaseGridComponent extends BaseComponent {

    private final String NO_DATA_COMPONENT_ROOT_ELEMENT_CSS = ".ant-table-tbody .ant-table-cell .ant-empty";


    @FindBy(xpath = "//thead[@class = 'ant-table-thead']//th")
    protected List<WebElement> headerItems;

    @Getter
    protected PaginationAdminPanelComponent pagination;

    @Getter
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

    public NoDataComponent getNoDataComponent(){
        if(noDataComponent == null){
            WebElement root = driver.findElement(By.cssSelector(NO_DATA_COMPONENT_ROOT_ELEMENT_CSS));
            noDataComponent = new NoDataComponent(driver, root);
        }
        return noDataComponent;
    }

    //TODO запитати де реалізувати методи пагінацій( на сторінці чи тут )
}
