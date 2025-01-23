package com.historycode.ui.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScrollTopButtonElement extends BaseElement {
    @FindBy(xpath = ".//div[@class='scrollToTopBtnContainer']")
    private WebElement scrollButton;

    public ScrollTopButtonElement(WebDriver driver) {
        super(driver);
    }

    public void clickScrollTop() {
        scrollButton.click();
    }

    public void isButtonDisplayed() {
        scrollButton.isDisplayed();
    }
}
