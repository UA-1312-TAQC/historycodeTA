package com.historycode.ui.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScrollTopButtonElement extends BaseElement {

    public ScrollTopButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public void clickScrollTop() {
        rootElement.click();
    }

    public boolean isButtonDisplayed() {
        return rootElement.isDisplayed();
    }
}
