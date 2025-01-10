package com.historycode.ui.page.adminpanel.teampage.editModal.photoElement;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhotoWindowComponent extends BaseComponent {

    @FindBy(xpath = "//a[contains(@class, 'ant-upload-list-item-thumbnail')]/img")
    private WebElement uploadedPhoto;

    @FindBy(xpath = "//span[@role='img' and contains(@class, 'anticon-picture')]")
    private WebElement placeholderIcon;

    @FindBy(xpath = "//span[@role='img' and @aria-label='eye']")
    private WebElement previewButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn-icon-only') and @title='Remove file']")
    private WebElement deleteButton;

    private final PhotoModalComponent photoModalComponent;

    public PhotoWindowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.photoModalComponent = new PhotoModalComponent(driver, rootElement);
    }

    public boolean isPhotoUploaded() {
        try {
            return uploadedPhoto.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPlaceholderClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(placeholderIcon));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickPreviewButton() {
        previewButton.click();
        photoModalComponent.waitForModalToAppear();
    }

    public void closePreviewModal() {
        photoModalComponent.close();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }
}
