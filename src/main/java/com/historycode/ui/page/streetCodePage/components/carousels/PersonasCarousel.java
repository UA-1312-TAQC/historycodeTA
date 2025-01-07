package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.RelatedPersonasCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PersonasCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='slider-item-container']")
    private List<WebElement> personCardNodes;

    private List<RelatedPersonasCardComponent> personCards;

    public PersonasCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.personCards = personCardNodes.stream()
                .map(node -> new RelatedPersonasCardComponent(driver, node))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasArrows() {
        return personCards.size() > 4;
    }

    public List<RelatedPersonasCardComponent> getCards() {
        return personCards;
    }
}
