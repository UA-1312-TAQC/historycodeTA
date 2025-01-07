package com.historycode.ui.elements.adminPanel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class CheckboxElement {

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]/label")
    private WebElement checboxLabel;

    @Getter
    @FindBy(xpath = "//input[@type = 'checkbox']")
    private WebElement checkbox;

    public CheckboxElement(WebDriver driver, WebElement rootElement) {
        PageFactory.initElements(driver, this);
    }

    public void check() {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheck() {
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isChecked() {
        return checkbox.isSelected();
    }

}
