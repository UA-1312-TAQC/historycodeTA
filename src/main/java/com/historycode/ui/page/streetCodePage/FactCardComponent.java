package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class FactCardComponent extends BaseComponent {
    private FactCardModal factCardModal;

    public FactCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCardModal = new FactCardModal(driver, rootElement);
    }
}
