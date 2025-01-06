package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.modals.SourcesModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SourcesComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='sourcesSliderItem']")
    private List<WebElement> categoryCardsNode;

    private List<SourcesCardComponent> categoryCards;
    private SourcesModal sourceModal;

    public SourcesComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.categoryCards = categoryCardsNode.stream()
                .map(node -> new SourcesCardComponent(driver, node))
                .collect(Collectors.toList());
        this.sourceModal = new SourcesModal(driver, rootElement);
    }

    public SourcesCardComponent getCardByTitle(String title) {
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
                .map(SourcesCardComponent::getTitle)
                .collect(Collectors.toList());
    }

    public SourcesModal getModal() {
        return sourceModal;
    }
}
