package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class StreetCodeTextBlockComponent extends BaseComponent {
    final String EXPAND_BUTTON_LOCATOR = ".//div[@class='text']//p";
    final By LESS_BUTTON_LOCATOR = By.xpath("//span[@class = 'readMore readLess']");

    @FindBy(xpath = EXPAND_BUTTON_LOCATOR)
    private List<WebElement> mainTextContent;

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

    @Step("Expand text content")
    public StreetCodeTextBlockComponent toggleTextContent() {
        clickDynamicElement(expandButton);
        return this;
    }

    @Step("Get paragraph count")
    public int getParagraphCount() {
        return mainTextContent.size();
    }

    @Step("Get expanded paragraph count")
    public int getParagraphCount(int initialCount) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(EXPAND_BUTTON_LOCATOR), initialCount));

        List<WebElement> paragraphs = rootElement.findElements(By.xpath(EXPAND_BUTTON_LOCATOR));
        return paragraphs.size();
    }

    @Step("The 'Трохи ще' button is displayed")
    public boolean isExpandButtonDisplayed() {
        try {
            return expandButton.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Error checking visibility of the 'Expand' button", e);
            return false;
        }
    }

    @Step("The 'Трохи ще' button is displayed")
    public boolean isLessButtonDisplayed() {
        try {
            return rootElement.findElement(LESS_BUTTON_LOCATOR).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Error checking visibility of 'Less' button", e);
            return false;
        }
    }

    @Step("Click the 'Дещо менше' button")
    public StreetCodeTextBlockComponent isLessButtonClick() {
        clickDynamicElement(rootElement.findElement(LESS_BUTTON_LOCATOR));
        return this;
    }

    @Step("Get collapsed paragraph count")
    public boolean getCollapsedParagraphCount(int expectedCount) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(EXPAND_BUTTON_LOCATOR), expectedCount));
            return true;
        } catch (TimeoutException e) {
            logger.error("Error getting collapsed paragraph count", e);
            return false;
        }
    }

    @Step("Check if the last paragraph is visible in the parent")
    public boolean isLastParagraphVisibleInParent() {

        if (mainTextContent.isEmpty()) {
            return false;
        }

        WebElement lastParagraph = mainTextContent.getLast();

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
        scrollToElement(rootElement.findElement(LESS_BUTTON_LOCATOR));
        return this;
    }

}
