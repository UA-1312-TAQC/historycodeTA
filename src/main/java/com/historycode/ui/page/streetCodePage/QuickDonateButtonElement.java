package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickDonateButtonElement extends BaseElement {
    @FindBy(xpath = "")
    private WebElement donateButton;

    public QuickDonateButtonElement(WebDriver driver) {
        super(driver);
    }

    public void click() {
        donateButton.click();
    }

    public boolean isVisible() {
        return donateButton.isDisplayed();
    }
}
