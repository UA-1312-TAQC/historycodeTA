package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordPersonsModal extends BaseModal {
    @FindBy(xpath = "")
    private List<WebElement> keywords;

    @FindBy(xpath = "")
    private List<WebElement> personCards;

    private List<PersonsCardComponent> persons;

    public KeywordPersonsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.persons = personCards.stream()
                .map(card -> new PersonsCardComponent(driver, card))
                .collect(Collectors.toList());
    }

    public void selectKeyword(String keyword) {
        keywords.stream()
                .filter(k -> k.getText().equals(keyword))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public List<String> getAllKeywords() {
        return keywords.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<PersonsCardComponent> getVisiblePersons() {
        return persons;
    }
}
