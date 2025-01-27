package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopUpMessageComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class, 'ant-message-notice-success')]")
    private WebElement successPopUpWindowRoot;

    @FindBy(xpath = "//div[contains(@class, 'ant-message-notice-error')]")
    private WebElement errorPopUpWindowRoot;

    private static final String MESSAGE_PATH = ".//span[not(@role='img')]";

    public PopUpMessageComponent(WebDriver driver) {
        super(driver);
    }

    private boolean isPopUpDisplayed(WebElement popupElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(popupElement));
            return popupElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private String getMessageText(WebElement popupElement) {
        WebElement message = popupElement.findElement(By.xpath(MESSAGE_PATH));
        return message.getText();
    }

    public boolean isSuccessPopUpDisplayed() {
        return isPopUpDisplayed(successPopUpWindowRoot);
    }

    public String getSuccessMessage() {
        return getMessageText(successPopUpWindowRoot);
    }

    public boolean isErrorPopUpDisplayed() {
        return isPopUpDisplayed(errorPopUpWindowRoot);
    }

    public String getErrorMessage() {
        return getMessageText(errorPopUpWindowRoot);
    }
}
