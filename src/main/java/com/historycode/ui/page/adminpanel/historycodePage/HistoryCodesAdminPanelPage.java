package com.historycode.ui.page.adminpanel.historycodePage;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelGridComponent;
import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelPage extends BasePageAdminPanel {
    protected AdminMenuBarComponent adminMenuBar;
    protected StreetcodeEditPage streetcodeEditPage;
    protected HistoryCodesAdminPanelGridComponent historyCodesAdminPanel;
    protected PaginationAdminPanelComponent paginationAdminPanel;

    @FindBy(xpath = "")
    protected WebElement adminMenuBarNode;
    @FindBy(xpath = "")
    protected WebElement historyCodesAdminPanelNode;
    @FindBy(xpath = "")
    protected WebElement paginationAdminPanelNode;

    public HistoryCodesAdminPanelPage(WebDriver driver) {
        super(driver);
        this.streetcodeEditPage = new StreetcodeEditPage(driver);
        this.adminMenuBar = new AdminMenuBarComponent(driver, adminMenuBarNode);
        this.historyCodesAdminPanel = new HistoryCodesAdminPanelGridComponent(driver, historyCodesAdminPanelNode);
        this.paginationAdminPanel = new PaginationAdminPanelComponent(driver, paginationAdminPanelNode);
    }
}
