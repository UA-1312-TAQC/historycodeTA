package com.historycode.ui.page.adminpanel.teamPage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TeamPage extends BasePageAdminPanel {

    private TeamManagementGridComponent teamManagementGrid;
    protected WebElement rootElement;

    public TeamPage(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        this.teamManagementGrid = new TeamManagementGridComponent(driver, rootElement);
    }
}
