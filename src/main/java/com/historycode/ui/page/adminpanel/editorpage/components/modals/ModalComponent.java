package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.EditorBasePage;
import com.historycode.ui.page.adminpanel.editorpage.elements.modalInputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalComponent extends BaseComponent {

    @FindBy(xpath = "./button[@aria-label='Close']")
    protected WebElement closeButton;
    @FindBy(xpath = "./button")
    protected WebElement saveButton;

    public modalInputElement inputComponent;

    public ModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputComponent = new modalInputElement(driver, rootElement);
    }

    public void close() {
        closeButton.click();
    }

    public void save() {
        saveButton.click();
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public String getSaveButtonTitleString() {
        return saveButton.getText();
    }

    public String getInputTitleString() {
        return inputComponent.getLabelString();
    }
}
