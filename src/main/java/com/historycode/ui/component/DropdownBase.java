package com.historycode.ui.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropdownBase extends BaseComponent {

    public DropdownBase(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectOptionFromDropdown(String dropdownTriggerXpath, String optionXpath) {
        // Знайти елемент, який відкриває dropdown
        WebElement dropdownTrigger = driver.findElement(By.xpath(dropdownTriggerXpath));
        dropdownTrigger.click();

        // додати очікування
        // додати вибір опції

        WebElement option = driver.findElement(By.xpath(optionXpath));
        option.click();
        }
    }
