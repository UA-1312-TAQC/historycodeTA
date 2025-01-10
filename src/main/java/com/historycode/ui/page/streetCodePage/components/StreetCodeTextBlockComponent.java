package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class StreetCodeTextBlockComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;

    @FindBy(xpath = "./span[@class='readMore false']")
    private WebElement readMoreButton;


    @Getter
    @FindBy(xpath = "./span[@class='readMore readLess']")
    private WebElement readLessButton;

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

    public void clickReadMoreButton() {
        readMoreButton.click();
    }
    public void clickReadLessButton() {
        readLessButton.click();
    }

}

