package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public ContextsModalComponent saveNewContext() {
        clickSaveButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(rootElement));
        return this;
    }

    public void closeModal() {
        clickCloseButton();
    }
}
