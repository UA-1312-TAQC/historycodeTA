package com.historycode.ui.page.adminpanel.historycodePage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelGridComponent;
import com.historycode.ui.page.adminpanel.historycodePage.component.HistoryCodesAdminPanelSearchMenuComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelPage extends BasePageAdminPanel {

    @Getter
    protected HistoryCodesAdminPanelSearchMenuComponent historyCodesAdminPanelSearchMenuComponent;
    @FindBy(xpath = "//div[@class='searchMenu']")
    protected WebElement historyCodesAdminPanelSearchMenuComponentNode;


    @Getter
    protected HistoryCodesAdminPanelGridComponent historyCodesAdminPanelGridComponent;
    @FindBy(xpath = "//div[@class='StreetcodeTableWrapper']")
    protected WebElement historyCodesAdminPanelGridComponentNode;

    public HistoryCodesAdminPanelPage(WebDriver driver) {
        super(driver);
        this.historyCodesAdminPanelSearchMenuComponent = new HistoryCodesAdminPanelSearchMenuComponent(driver, historyCodesAdminPanelSearchMenuComponentNode);
        this.historyCodesAdminPanelGridComponent = new HistoryCodesAdminPanelGridComponent(driver, historyCodesAdminPanelGridComponentNode);
    }
}
