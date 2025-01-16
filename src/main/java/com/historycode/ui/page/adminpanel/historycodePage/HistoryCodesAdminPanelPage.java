package com.historycode.ui.page.adminpanel.historycodePage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelGridComponent;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelSearchMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelPage extends BasePageAdminPanel {

    protected HistoryCodesAdminPanelSearchMenuComponent historyCodesAdminPanelSearchMenuComponent;
    @FindBy(xpath = "//div[@class='searchMenu']")
    private WebElement historyCodesAdminPanelSearchMenuComponentNode;

    protected HistoryCodesAdminPanelGridComponent historyCodesAdminPanelGridComponent;
    @FindBy(xpath = "//div[@class='StreetcodeTableWrapper']")
    private WebElement historyCodesAdminPanelGridComponentNode;

    public HistoryCodesAdminPanelPage(WebDriver driver) {
        super(driver);
    }

    public HistoryCodesAdminPanelSearchMenuComponent getHistoryCodesAdminPanelSearchMenuComponent() {
        if (historyCodesAdminPanelSearchMenuComponent == null) {
            this.historyCodesAdminPanelSearchMenuComponent = new HistoryCodesAdminPanelSearchMenuComponent(driver, historyCodesAdminPanelSearchMenuComponentNode);
        }
        return historyCodesAdminPanelSearchMenuComponent;
    }

    public HistoryCodesAdminPanelGridComponent getHistoryCodesAdminPanelGridComponent() {
        if (historyCodesAdminPanelGridComponent == null) {
            this.historyCodesAdminPanelGridComponent = new HistoryCodesAdminPanelGridComponent(driver, historyCodesAdminPanelGridComponentNode);
        }
        return historyCodesAdminPanelGridComponent;
    }
}
