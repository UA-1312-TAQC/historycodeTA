package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class AdditionalInfoComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='sourcesSliderItem']")
    private List<WebElement> categoryCards;

    private List<InfoCardComponent> infoCards;
    private AdditionalInfoModal additionalInfoModal;

    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.infoCards = categoryCards.stream()
                .map(node -> new InfoCardComponent(driver, node))
                .collect(Collectors.toList());
        this.additionalInfoModal = new AdditionalInfoModal(driver, rootElement);
    }

    public void selectCategory(String categoryName) {
        infoCards.stream()
                .filter(card -> card.getTitle().equals(categoryName))
                .findFirst()
                .ifPresent(InfoCardComponent::click);
    }

    public List<String> getAvailableCategories() {
        return infoCards.stream()
                .map(InfoCardComponent::getTitle)
                .collect(Collectors.toList());
    }
}
