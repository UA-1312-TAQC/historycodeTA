package com.historycode.ui.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScrollTopButtonElement extends BaseElement{
    @FindBy(xpath = "")
    private WebElement scrollButton;

    public ScrollTopButtonElement(WebDriver driver) {
        super(driver);
    }

    public void clickScrollTop() {
        scrollButton.click();
    }

    public boolean isButtonSticky() {
        return scrollButton.getCssValue("position").equals("sticky");
    }
}
