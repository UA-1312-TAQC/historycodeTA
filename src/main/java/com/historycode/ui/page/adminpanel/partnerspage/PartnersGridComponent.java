package com.historycode.ui.page.adminpanel.partnerspage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;

import lombok.Getter;

@Getter
public class PartnersGridComponent extends BaseGridComponent {

    List<PartnersRowComponent> partnersRowComponents;

    public PartnersGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement, headerItems, pagination);
    }
}
