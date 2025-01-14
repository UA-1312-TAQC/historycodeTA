package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsModalComponent extends BaseEditModal {

    @FindBy(xpath = "//label[@for = 'context']/../..")
    private WebElement contextContainer;

    private InputElement contextInput;

    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.contextInput = new InputElement(driver, contextContainer);
    }

    public ContextsModalComponent inputNewContext(String name) {
        contextInput.setInputField(name);
        return this;
    }

    public ContextsModalComponent saveNewContext() throws InterruptedException {
        clickSaveButton();
        Thread.sleep(4000);
        return this;
    }

    public void closeModal() {
        clickCloseButton();
    }
}


