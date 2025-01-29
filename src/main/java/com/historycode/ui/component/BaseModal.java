package com.historycode.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public abstract class BaseModal extends BaseComponent {

    @FindBy(xpath = ".//button[@class='ant-modal-close']")
    private WebElement closeButton;


    @FindBy(xpath = ".//button[@class='ant-btn css-k7429z ant-btn-default streetcode-custom-button']")
    private WebElement saveButton;


    @FindBy(xpath = ".//div[@class='center']/h2")
    private WebElement modTitle;

    public BaseModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean checkTitleOfModal(String expectedText) {
        String actualText = modTitle.getText().trim().replaceAll("\\s+", " ");
        return actualText.equals(expectedText);
    }

    public boolean checkAttribute(WebElement element, String attributeName, String expectedValue) {
        String attributeValue = element.getAttribute(attributeName);
        return expectedValue.equals(attributeValue);
    }

    public boolean isAttributeAbsent(WebElement element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue == null;
    }

    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }

    public void clickCloseButton() {
        waitUntilElementClickable(closeButton);
        closeButton.click();
    }

    public void clickSaveButton() {
        if (isSaveButtonEnabled()) {
            waitUntilElementClickable(saveButton);
            saveButton.click();
        }
    }

}
