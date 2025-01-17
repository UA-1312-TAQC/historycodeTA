package com.historycode.ui.elements.adminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoElement extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item-label')]/label")
    private WebElement label;

    @Getter
    @FindBy(xpath = ".//input[@id='logo']")
    private WebElement logoImage;

    @Getter
    @FindBy(xpath = ".//span[@class='ant-upload']/p")
    private WebElement logoInsideHint;

    @Getter
    @FindBy(xpath = ".//span[@role='img' and @aria-label='eye']")
    private WebElement previewButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'ant-btn-icon-only') and @title='Remove file']")
    private WebElement deleteButton;


    public LogoElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void uploadLogo(String imagePath) {
       logoImage.sendKeys(imagePath);
    }

    public void clickPreviewButton() {
        previewButton.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }
}
