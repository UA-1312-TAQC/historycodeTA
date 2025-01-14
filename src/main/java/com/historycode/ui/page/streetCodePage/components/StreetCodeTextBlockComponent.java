package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class StreetCodeTextBlockComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;

    @FindBy(xpath = ".//span[@class='readMore false']")
    private WebElement expandButton;

//    @FindBy(xpath = ".//div[@class='video-container']")
//    private WebElement videoContainer;

    private boolean hasVideo;

    public StreetCodeTextBlockComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
//        checkVideoPresence();
    }

//    private void checkVideoPresence() {
//        try {
//            hasVideo = videoContainer.isDisplayed();
//        } catch (NoSuchElementException e) {
//            hasVideo = false;
//        }
//    }

    public boolean hasVideo() {
        return hasVideo;
    }

    public void toggleTextContent() {
        expandButton.click();
    }
}

