package com.historycode.ui.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseModal extends BaseComponent{
    public BaseModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
