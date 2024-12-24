package com.historycode.ui.page.adminpanel.teamPage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class TeamPageGridComponent extends BaseGridComponent {

    List<TeamRowComponent> teamRowComponents;

    public TeamPageGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement, headerItems, pagination);
    }

}
