package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsModalComponent extends ModalComponent {

    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    public TagsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Tag Name Input.")
    public TagsModalComponent enterTag(String name) {
        inputComponent.setInput(name);
        return new TagsModalComponent(driver, rootElement);
    }

    @Step("Check Tag Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public WebElement getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Tags Modal Save Button.")
    public TagsModalComponent save() {
        saveButton.click();
        return new TagsModalComponent(driver, rootElement);
    }
}
