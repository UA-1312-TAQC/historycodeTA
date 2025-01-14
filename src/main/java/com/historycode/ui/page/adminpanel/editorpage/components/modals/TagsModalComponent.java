package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TagsModalComponent extends ModalComponent {
    public TagsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterTag(String name) {
        inputComponent.setInput(name);
    }
}
