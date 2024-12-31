package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseEditModal extends BaseModal {
    @FindBy(xpath = "//button[@class='ant-modal-close']/span")
    protected WebElement closeButton;

    @FindBy(xpath = "//div[@class='ant-popover-content']//div[@class='ant-popover-inner-content']")
    protected WebElement tooltip;

    @FindBy(xpath = "//div[@class='center']//h2")
    protected WebElement title;

    @FindBy(xpath = "//div[@class='center']//button/span")
    protected WebElement saveButton;

    protected Actions actions;
    protected WebDriverWait wait;

    public BaseEditModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        try {
            closeButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking close button: " + e.getMessage());
        }
    }

    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }

    public void clickSaveButton() {
        if (isSaveButtonEnabled()) {
            waitUntilElementClickable(saveButton);
            try {
                saveButton.click();
            } catch (Exception e) {
                System.err.println("Error clicking save button: " + e.getMessage());
            }
        } else {
            System.err.println("Save button is not enabled.");
        }
    }

    private void waitUntilElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.err.println("Error waiting for element to be visible: " + e.getMessage());
        }
    }

    private void waitUntilElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.err.println("Error waiting for element to be clickable: " + e.getMessage());
        }
    }
}
