package com.historycode.ui.page.adminpanel.newspage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;

@Getter
public class NewsGridComponent extends BaseGridComponent {
    public NewsGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement, headerItems, pagination);
    }

    List<NewsRowComponent> teamRowComponents;
}
