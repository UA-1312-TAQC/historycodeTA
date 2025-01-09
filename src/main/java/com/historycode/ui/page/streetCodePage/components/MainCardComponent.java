package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.modals.KeywordPersonasModal;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MainCardComponent extends BaseComponent {
    @FindBy(xpath = ".//img[@class='streetcodeImgGrey']")
    private List<WebElement> photo;

    @FindBy(xpath = ".//div[@class='streetcodeIndex']")
    private WebElement catalogNumber;

    @FindBy(xpath = ".//h2[@class='streetcodeTitle']")
    private WebElement name;

    @FindBy(xpath = ".//div[@class='streetcodeDate']")
    private WebElement lifeYears;

    @FindBy(xpath = ".//div[@class='tagContainer']//button")
    private List<WebElement> keywords;

    @FindBy(xpath = ".//p[@class='teaserBlock']")
    private List<WebElement> description;

    @FindBy(xpath = ".//div[@class='cardFooter']/button")
    private WebElement audioButton;

    @FindBy(xpath = ".//div[@class='leftSider']//ul[@class='slick-dots']")
    private WebElement paginationNode;

    private KeywordPersonasModal keywordPersonsModal;
    private PaginationComponent pagination;

    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keywordPersonsModal = new KeywordPersonasModal(driver, rootElement);
        this.pagination = new PaginationComponent(driver, paginationNode);
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
    public List<String> getDescriptions() {
        return description.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Check if the 'Teaser' text has truncation or overflow")
    public boolean isTeaserTextOverflowing() {
        //TODO: How to get paragraphs from the teaser
        boolean isFirstParagraphOverflowing = isContentOverflowing(description.getFirst());

        if (description.size() > 1) {
            boolean isSecondParagraphOverflowing = isContentOverflowing(description.get(1));
            return isFirstParagraphOverflowing || isSecondParagraphOverflowing;
        }

        return isFirstParagraphOverflowing;
    }

    public void clickAudioButton() {
        audioButton.click();
    }

    public List<String> getKeywords() {
        return keywords.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickKeyword(String keyword) {
        keywords.stream()
                .filter(k -> k.getText().equals(keyword))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Keyword not found: " + keyword))
                .click();
    }

    public void toggleAudio() {
        audioButton.click();
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
        return keywordPersonsModal;
    }
}
