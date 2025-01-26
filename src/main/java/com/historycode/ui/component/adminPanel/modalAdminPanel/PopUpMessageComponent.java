package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopUpMessageComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class, 'ant-message-notice-success')]")
    private WebElement successPopUpWindowRoot;

    @FindBy(xpath = "//div[contains(@class, 'ant-message-notice-error')]")
    private WebElement errorPopUpWindowRoot;

    protected String MESSAGE_PATH = ".//span[not(@role='img')]";

    private final WebDriverWait wait;

    public PopUpMessageComponent(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isSuccessPopUpDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successPopUpWindowRoot));
            return successPopUpWindowRoot.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Locating the success message dynamically within the popup
    public String getSuccessMessage() {
        WebElement message = successPopUpWindowRoot.findElement(By.xpath(MESSAGE_PATH));
        return message.getText();
    }

    public boolean isErrorPopUpDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorPopUpWindowRoot));
            return errorPopUpWindowRoot.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        WebElement message = errorPopUpWindowRoot.findElement(By.xpath(MESSAGE_PATH));
        return message.getText();
    }

}
