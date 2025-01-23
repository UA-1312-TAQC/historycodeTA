package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public abstract class BaseCreateModal extends BaseModal {

    @FindBy(xpath = ".//button[@class='ant-modal-close']")
    protected WebElement closeButton;

    @FindBy(xpath = ".//div[@class='ant-popover-content']//div[@class='ant-popover-inner-content']")
    protected WebElement tooltip;

    @FindBy(xpath = ".//div[@class='center']//h2")
    protected WebElement title;

    @FindBy(xpath = ".//div[@class='center']//button")
    protected WebElement saveButton;

    @FindBy(xpath = ".//span[@class='ant-upload']")
    protected WebElement uploadLogo;

    @FindBy(xpath = "//div[@class = 'ant-message-notice-content']")
    protected WebElement tooltipConfirmation;

    @FindBy(xpath = "//div[contains(@class, 'ant-message-success')]")
    protected WebElement saveConfirmation;

    @FindBy(xpath = "//div[contains(@class, 'ant-message-error')]")
    protected WebElement errorConfirmation;

    protected Actions actions;

    public BaseCreateModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.actions = new Actions(driver);
    }

    public boolean isModalDisplayed() {
        return rootElement.isDisplayed();
    }

    public boolean isTooltipVisible() {
        return tooltip.isDisplayed();
    }

    public boolean isErrorConfirmationDisplayed() { return  errorConfirmation.isDisplayed(); }

    public boolean isSaveButtonEnabled() { return saveButton.isEnabled(); }

    public boolean isCloseButtonEnabled() { return closeButton.isEnabled(); }

    public void hoverOverCloseButton() {
        actions.moveToElement(closeButton).perform();
    }

    public String getTooltipText() {
        waitUntilElementVisible(tooltip);
        return tooltip.getText();
    }

}
