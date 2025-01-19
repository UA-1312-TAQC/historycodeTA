package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseCreateModal extends BaseModal {

    @FindBy(xpath = ".//button[@class='ant-modal-close']/span")
    protected WebElement closeButton;

    @FindBy(xpath = ".//div[@class='ant-popover-content']//div[@class='ant-popover-inner-content']")
    protected WebElement tooltip;

    @FindBy(xpath = ".//div[@class='center']//h2")
    protected WebElement title;

    @FindBy(xpath = ".//div[@class='center']//button/span")
    protected WebElement saveButton;

    public BaseCreateModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isModalDisplayed() {
        return rootElement.isDisplayed();
    }

    public boolean isTooltipVisible() {
        return tooltip.isDisplayed();
    }

    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }

    public boolean isCloseButtonEnabled() {
        return closeButton.isEnabled();
    }

    public void hoverOverCloseButton() {
        actions.moveToElement(closeButton).perform();
    }

    public String getTooltipText() {
        waitUntilElementVisible(tooltip);
        return tooltip.getText();
    }

    public void clickCloseButton() {
        if (isCloseButtonEnabled()) {
            waitUntilElementClickable(closeButton);
            closeButton.click();
        }
    }

    public void clickSaveButton() {
        if (isSaveButtonEnabled()) {
            waitUntilElementClickable(saveButton);
            sleep(2000);
            saveButton.click();
            sleep(2000);
        }
    }
}
