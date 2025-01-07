package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PositionsModalComponent extends ModalComponent {
    public PositionsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterPosition(String name) {
        inputComponent.setInput(name);
    }
}
