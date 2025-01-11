package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsModalComponent extends ModalComponent {

    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    public TagsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterTag(String name) {
        inputComponent.setInput(name);
    }

    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public WebElement getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.getText();
    }
}
