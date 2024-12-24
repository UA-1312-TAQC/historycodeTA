package com.historycode.ui.component.adminPanel.gridAdminPanel;


import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public abstract class BaseGridComponent extends BaseComponent {
    List<WebElement> headerItems;
    PaginationAdminPanelComponent pagination;

    public BaseGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement);
        this.headerItems = headerItems;
        this.pagination = pagination;
    }
}
