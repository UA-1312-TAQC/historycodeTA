package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsModalComponent extends BaseCreateEditModalComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='center']/h2")
    private WebElement title;

    public TagsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Tag Name Input.")
    public TagsModalComponent enterTag(String name) {
        inputComponent.setInputField(name);
        return new TagsModalComponent(driver, rootElement);
    }

    @Step("Set another '{name}' Into The Tag Name Input.")
    public TagsModalComponent setTag(String name) {
        inputComponent.setInputField(name);
        return new TagsModalComponent(driver, rootElement);
    }

    @Step("Check Tag Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Tags Modal Save Button.")
    public TagsModalComponent save() {
        saveButton.click();
        sleep(1000); //ToDo Remove it
        return new TagsModalComponent(driver, rootElement);
    }
}
