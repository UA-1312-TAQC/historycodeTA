package com.historycode.ui.component.streetcodeEditor;

import com.historycode.ui.component.BaseModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class WowFactsModal extends BaseModal {

    @FindBy(xpath=".//input[@id='title']")
    private WebElement title;

    @FindBy(xpath=".//span[input[@id='title']]/span/span[@class='ant-input-show-count-suffix']")
    private WebElement counterTitle;

    @FindBy(xpath=".//div[div/label[@title='Заголовок: ']]/div/div/div/div[@class='ant-form-item-explain-error']")
    private WebElement titleValidation;

    @FindBy(xpath=".//textarea[@id='factContent']")
    private WebElement factContent;

    @FindBy(xpath = ".//span[textarea[@id='factContent']]/span/span[@class='ant-input-data-count']")
    private WebElement counterContent;

    @FindBy(xpath=".//div[div/label[@title=''Основний текст: ']]/div/div/div/div[@class='ant-form-item-explain-error']")
    private WebElement contentValidation;

    @FindBy(xpath=".//input[@data-testid='fileuploader']")
    private WebElement fileuploader;

    @FindBy(xpath=".//div[@class='ant-upload-list-item ant-upload-list-item-done']")
    private WebElement uploadedImage;

    @FindBy(xpath=".//input[@id='imageDescription']")
    private WebElement imageDescription;

    @FindBy(xpath=".//span[input[@id='imageDescription']]/span/span[@class='ant-input-show-count-suffix']")
    private WebElement counterImageDesc;

    public WowFactsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isSymbolsLeftInTitle(String expectedCounterValue) {
        wait.until(ExpectedConditions.textToBePresentInElement(counterTitle, expectedCounterValue));
        String actualCounterValue = counterTitle.getText();
        return actualCounterValue.equals(expectedCounterValue);
    }

    public boolean isSymbolsLeftInContent(String expectedCounterValue) {
        wait.until(ExpectedConditions.textToBePresentInElement(counterContent, expectedCounterValue));
        String actualCounterValue = counterContent.getText();
        return actualCounterValue.equals(expectedCounterValue);
    }

    public boolean isSymbolsLeftImgDesc(String expectedCounterValue) {
        wait.until(ExpectedConditions.textToBePresentInElement(counterImageDesc, expectedCounterValue));
        String actualCounterValue = counterImageDesc.getText();
        return actualCounterValue.equals(expectedCounterValue);
    }

}
