package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.elements.adminPanel.InputElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "./button[@aria-label='Close']")
    protected WebElement closeButton;
    @Getter
    @FindBy(xpath = ".//div[@class='center']//button")
    protected WebElement saveButton;

    @Getter
    protected InputElement inputComponent;

    public ModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputComponent = new InputElement(driver, rootElement);
    }

    @Step("Close Modal With 'X'")
    public void close() {
        closeButton.click();
        waitUntilElementInvisible(closeButton);
    }

    public String getSaveButtonTitleString() {
        return saveButton.getText();
    }

    //TODO Check correctness of new InputField root
}
