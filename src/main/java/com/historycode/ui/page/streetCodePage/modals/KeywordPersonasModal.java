package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import com.historycode.ui.page.streetCodePage.components.RelatedPersonasCardComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class KeywordPersonasModal extends BaseModal {
    @FindBy(xpath = ".//div[@class='tagModalContainer']//button")
    private List<WebElement> keywords;

    @FindBy(xpath = "//div[@class='relatedFiguresByTagsContentContainer']//a[1]")
    private List<WebElement> personCards;

    @Getter
    private final List<RelatedPersonasCardComponent> persons;

    public KeywordPersonasModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.persons = personCards.stream()
                .map(card -> new RelatedPersonasCardComponent(driver, card))
                .collect(Collectors.toList());
    }

    public void selectKeyword(String keyword) {
        keywords.stream()
                .filter(k -> k.getText().equals(keyword))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Keyword not found: " + keyword))
                .click();
    }

    public List<String> getAvailableKeywords() {
        return keywords.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
