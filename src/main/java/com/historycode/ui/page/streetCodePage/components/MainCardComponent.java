package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.modals.KeywordPersonsModal;
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
    private WebElement description;

    @FindBy(xpath = ".//div[@class='cardFooter']/button")
    private WebElement audioButton;

    @FindBy(xpath = ".//div[@class='leftSider']//ul")
    private WebElement paginationContainer;

    private KeywordPersonsModal keywordPersonsModal;
    private PaginationComponent paginationComponent;

    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keywordPersonsModal = new KeywordPersonsModal(driver, rootElement);
        this.paginationComponent = new PaginationComponent(driver, paginationContainer);
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

    public String getDescription() {
        return description.getText();
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

    public KeywordPersonsModal getKeywordModal() {
        return keywordPersonsModal;
    }
}
