package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StreetCodeTextBlockComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;

    @FindBy(xpath = "./span[@class='readMore false']")
    private WebElement readMoreButton;

    @FindBy(xpath = "./span[@class='readMore readLess']")
    private WebElement readLessButton;


    @FindBy(xpath = "//div[@class='additionalText']")
    private WebElement additionalText;

    @FindBy(xpath = "//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;


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
        if (readMoreButton != null && isReadMoreButtonDisplayed()) {
            readMoreButton.click();
        }
    }

    public void clickReadLessButton() {
        if (readLessButton != null && isReadLessButtonDisplayed()) {
            readLessButton.click();
        }
    }

    public boolean isReadMoreButtonDisplayed() {
        return readMoreButton.isDisplayed();
    }

    public boolean isReadLessButtonDisplayed() {
        return readLessButton.isDisplayed();
    }

    public boolean isAdditionalTextDisplayed() {
        return additionalText.isDisplayed();
    }

    public List<String> getLinksInNewsContent() {
        List<String> links = new ArrayList<>();
        try {
            for (WebElement link : linkInAdditionalText) {
                String url = link.getDomAttribute("href");
                if (url != null && !url.isEmpty()) {
                    links.add(url);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("No links found");
        }
        return links;
    }
}

