package com.historycode.ui.component.streetcodeEditor;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WowFactsModal extends BaseModal {
    public WowFactsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getTitle() {
        return rootElement.findElement(By.xpath(".//input[@id='title']"));
    }

    public WebElement getTitleValidation() {
        return rootElement.findElement(By.xpath(".//div[div/label[@title='Заголовок: ']]/div/div/div/div[@class='ant-form-item-explain-error']"));
    }

    public WebElement getFactContent() {
        return rootElement.findElement(By.xpath(".//textarea[@id='factContent']"));
    }

    public WebElement getContentValidation() {
        return rootElement.findElement(By.xpath(".//div[div/label[@title=''Основний текст: ']]/div/div/div/div[@class='ant-form-item-explain-error']"));
    }

    public WebElement getFileuploader() {
        return rootElement.findElement(By.xpath(".//input[@data-testid='fileuploader']"));
    }

    public WebElement getUploadedImage() {
        return rootElement.findElement(By.xpath(".//div[@class='ant-upload-list-item ant-upload-list-item-done']"));
    }

    public WebElement getImageDescription() {
        return rootElement.findElement(By.xpath(".//input[@id='imageDescription']"));
    }

}
