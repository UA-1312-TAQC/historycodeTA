package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelStatisticsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelRowComponent extends BaseComponent {

    @FindBy(xpath = "./td[@class='ant-table-cell']")
    private WebElement name;
    @FindBy(xpath = "./td[@class='ant-table-cell']")
    private WebElement id;
    private DropdownComponent dropDown;
    @FindBy(xpath = "./button[@class='ant-btn css-k7429z ant-btn-default ant-dropdown-trigger']")
    private WebElement dropDownNode;
    @FindBy(xpath = "./td[@class='ant-table-cell']")
    private WebElement Data;
    @FindBy(xpath = "./span[@class='anticon anticon-edit actionButton']")
    private WebElement editPageButton;
    @FindBy(xpath = "./span[@class='anticon anticon-delete actionButton']")
    private WebElement deleteButton;
    @FindBy(xpath = "./span[@class='anticon anticon-bar-chart actionButton']")
    private WebElement statisticsPageButton;

    public HistoryCodesAdminPanelRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.dropDown = new DropdownComponent(driver, dropDownNode);
    }
}
