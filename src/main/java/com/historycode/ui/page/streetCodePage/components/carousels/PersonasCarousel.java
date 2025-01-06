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

    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    private WebElement rightArrow;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    private WebElement leftArrow;

    private List<RelatedPersonasCardComponent> personCards;

    public PersonasCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.personCards = personCardNodes.stream()
                .map(node -> new RelatedPersonasCardComponent(driver, node))
                .collect(Collectors.toList());
    }

    public void clickNext() {
        if (hasArrows()) {
            rightArrow.click();
        }
    }

    public void clickPrevious() {
        if (hasArrows()) {
            leftArrow.click();
        }
    }

    @Override
    public boolean hasArrows() {
        return personCards.size() > 4;
    }

    public List<RelatedPersonasCardComponent> getCards() {
        return personCards;
    }
}
