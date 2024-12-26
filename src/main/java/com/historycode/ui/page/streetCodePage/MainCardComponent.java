package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainCardComponent extends BaseComponent {
    private KeywordPersonsModal keywordPersonsModal;

    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keywordPersonsModal = new KeywordPersonsModal(driver, rootElement);
    }
}
