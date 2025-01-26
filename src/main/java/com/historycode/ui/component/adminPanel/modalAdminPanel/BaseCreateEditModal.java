package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public abstract class BaseCreateEditModal extends BaseModal {
    @FindBy(xpath = ".//button[@class='ant-modal-close']/span")
    protected WebElement closeButton;

    @FindBy(xpath = ".//div[@class='ant-popover-content']//div[@class='ant-popover-inner-content']")
    protected WebElement tooltip;

    @FindBy(xpath = ".//div[@class='center']//h2")
    protected WebElement title;

    @FindBy(xpath = ".//div[@class='center']//button/span")
    protected WebElement saveButton;


    @FindBy(xpath = "//div[@class = 'ant-message-notice-content']")
    protected WebElement tooltipConfirmation;

    protected Actions actions;

    public BaseCreateEditModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.actions = new Actions(driver);
    }

    public boolean isModalDisplayed() {
        return rootElement.isDisplayed();
    }

    public void hoverOverCloseButton() {
        actions.moveToElement(closeButton).perform();
    }

    public boolean isTooltipVisible() {
        return tooltip.isDisplayed();
    }

    public String getTooltipText() {
        waitUntilElementVisible(tooltip);
        return tooltip.getText();
    }

    public boolean isCloseButtonEnabled() { return closeButton.isEnabled(); }

    public void clickCloseButton() {
        waitUntilElementClickable(closeButton);
        closeButton.click();
    }

    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }

    public void clickSaveButton() {
        if (isSaveButtonEnabled()) {
            waitUntilElementClickable(saveButton);
            saveButton.click();
        }
    }

}
