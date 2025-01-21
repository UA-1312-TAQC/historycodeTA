package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StreetCodeTextBlockComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;

    @FindBy(xpath = ".//span[contains(@class,'readMore false')]")
    private WebElement readMoreButton;

    @FindBy(xpath = ".//span[contains(@class,'readMore readLess')]")
    private WebElement readLessButton;


    @FindBy(xpath = "//div[@class='additionalText']")
    private WebElement additionalText;

    @FindBy(xpath = "//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;

    @FindBy(xpath = ".//div[@class='text']//p")
    private List<WebElement> paragraphs;

    @FindBy(xpath = ".//iframe[(@id='widget2')]")
    private WebElement videoPlayer;

    @FindBy(xpath = ".//button[(@class = 'ytp-play-button ytp-button' and @title='Відтворити (k)')]")
    private WebElement videoPlayButton;

    @FindBy(xpath = ".//button[(@class = 'ytp-play-button ytp-button' and @title='Призупинити (k)')]")
    private WebElement videoPauseButton;

    public StreetCodeTextBlockComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isReadMoreButtonDisplayed() {
        waitUntilElementVisible(readMoreButton);
        return readMoreButton.isDisplayed();
    }

    public boolean isReadLessButtonDisplayed() {
        waitUntilElementVisible(readLessButton);
        return readLessButton.isDisplayed();
    }

    public void clickReadMoreButton() {
        scrollToElement(readMoreButton);
        readMoreButton.click();
//        if (isReadMoreButtonDisplayed()) {
//            clickDynamicElement(readMoreButton);
//        }
    }

    public void clickReadLessButton() {
        scrollToElement(readLessButton);
        readLessButton.click();
    }


    public boolean isMainTextContentVisible() {
        waitUntilElementVisible(mainTextContent);
        return mainTextContent.isDisplayed();
    }

    public int getParagraphCount() {
        return paragraphs.size();
    }

    public boolean checkExpanded() {
        int initialNumberOfParagraph = getParagraphCount();
        clickReadMoreButton();
        waitUntilElementVisible(paragraphs.getLast());
        int expandedNumberOfParagraph = getParagraphCount();

        return expandedNumberOfParagraph > initialNumberOfParagraph;
    }

    public boolean checkCollapsed(int initialCount) {
        clickReadLessButton();
        waitUntilElementVisible(paragraphs.getFirst());
        int collapsedCount = getParagraphCount();

        return collapsedCount == initialCount;
    }


    public boolean isVideoVisible() {
        waitUntilElementVisible(videoPlayer);
        scrollToElement(videoPlayer);
        return videoPlayer.isDisplayed();
    }

    public void clickPlayButton() {
        if (isVideoVisible()) {
            clickDynamicElement(videoPlayButton);
        }
    }

    public boolean isVideoPlaying() {
        return !videoPlayButton.isDisplayed() && videoPauseButton.isDisplayed();
    }

    public void clickPauseButton() {
        if (isVideoVisible()) {
            clickDynamicElement(videoPauseButton);
        }
    }

    public boolean isVideoPaused() {
        return !videoPauseButton.isDisplayed() && videoPlayButton.isDisplayed();
    }

    public boolean isAdditionalTextDisplayed() {
        return additionalText.isDisplayed();
    }

    public List<String> getLinksInNewsContent() {
        List<String> links = new ArrayList<>();

        for (WebElement link : linkInAdditionalText) {
            String url = link.getDomAttribute("href");
            if (url != null && !url.isEmpty()) {
                links.add(url);
            }
        }
        return links;
    }


}

