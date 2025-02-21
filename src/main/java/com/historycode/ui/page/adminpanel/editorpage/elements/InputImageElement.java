package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.historycode.ui.utils.ImageLoader.loadImageUsingRelativePath;

@Getter
public class InputImageElement extends BaseComponent {

    @FindBy(xpath = ".//label[@for='image']")
    private WebElement label;
    @FindBy(xpath = ".//input[@id='image']")
    private WebElement uploadPlace;
    @FindBy(xpath = ".//div[@class='ant-upload-list-item-container']")
    private WebElement uploadedPlace;
    @FindBy(xpath = ".//span[@role='img' and @aria-label='eye']")
    private WebElement previewButton;
    @FindBy(xpath = ".//button[contains(@class, 'ant-btn-icon-only') and @title='Remove file']")
    private WebElement deleteButton;

    public InputImageElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickPreviewButton() {
        waitUntilElementClickable(previewButton);
        scrollToElementJs(previewButton);
        previewButton.click();
    }

    public void clickDeleteButton() {
        waitUntilElementClickable(deleteButton);
        scrollToElementJs(deleteButton);
        deleteButton.click();
    }

    public void uploadImage(String imagePath) {
        loadImageUsingRelativePath(imagePath, uploadPlace);
        actions.moveToElement(uploadedPlace).perform();
        wait.until(ExpectedConditions.visibilityOf(previewButton));
    }

}
