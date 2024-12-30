package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetCodeDetailsComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement mainTextContent;

    @FindBy(xpath = "")
    private WebElement expandButton;

    @FindBy(xpath = "")
    private WebElement videoContainer;

    @FindBy(xpath = "")
    private WebElement additionalLink;

    public StreetCodeDetailsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void toggleTextContent() {
        expandButton.click();
    }

    public void clickAdditionalLink() {
        additionalLink.click();
    }

    public void playVideo() {
        videoContainer.click();
    }
}

