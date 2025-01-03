package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AdditionalInfoComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='sourcesSliderItem']")
    private List<WebElement> categoryCardsNode;

    private List<InfoCardComponent> categoryCards;
    private AdditionalInfoModal additionalInfoModal;

    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.categoryCards = categoryCardsNode.stream()
                .map(node -> new InfoCardComponent(driver, node))
                .collect(Collectors.toList());
        this.additionalInfoModal = new AdditionalInfoModal(driver, rootElement);
    }

    public InfoCardComponent getCardByTitle(String title) {
        return categoryCards.stream()
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Card with title '" + title + "' not found"));
    }

    public boolean isCardPresent(String title) {
        return categoryCards.stream()
                .anyMatch(card -> card.getTitle().equals(title));
    }

    public List<String> getAllCardTitles() {
        return categoryCards.stream()
                .map(InfoCardComponent::getTitle)
                .collect(Collectors.toList());
    }

    public AdditionalInfoModal getModal() {
        return additionalInfoModal;
    }
}
