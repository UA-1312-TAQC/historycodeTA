package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelSearchMenuComponent extends BaseComponent {

    @FindBy(xpath = ".//button[contains(@class, 'ant-btn') and span[text()='Пошук history-кодів']]")
    protected WebElement searchButton;

    @Getter
    @Setter
    protected InputElement searchInput;
    @Getter
    @Setter
    protected DropdownComponent dropdown;
    @FindBy(xpath = ".//button[contains(@class, 'ant-btn') and span[text()='Новий history-код']]")
    protected WebElement newHistoryCodeButton;
    @FindBy(xpath = ".//div[@class='ant-input css-k7429z']")
    private WebElement searchInputNode;
    @FindBy(xpath = ".//div[@class='ant-select-selection-search-input']")
    private WebElement dropdownNode;


    public HistoryCodesAdminPanelSearchMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.searchInput = new InputElement(driver, searchInputNode);
        //todo: Replace with a dropDown with multiple choice when it will be developed.
        this.dropdown = new DropdownComponent(driver, dropdownNode);
    }

    public HistoryCodesAdminPanelPage clickSearchButton() {
        Actions actions = new Actions(driver).moveToElement(searchButton);
        searchButton.click();
        return new HistoryCodesAdminPanelPage(driver);
    }

    public StreetcodeEditPage clickNewHistoryCodeButton() {
        Actions actions = new Actions(driver).moveToElement(newHistoryCodeButton);
        newHistoryCodeButton.click();
        return new StreetcodeEditPage(driver);
    }
}
