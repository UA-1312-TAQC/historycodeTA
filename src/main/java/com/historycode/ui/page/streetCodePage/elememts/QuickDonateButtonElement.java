package com.historycode.ui.page.streetCodePage.elememts;

import com.historycode.ui.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickDonateButtonElement extends BaseElement {
    @FindBy(xpath = ".//div[@class='donateBtnContainer']")
    private WebElement donateButton;

    public QuickDonateButtonElement(WebDriver driver) {
        super(driver);
    }

    public void clickDonateButton() {
        donateButton.click();
    }

    public boolean isDonateButtonDisplayed() {
        return donateButton.isDisplayed();
    }
}
