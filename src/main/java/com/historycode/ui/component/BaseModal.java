package com.historycode.ui.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseModal extends BaseComponent{
    public BaseModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getCloseModalButton() {
        return rootElement.findElement(By.xpath(".//button[@class='ant-modal-close']"));
    }

    public WebElement getSaveModalButton() {
        return rootElement.findElement(By.xpath(".//button/span[text()='Зберегти']"));
    }

}
