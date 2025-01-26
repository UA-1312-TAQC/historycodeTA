package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessPopupComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class, 'ant-message-notice-success')]")
    private WebElement successPopUpWindow;

    protected String SUCCESS_MESSAGE_PATH = ".//span[not(@role='img')]";

    private final WebDriverWait wait;

    public SuccessPopupComponent(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isSuccessPopUpDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successPopUpWindow));
            return successPopUpWindow.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        //Locate the success message dynamically within the popup
        WebElement message = successPopUpWindow.findElement(By.xpath(SUCCESS_MESSAGE_PATH));
        return message.getText();
    }

}
