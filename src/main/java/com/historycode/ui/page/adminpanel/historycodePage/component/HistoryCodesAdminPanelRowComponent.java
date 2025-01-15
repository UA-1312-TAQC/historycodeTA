package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.page.adminpanel.StreetcodeEditPage;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelStatisticsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelRowComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][1]")
    private WebElement name;

    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][2]")
    private WebElement id;

    @Getter
    private final DropdownComponent dropDown;
    @FindBy(xpath = "./td[@class='ant-table-cell'][3]//button[@class='ant-btn css-k7429z ant-btn-default ant-dropdown-trigger']")
    private WebElement dropDownNode;

    @Getter
    @FindBy(xpath = "./td[@class='ant-table-cell'][4]")
    private WebElement Data;

    @FindBy(xpath = ".//td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
    private WebElement editPageButton;

    @FindBy(xpath = "./td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    private WebElement deleteButton;

    @FindBy(xpath = "./td[@class='ant-table-cell'][5]//span[contains(@class, 'anticon-bar-chart')]//*[name()='svg']")
    private WebElement statisticsPageButton;

    public HistoryCodesAdminPanelRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.dropDown = new DropdownComponent(driver, dropDownNode);
    }

    public String getTitleString() {
        return name.getText();
    }

    public StreetcodeEditPage clickEdit() {
        BasePage.moveToElement(driver, editPageButton);
        editPageButton.click();
        return new StreetcodeEditPage(driver);
    }

    public void clickDelete() {
        BasePage.moveToElement(driver, deleteButton);
        deleteButton.click();
    }

    public HistoryCodesAdminPanelStatisticsPage clickStatistics() {
        BasePage.moveToElement(driver, statisticsPageButton);
        statisticsPageButton.click();
        return new HistoryCodesAdminPanelStatisticsPage(driver);
    }
}
