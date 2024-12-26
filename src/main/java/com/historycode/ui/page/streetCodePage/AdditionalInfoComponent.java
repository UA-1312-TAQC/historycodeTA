package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdditionalInfoComponent extends BaseComponent {
    private AdditionalInfoModal additionalInfoModal;
    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.additionalInfoModal = new AdditionalInfoModal(driver, rootElement);
    }
}
