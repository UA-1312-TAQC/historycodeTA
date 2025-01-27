package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.ModalInputElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalComponent extends BaseComponent {

    @FindBy(xpath = "./button[@aria-label='Close']")
    protected WebElement closeButton;
    @FindBy(xpath = ".//div[@class='center']//button")
    protected WebElement saveButton;

    @Getter
    protected ModalInputElement inputComponent;

    public ModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputComponent = new ModalInputElement(driver, rootElement);
    }

    @Step("Close Modal With 'X'")
    public void close() {
        closeButton.click();
        waitUntilElementInvisible(closeButton);
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
