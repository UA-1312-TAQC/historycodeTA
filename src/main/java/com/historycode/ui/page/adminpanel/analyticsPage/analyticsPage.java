package com.historycode.ui.page.adminpanel.analyticsPage;

import com.historycode.ui.component.sectionsAdminPanel.SectionsAdminPanelComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class analyticsPage extends BasePageAdminPanel {

    public analyticsPage(WebDriver driver, SectionsAdminPanelComponent sectionsAdminPanelComponent) {
        super(driver);
        this.sectionsAdminPanelComponent = sectionsAdminPanelComponent;
        //TODO add streetCodeName initialization
    }
    private SectionsAdminPanelComponent sectionsAdminPanelComponent;

    private WebElement streetCodeName;

    private WebElement statisticTable;
    //TODO change to proper component
}
