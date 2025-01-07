package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoriesModalComponent extends ModalComponent {
    public CategoriesModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterCategory(String name) {
        inputComponent.setInput(name);
    }
}
