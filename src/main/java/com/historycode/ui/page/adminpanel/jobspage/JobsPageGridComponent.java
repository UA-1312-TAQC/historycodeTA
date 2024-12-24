package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class JobsPageGridComponent extends BaseGridComponent {
    List<JobsRowComponent> jobsRowComponents;
    
    public JobsPageGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement, headerItems, pagination);
    }
}
