package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.PersonsCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PersonsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='slider-item-container']")
    private List<WebElement> personCardNodes;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    private WebElement rightArrow;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    private WebElement leftArrow;

    private List<PersonsCardComponent> personCards;

    public PersonsCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.personCards = personCardNodes.stream()
                .map(node -> new PersonsCardComponent(driver, node))
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

    public List<PersonsCardComponent> getCards() {
        return personCards;
    }
}
