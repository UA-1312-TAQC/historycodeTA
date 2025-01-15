package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
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

    @FindBy(xpath = ".//span[@class='readMore false']")
    private WebElement readMoreButton;

    @FindBy(xpath = ".//span[@class='readMore readLess']")
    private WebElement readLessButton;


    @FindBy(xpath = "//div[@class='additionalText']")
    private WebElement additionalText;

    @FindBy(xpath = "//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;

    @FindBy(xpath = ".//div[@class='text']//p")
    private List<WebElement> paragraphs;

    @FindBy(xpath = ".//div[(@id='player')]")
    private WebElement videoPlayer;

    @FindBy(xpath = ".//div[(@class='ytp-bezel' and @aria-label='Відтворити')]")
    private WebElement playButton;

    @FindBy(xpath = ".//div[(@class='ytp-bezel' and @aria-label='Призупинити')]")
    private WebElement pauseButton;

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
        if (isReadMoreButtonDisplayed()) {
            readMoreButton.click();
        }
    }

    public void clickReadLessButton() {
        scrollToElement(readLessButton);
        if (isReadLessButtonDisplayed()) {
            readLessButton.click();
        }
    }

    public boolean isAdditionalTextDisplayed() {
        return additionalText.isDisplayed();
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
        waitUntilElementVisible(paragraphs.get(paragraphs.size() - 1));
        int expandedNumberOfParagraph = getParagraphCount();

        return expandedNumberOfParagraph > initialNumberOfParagraph;
    }

    public boolean checkCollapsed(int initialCount) {
        clickReadLessButton();
        waitUntilElementVisible(paragraphs.get(0));
        int collapsedCount = getParagraphCount();

        return collapsedCount == initialCount;
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

    public boolean isVideoVisible() {
        waitUntilElementVisible(videoPlayer);
        return videoPlayer.isDisplayed();
    }

    public void clickPlayButton() {
        scrollToElement(playButton);
        if (playButton.isDisplayed()) {
            playButton.click();
        }
    }

    public boolean isVideoPlaying() {
        waitUntilElementVisible(playButton);
        return !playButton.isDisplayed() && pauseButton.isDisplayed();
    }

    public void clickPauseButton() {
        scrollToElement(pauseButton);
        if (pauseButton.isDisplayed()) {
            pauseButton.click();
        }
    }

    public boolean isVideoPaused() {
        waitUntilElementVisible(pauseButton);
        return !pauseButton.isDisplayed() && playButton.isDisplayed();
    }
}

