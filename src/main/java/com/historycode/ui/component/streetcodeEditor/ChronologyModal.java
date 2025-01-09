package com.historycode.ui.component.streetcodeEditor;

import com.historycode.ui.component.BaseModal;
import com.historycode.ui.component.DropdownBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChronologyModal extends BaseModal {
    public ChronologyModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getTitle() {
        return rootElement.findElement(By.xpath(".//input[@id='title']"));
    }

    public WebElement getTitleValidation() {
        return rootElement.findElement(By.xpath(".//div[div/label[@title='Назва: ']]/div/div/div/div[@class='ant-form-item-explain-error']"));
    }

    public DropdownBase getFormatdateDropdow() {
        WebElement firstDropdownRoot = driver.findElement(By.xpath("(.//div[@class='ant-select-selector'])[1]"));
        return new DropdownBase(driver, firstDropdownRoot);
    }

    public WebElement getDatepicker() {
        return rootElement.findElement(By.xpath(".//input[@id='date']"));
    }

    public DropdownBase getContextDropdow() {
        WebElement contextDropdownRoot = driver.findElement(By.xpath("(.//div[@class='ant-select-selector'])[2]"));
        return new DropdownBase(driver, contextDropdownRoot);
    }

    public WebElement getDescription() {
        return rootElement.findElement(By.xpath(".//textarea[@id='description']"));
    }

    public WebElement getDescriptionValidation() {
        return rootElement.findElement(By.xpath(".//div[div/label[@title='Опис: ']]/div/div/div/div[@class='ant-form-item-explain-error']"));
    }
}
