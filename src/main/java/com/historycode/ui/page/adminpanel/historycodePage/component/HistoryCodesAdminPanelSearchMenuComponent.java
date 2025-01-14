package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryCodesAdminPanelSearchMenuComponent extends BaseComponent {

    @FindBy(xpath = "./button[contains(@class, 'ant-btn') and span[text()='Пошук history-кодів']]")
    protected WebElement searchButton;

    @Getter
    @Setter
    protected InputElement searchInput;
    @FindBy(xpath = "./div[@class='ant-input css-k7429z']")
    protected WebElement searchInputNode;

    @Getter
    @Setter
    protected DropdownComponent dropdown;
    @FindBy(xpath = "./div[@class='ant-select-selection-search-input']")
    protected WebElement dropdownNode;

    @FindBy(xpath = "./button[contains(@class, 'ant-btn') and span[text()='Новий history-код']]")
    protected WebElement newHistoryCodeButton;


    public HistoryCodesAdminPanelSearchMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.searchInput = new InputElement(driver, searchInputNode);
        //todo: Replace with a dropDown with multiple choice when it will be developed.
        this.dropdown = new DropdownComponent(driver, dropdownNode);
    }

    //todo: Should these methods return a new window (new StreetcodeEditPage)?
    public void clickSearchButton() {
        BasePage.moveToElement(driver, searchButton);
        searchButton.click();
    }

    public void clickNewHistoryCodeButton() {
        BasePage.moveToElement(driver, newHistoryCodeButton);
        newHistoryCodeButton.click();
    }
}
