package com.historycode.ui.elements.adminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.historycode.ui.utils.ImageLoader.loadImageUsingRelativePath;

public class LogoElement extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item-label')]/label")
    private WebElement label;

    @FindBy(xpath = ".//input[@id='logo']")
    private WebElement logoImage;

    @FindBy(xpath = ".//span[@class='ant-upload']/p")
    private WebElement logoInsideHint;

    @FindBy(xpath = ".//span[@role='img' and @aria-label='eye']")
    private WebElement previewButton;

    @FindBy(xpath = ".//button[contains(@class, 'ant-btn-icon-only') and @title='Remove file']")
    private WebElement deleteButton;


    public LogoElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void uploadLogo(String imagePath) {
        loadImageUsingRelativePath(imagePath, logoImage);
    }

    public void clickPreviewButton() {
        waitUntilElementClickable(previewButton);
        previewButton.click();
    }

    public void clickDeleteButton() {
        waitUntilElementClickable(deleteButton);
        deleteButton.click();
    }
}
