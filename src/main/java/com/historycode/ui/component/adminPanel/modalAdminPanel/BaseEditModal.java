package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public abstract class BaseEditModal extends BaseModal {
    @FindBy(xpath = ".//button[@class='ant-modal-close']/span")
    protected WebElement closeButton;

    @FindBy(xpath = ".//div[@class='ant-popover-content']//div[@class='ant-popover-inner-content']")
    protected WebElement tooltip;

    @FindBy(xpath = ".//div[@class='center']//h2")
    protected WebElement title;

    @FindBy(xpath = ".//div[@class='center']//button")
    protected WebElement saveButton;

    public BaseEditModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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

    public void clickCloseButton() {
        waitUntilElementClickable(closeButton);
        closeButton.click();
        wait.until(ExpectedConditions.invisibilityOf(rootElement));
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
