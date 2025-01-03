package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetCodeDetailsComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;

    @FindBy(xpath = ".//span[@class='readMore false']")
    private WebElement expandButton;

    public StreetCodeDetailsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void toggleTextContent() {
        expandButton.click();
    }
}

