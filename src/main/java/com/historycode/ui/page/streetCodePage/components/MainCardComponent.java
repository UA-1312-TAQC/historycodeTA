package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.modals.KeywordPersonasModal;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MainCardComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = "//div[@class='ant-modal css-k7429z tagsModal']")
    private WebElement modalContainer;
    @FindBy(xpath = ".//img[@class='streetcodeImgGrey']")
    private List<WebElement> photo;

    @FindBy(xpath = ".//div[@class='streetcodeIndex']")
    private WebElement catalogNumber;

    @Getter
    @FindBy(xpath = ".//h2[@class='streetcodeTitle']")
    private WebElement name;

    @FindBy(xpath = ".//div[@class='streetcodeDate']")
    private WebElement lifeYears;

    @FindBy(xpath = ".//div[@class='tagContainer']//button")
    private List<WebElement> tagsNode;

    @FindBy(xpath = ".//p[@class='teaserBlock']")
    private WebElement teaserBlockNode;

    @FindBy(xpath = ".//button[contains(@class, 'audioBtn')]")
    private WebElement audioButton;

    @FindBy(xpath = ".//div[@class='leftSider']//ul[@class='slick-dots']")
    private WebElement paginationNode;

    private KeywordPersonasModal tagPersonsModal;
    private PaginationComponent pagination;

    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> getPersonPhotos() {
        return photo.stream()
                .map(element -> element.getAttribute("src"))
                .collect(Collectors.toList());
    }

    public String getCatalogNumber() {
        return catalogNumber.getText();
    }

    public String getPersonName() {
        return name.getText();
    }

    public String getLifeYears() {
        return lifeYears.getText();
    }

    @Step("Get the text of the 'Teaser' element")
    public String getTeaserText() {
        return teaserBlockNode.getText();
    }

    @Step("Get the number of paragraphs in the 'Teaser' element")
    public int getTeaserParagraphCount() {
        String[] paragraphs = teaserBlockNode.getText().split("\n");
        return paragraphs.length;
    }

    @Step("Get the number of characters in the 'Teaser' element")
    public int getTeaserCharacterCount() {
        return getTeaserText().replace("\n", "").length();
    }

    @Step("Check if the 'Teaser' text has truncation or overflow")
    public boolean isTeaserTextOverflowing() {
        return isContentTruncatedOrOverflow(teaserBlockNode);
    }

    public void clickAudioButton() {
        audioButton.click();
    }

    public List<String> getTags() {
        return tagsNode.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void goToPhoto(int index) {
        pagination.selectDot(index);
    }

    public int getCurrentPhotoIndex() {
        return pagination.getActiveIndex();
    }

    public int getTotalPhotos() {
        return pagination.getTotalDots();
    }

    public KeywordPersonasModal getKeywordModal() {
        return tagPersonsModal;
    }

    public void toggleAudio() {
        if (isAudioAvailable()) {
            audioButton.click();
        } else {
            throw new IllegalStateException("Cannot toggle audio - audio is not available");
        }
    }

    public String getAudioButtonText() {
        return audioButton.getText();
    }

    public boolean isAudioButtonEnabled() {
        return audioButton.isEnabled();
    }

    public boolean isAudioAvailable() {
        try {
            return !Boolean.valueOf(audioButton.getAttribute("disabled"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAudioComingSoonMessageDisplayed() {
        try {
            return Boolean.valueOf(audioButton.getAttribute("disabled")) &&
                    audioButton.getText().equals("Аудіо на підході");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Get tag element")
    public WebElement getTagElement(String keyword) {
        return tagsNode.stream()
                .filter(k -> k.getText().equals(keyword))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Step("Get tag border color")
    public String getTagBorderColor(String tag) {
        waitUntilElementVisible(getTagElement(tag));
        return getTagElement(tag).getCssValue("border-color");
    }

    @Step("Get tag text color")
    public String getTagTextColor(String tag) {
        waitUntilElementVisible(getTagElement(tag));
        return getTagElement(tag).getCssValue("color");
    }

    @Step("Hover over tag")
    public void hoverOverTag(String tag) {
        waitUntilElementVisible(getTagElement(tag));
        actions.moveToElement(getTagElement(tag)).perform();
        sleep(500);
    }

    @Step("Check if modal is visible")
    public boolean isModalVisible() {
        try {
            WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='ant-modal css-k7429z tagsModal']")
            ));
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public KeywordPersonasModal clickKeyword(String tag) {
        WebElement tagElement = tagsNode.stream()
                .filter(k -> k.getText().equals(tag))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        tagElement.click();

        WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='ant-modal css-k7429z tagsModal']")
        ));

        waitUntilElementVisible(modal);
        tagPersonsModal = new KeywordPersonasModal(driver, modal);
        waitUntilElementVisible(tagPersonsModal.getPersonsCardsContainer());
        return tagPersonsModal;
    }

    public boolean isNameVisible() {
        return name.isDisplayed();
    }
}
