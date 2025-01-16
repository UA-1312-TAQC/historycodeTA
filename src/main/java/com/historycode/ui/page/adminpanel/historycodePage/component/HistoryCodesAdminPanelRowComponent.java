package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.page.adminpanel.StreetcodeEditPage;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelStatisticsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelRowComponent extends BaseComponent {

    @Getter
    protected final DropdownComponent dropDown;
    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][1]")
    protected WebElement name;
    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][2]")
    protected WebElement id;
    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][4]")
    protected WebElement Data;
    @FindBy(xpath = ".//td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    protected WebElement editPageButton;
    @FindBy(xpath = "./td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    protected WebElement deleteButton;
    @FindBy(xpath = "./td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-bar-chart')]//*[name()='svg']")
    protected WebElement statisticsPageButton;
    @FindBy(xpath = "./td[@class='ant-table-cell'][3]//button[@class='ant-btn css-k7429z ant-btn-default ant-dropdown-trigger']")
    private WebElement dropDownNode;

    public HistoryCodesAdminPanelRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.dropDown = new DropdownComponent(driver, dropDownNode);
    }

    public String getTitleString() {
        return name.getText();
    }

    public StreetcodeEditPage clickEdit() {

        actions.moveToElement(editPageButton);
        editPageButton.click();
        return new StreetcodeEditPage(driver);
    }

    public void clickDelete() {
        actions.moveToElement(deleteButton);
        deleteButton.click();
    }

    public HistoryCodesAdminPanelStatisticsPage clickStatistics() {
        actions.moveToElement(statisticsPageButton);
        statisticsPageButton.click();
        return new HistoryCodesAdminPanelStatisticsPage(driver);
    }
}
