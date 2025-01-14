package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.elements.adminPanel.InputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelSearchMenuComponent extends BaseComponent {
    // змінити xpath contains text 
    @FindBy(xpath = "./div[@class='ant-btn css-k7429z ant-btn-default Button']")
    protected WebElement searchButton;
    protected InputElement searchInput;
    protected DropdownComponent dropdown;
    @FindBy(xpath = "./div[@class='ant-btn css-k7429z ant-btn-default Button']")
    protected WebElement newHistoryCodeButton;

    @FindBy(xpath = "./div[@class='ant-input css-k7429z']")
    protected WebElement searchInputNode;
    @FindBy(xpath = "./div[@class='ant-select-selection-search-input']")
    protected WebElement dropdownNode;

    public HistoryCodesAdminPanelSearchMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.searchInput = new InputElement(driver, searchInputNode);
        this.dropdown = new DropdownComponent(driver, dropdownNode);
    }
}
