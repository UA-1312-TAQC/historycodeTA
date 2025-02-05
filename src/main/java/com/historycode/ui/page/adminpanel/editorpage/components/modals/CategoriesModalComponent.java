package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesModalComponent extends ModalComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    public CategoriesModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Category Name Input.")
    public CategoriesModalComponent enterCategory(String name) {
        inputComponent.setInputField(name);
        return new CategoriesModalComponent(driver, rootElement);
    }

    @Step("Check Category Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Categories Modal Save Button.")
    public CategoriesModalComponent save() {
        saveButton.click();
        return new CategoriesModalComponent(driver, rootElement);
    }
}
