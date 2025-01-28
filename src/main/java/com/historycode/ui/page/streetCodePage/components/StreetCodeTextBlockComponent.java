package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.ArrayList;


public class StreetCodeTextBlockComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[@class='text']")
    private WebElement mainTextContent;
    @Getter
    @FindBy(xpath = ".//span[contains(@class,'readMore false')]")
    private WebElement readMoreButton;
    @Getter
    @FindBy(xpath = ".//span[contains(@class, 'readLess')]")
    private WebElement readLessButton;
    @Getter
    @FindBy(xpath = ".//div[@class='additionalText']")
    private WebElement additionalText;
    @Getter
    @FindBy(xpath = ".//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;
    @Getter
    @FindBy(xpath = ".//div[@class='text']//p")
    private List<WebElement> paragraphs;

    public StreetCodeTextBlockComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("The 'Трохи ще' button is displayed")
    public boolean isReadMoreButtonDisplayed() {
        waitUntilElementVisible(readMoreButton);
        return readMoreButton.isDisplayed();
    }

    @Step("The 'Дещо менше' button is displayed")
    public boolean isReadLessButtonDisplayed() {
        waitUntilElementVisible(readLessButton);
        return readLessButton.isDisplayed();
    }

    @Step("Click the 'Трохи ще' button")
    public StreetCodeTextBlockComponent clickReadMoreButton() {
        scrollToElementJS(readMoreButton);
        clickDynamicElement(readMoreButton);
        return this;
    }

    @Step("Scroll and click the 'Дещо менше' button")
    public StreetCodeTextBlockComponent clickReadLessButton() {
        scrollToElementJS(readLessButton);
        clickDynamicElement(readLessButton);
        return this;
    }

    @Step("Is the text visible")
    public boolean isMainTextContentVisible() {
        waitUntilElementVisible(mainTextContent);
        return mainTextContent.isDisplayed();
    }

    @Step("Get paragraph count")
    public int getParagraphCount() {
        return paragraphs.size();
    }

    @Step("Check if the text is expanded")
    public boolean checkExpanded(int initialCount) {
        waitUntilElementVisible(paragraphs.getLast());
        int expandedNumberOfParagraph = getParagraphCount();
        return expandedNumberOfParagraph > initialCount;
    }

    @Step("Check if the text is collapsed")
    public boolean checkCollapsed(int initialCount) {
        waitUntilElementVisible(paragraphs.getFirst());
        int collapsedCount = getParagraphCount();
        return collapsedCount == initialCount;
    }

    @Step("Check if the additional text is displayed")
    public boolean isAdditionalTextDisplayed() {
        return additionalText.isDisplayed();
    }

    @Step("Get links in the additional text")
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

    @Step("Check if the text fits on one screen")
    public boolean isTextFitsOneScreen() {
        Long viewportHeight;
        Long elementHeight;

        try {
            viewportHeight = (Long) threadJs.executeScript("return window.innerHeight;");
            elementHeight = (Long) threadJs.executeScript("return arguments[0].getBoundingClientRect().height;",
                    mainTextContent);
        } catch (Exception e) {
            logger.error("Error during script execution", e);
            return false;
        }

        return (viewportHeight != null)
                && (elementHeight != null)
                && (elementHeight <= viewportHeight);
    }

}
