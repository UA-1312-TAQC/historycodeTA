package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsModalComponent extends ModalComponent {

    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    public PositionsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Position Name Input.")
    public PositionsModalComponent enterPosition(String name) {
        inputComponent.setInput(name);
        return new PositionsModalComponent(driver, rootElement);
    }

    @Step("Check Position Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public WebElement getTitle() {
        return title;
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
