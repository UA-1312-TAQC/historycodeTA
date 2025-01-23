package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
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

    @FindBy(xpath = ".//iframe[contains(@src,'www.youtube.com')]")
    private WebElement videoPlayer;

    @FindBy(xpath = "//button[contains(@class, 'ytp-large-play-button')]")
    private WebElement videoPlayButton;

    @FindBy(xpath = "//button[(@class = 'ytp-play-button ytp-button' and @title='Призупинити (k)')]")
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
        clickDynamicElement(readMoreButton);
    }

    public void clickReadLessButton() {
        scrollToElement(readLessButton);
        clickDynamicElement(readLessButton);
    }


    public boolean isMainTextContentVisible() {
        waitUntilElementVisible(mainTextContent);
        return mainTextContent.isDisplayed();
    }

    public int getParagraphCount() {
        return paragraphs.size();
    }

    public boolean checkExpanded(int initialCount) {
        waitUntilElementVisible(paragraphs.getLast());
        int expandedNumberOfParagraph = getParagraphCount();
        return expandedNumberOfParagraph > initialCount;
    }

    public boolean checkCollapsed(int initialCount) {
        waitUntilElementVisible(paragraphs.getFirst());
        int collapsedCount = getParagraphCount();
        return collapsedCount == initialCount;
    }


    public boolean isVideoVisible() {
//        waitUntilElementVisible(videoPlayer);
//        scrollToElement(videoPlayer);
        return videoPlayer.isDisplayed();
    }

    public boolean isPlayButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[contains(@class, 'ytp-large-play-button')]")).isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }

    public boolean isPauseButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = videoPauseButton.isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }

    public void clickPlayButton() {
        waitUntilElementClickable(videoPlayButton);
        videoPlayButton.click();
    }

    public void clickPauseButton() {
        waitUntilElementClickable(videoPauseButton);
        clickDynamicElement(videoPauseButton);
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

