package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContextsModalComponent extends ModalComponent {
    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterContext(String name) {
        inputComponent.setInput(name);
    }
}
