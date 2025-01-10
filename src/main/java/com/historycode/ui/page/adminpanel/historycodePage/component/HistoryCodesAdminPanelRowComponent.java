package com.historycode.ui.page.adminpanel.historycodePage.component;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HistoryCodesAdminPanelRowComponent extends BaseComponent {
    private DropdownComponent dropdown;

    public HistoryCodesAdminPanelRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
