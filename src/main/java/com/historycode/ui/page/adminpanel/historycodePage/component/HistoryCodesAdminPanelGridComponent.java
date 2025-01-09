package com.historycode.ui.page.adminpanel.historycodePage.component;


import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryCodesAdminPanelGridComponent extends BaseGridComponent {

    protected List<HistoryCodesAdminPanelRowComponent> rows;
    @FindBy(xpath = "")
    protected List<WebElement> rowNodes;

    public HistoryCodesAdminPanelGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
