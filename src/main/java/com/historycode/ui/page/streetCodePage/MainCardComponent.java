package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MainCardComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement photo;

    @FindBy(xpath = "")
    private WebElement catalogNumber;

    @FindBy(xpath = "")
    private WebElement name;

    @FindBy(xpath = "")
    private WebElement lifeYears;

    @FindBy(xpath = "")
    private List<WebElement> keywords;

    @FindBy(xpath = "")
    private WebElement description;

    @FindBy(xpath = "")
    private WebElement audioButton;

    private KeywordPersonsModal keywordPersonsModal;

    public MainCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.keywordPersonsModal = new KeywordPersonsModal(driver, rootElement);
    }

    public String getPersonPhoto() {
        return photo.getAttribute("src");
    }

    public String getCatalogNumber() {
        return catalogNumber.getText();
    }

    public String getName() {
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

    public void clickKeyword(String keyword) {
        keywords.stream()
                .filter(element -> element.getText().equals(keyword))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public List<String> getAllKeywords() {
        return keywords.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
