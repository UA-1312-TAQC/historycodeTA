package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsModalComponent extends ModalComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='center']/h2")
    private WebElement title;

    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Context Name Input.")
    public ContextsModalComponent enterContext(String name) {
        inputComponent.setInputField(name);
        return new ContextsModalComponent(driver, rootElement);
    }

    @Step("Check Context Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Contexts Modal Save Button.")
    public ContextsModalComponent save() {
        sleep(1000);
        saveButton.click();
        sleep(1000);
        return new ContextsModalComponent(driver, rootElement);
    }
}
