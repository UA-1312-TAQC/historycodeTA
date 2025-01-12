package com.historycode.ui.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseModal extends BaseComponent{
    public BaseModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getCloseButton() {
        return rootElement.findElement(By.xpath(".//button[@class='ant-modal-close']"));
    }

    public WebElement getSaveButton() {
        return rootElement.findElement(By.xpath(".//button[@class='ant-btn css-k7429z ant-btn-default streetcode-custom-button']"));
    }

    public boolean checkTitle(String expectedText) {
        WebElement title = rootElement.findElement(By.xpath(".//div[@class='center']/h2"));
        String actualText = title.getText().trim().replaceAll("\\s+", " ");;
        return actualText.equals(expectedText);
    }

}
