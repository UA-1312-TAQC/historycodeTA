package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsModalComponent extends BaseCreateEditModalComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    @Getter
    @FindBy(xpath = ".//div[@class='ant-form-item-explain-error']")
    private WebElement error;

    public PositionsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Position Name Input.")
    public PositionsModalComponent enterPosition(String name) {
        inputComponent.setInputField(name);
        return new PositionsModalComponent(driver, rootElement);
    }

    @Step("Check Position Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Positions Modal Save Button.")
    public PositionsModalComponent save() {
        saveButton.click();
        return new PositionsModalComponent(driver, rootElement);
    }
}
