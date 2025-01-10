package com.historycode.ui.page.adminpanel.historycodePage;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelGridComponent;
import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelPage extends BasePageAdminPanel {
    protected AdminMenuBarComponent adminMenuBarComponent;
    protected StreetcodeEditPage streetcodeEditPage;
    protected HistoryCodesAdminPanelGridComponent historyCodesAdminPanelGridComponent;

    @Getter @FindBy(xpath = "//div[@class='searchMenu']")
    protected WebElement adminMenuBarComponentNode;
    @Getter @FindBy(xpath = "//div[@class='StreetcodeTableWrapper']")
    protected WebElement historyCodesAdminPanelGridComponentNode;

    public HistoryCodesAdminPanelPage(WebDriver driver) {
        super(driver);
        this.streetcodeEditPage = new StreetcodeEditPage(driver);
        this.adminMenuBarComponent = new AdminMenuBarComponent(driver, adminMenuBarComponentNode);
        this.historyCodesAdminPanelGridComponent = new HistoryCodesAdminPanelGridComponent(driver,historyCodesAdminPanelGridComponentNode);
    }
}
