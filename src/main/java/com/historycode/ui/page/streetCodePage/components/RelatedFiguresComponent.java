package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.PersonsCarousel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RelatedFiguresComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='relatedFiguresContainer']//h1")
    private WebElement sectionTitle;

    @FindBy(xpath = ".//div[@class='relatedFiguresSliderContainer']")
    private WebElement carouselRoot;

    private PersonsCarousel carousel;

    public RelatedFiguresComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new PersonsCarousel(driver, carouselRoot);
    }

    public String getTitle() {
        return sectionTitle.getText();
    }

    public List<PersonsCardComponent> getPersonCards() {
        return carousel.getCards();
    }

    public void clickNextSlide() {
        carousel.clickNext();
    }

    public void clickPreviousSlide() {
        carousel.clickPrevious();
    }

    public void clickPersonCard(int index) {
        if (index >= 0 && index < getPersonCards().size()) {
            getPersonCards().get(index).click();
        }
    }

    public boolean hasNavigationArrows() {
        return carousel.hasArrows();
    }
}
