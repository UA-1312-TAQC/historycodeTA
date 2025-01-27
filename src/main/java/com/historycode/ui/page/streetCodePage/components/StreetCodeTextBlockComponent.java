package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;


public class StreetCodeTextBlockComponent extends BaseComponent {
    final String PARAGRAPHS_LOCATOR = ".//div[@class='text']//p";
    final String LESS_BUTTON_LOCATOR = "//span[contains(@class, 'readLess')]";

    @Getter
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;
    @Getter
    @FindBy(xpath = ".//span[contains(@class,'readMore false')]")
    private WebElement readMoreButton;
    @Getter
    @FindBy(xpath = LESS_BUTTON_LOCATOR)
    private WebElement readLessButton;
    @Getter
    @FindBy(xpath = "//div[@class='additionalText']")
    private WebElement additionalText;
    @Getter
    @FindBy(xpath = "//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;
    @Getter
    @FindBy(xpath = PARAGRAPHS_LOCATOR)
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

    @Step("The 'Трохи ще' button is displayed")
    public boolean isMoreButtonDisplayed() {
        try {
            return readMoreButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("The 'Дещо менше' button is displayed")
    public boolean isLessButtonDisplayed() {
        try {
            return rootElement.findElement(By.xpath(LESS_BUTTON_LOCATOR)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click the 'Трохи ще' button")
    public StreetCodeTextBlockComponent clickReadMoreButton() {
        scrollToElement(readMoreButton);
        clickDynamicElement(readMoreButton);
        return this;
    }

    @Step("Scroll and click the 'Дещо менше' button")
    public StreetCodeTextBlockComponent clickReadLessButton() {
        scrollToElement(readLessButton);
        clickDynamicElement(readLessButton);
        return this;
    }

    @Step("Click the 'Трохи ще' button")
    public StreetCodeTextBlockComponent clickMoreButton() {
        clickDynamicElement(readMoreButton);
        return this;
    }

    @Step("Click the 'Дещо менше' button")
    public StreetCodeTextBlockComponent clickLessButton() {
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

    @Step("Get expanded paragraph count")
    public int getExpandedParagraphCount(int initialCount) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(PARAGRAPHS_LOCATOR), initialCount));

        List<WebElement> paragraphs = rootElement.findElements(By.xpath(PARAGRAPHS_LOCATOR));
        return paragraphs.size();
    }

    @Step("Get collapsed paragraph count")
    public boolean getCollapsedParagraphCount(int expectedCount) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(PARAGRAPHS_LOCATOR), expectedCount));
            return true;
        } catch (TimeoutException e) {
            logger.error("Error getting collapsed paragraph count", e);
            return false;
        }
    }

    @Step("Check if the last paragraph is visible in the parent")
    public boolean isLastParagraphVisibleInParent() {

        if (paragraphs.isEmpty()) {
            return false;
        }

        WebElement lastParagraph = paragraphs.getLast();

        Boolean isVisible;
        try {
            isVisible = (Boolean) threadJs.executeScript(
                    "var parent = arguments[0];" +
                            "var elem = arguments[1];" +
                            "var parentRect = parent.getBoundingClientRect();" +
                            "var elemRect = elem.getBoundingClientRect();" +
                            "return (elemRect.top >= parentRect.top && elemRect.bottom <= parentRect.bottom);",
                    rootElement, lastParagraph);
        } catch (Exception ex) {
            logger.error("Error checking if the last paragraph is visible in the parent", ex);
            return false;
        }

        return (isVisible != null) && isVisible;
    }

    public StreetCodeTextBlockComponent scrollToLessButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LESS_BUTTON_LOCATOR)));
        scrollToElementAndWait(rootElement.findElement(By.xpath(LESS_BUTTON_LOCATOR)));
        return this;
    }

}
