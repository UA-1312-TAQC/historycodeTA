package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
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


public class StreetCodeTextBlockComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[@id='text']//div[@class='text']//p")
    private WebElement mainTextContent;
    @Getter
    @FindBy(xpath = ".//span[contains(@class,'readMore false')]")
    private WebElement readMoreButton;
    @Getter
    @FindBy(xpath = ".//span[contains(@class,'readMore readLess')]")
    private WebElement readLessButton;
    @Getter
    @FindBy(xpath = "//div[@class='additionalText']")
    private WebElement additionalText;
    @Getter
    @FindBy(xpath = "//div[contains(@class,'additionalText')]//a")
    private List<WebElement> linkInAdditionalText;
    @Getter
    @FindBy(xpath = ".//div[@class='text']//p")
    private List<WebElement> paragraphs;

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

//    @Step("Expand text content")
//    public StreetCodeTextBlockComponent toggleTextContent() {
//        clickDynamicElement(readMoreButton);
//        return this;
//    }
//
//    @Step("Get paragraph count")
//    public int getParagraphCount() {
//        return paragraphs.size();
//    }
//
//    @Step("Get expanded paragraph count")
//    public int getParagraphCount(int initialCount) {
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(EXPAND_BUTTON_LOCATOR), initialCount));
//
//        List<WebElement> paragraphs = rootElement.findElements(By.xpath(EXPAND_BUTTON_LOCATOR));
//        return paragraphs.size();
//    }
//
//    @Step("The 'Трохи ще' button is displayed")
//    public boolean isExpandButtonDisplayed() {
//        try {
//            return expandButton.isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    @Step("The 'Трохи ще' button is displayed")
//    public boolean isLessButtonDisplayed() {
//        try {
//            return rootElement.findElement(By.xpath(LESS_BUTTON_LOCATOR)).isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }

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
//
//    @Step("Click the 'Дещо менше' button")
//    public StreetCodeTextBlockComponent isLessButtonClick() {
//        clickDynamicElement(rootElement.findElement(By.xpath(LESS_BUTTON_LOCATOR)));
//        return this;
//    }
//
//    @Step("Get collapsed paragraph count")
//    public boolean getCollapsedParagraphCount(int expectedCount) {
//        try {
//            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(EXPAND_BUTTON_LOCATOR), expectedCount));
//            return true;
//        } catch (TimeoutException e) {
//            logger.error("Error getting collapsed paragraph count", e);
//            return false;
//        }
//    }
//
//    @Step("Check if the last paragraph is visible in the parent")
//    public boolean isLastParagraphVisibleInParent() {
//
//        if (mainTextContent.isEmpty()) {
//            return false;
//        }
//
//        WebElement lastParagraph = mainTextContent.getLast();
//
//        Boolean isVisible;
//        try {
//            isVisible = (Boolean) threadJs.executeScript(
//                    "var parent = arguments[0];" +
//                            "var elem = arguments[1];" +
//                            "var parentRect = parent.getBoundingClientRect();" +
//                            "var elemRect = elem.getBoundingClientRect();" +
//                            "return (elemRect.top >= parentRect.top && elemRect.bottom <= parentRect.bottom);",
//                    rootElement, lastParagraph);
//        } catch (Exception ex) {
//            logger.error("Error checking if the last paragraph is visible in the parent", ex);
//            return false;
//        }
//
//        return (isVisible != null) && isVisible;
//    }
//
//    public StreetCodeTextBlockComponent scrollToLessButton() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LESS_BUTTON_LOCATOR)));
//        scrollToElement(rootElement.findElement(By.xpath(LESS_BUTTON_LOCATOR)));
//        return this;
//    }

}
