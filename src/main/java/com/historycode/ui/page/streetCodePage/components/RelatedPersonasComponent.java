package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.PersonasCarousel;
import com.historycode.ui.page.streetCodePage.modals.RelatedPersonasModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RelatedPersonasComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='relatedFiguresContainer']//h1")
    private WebElement sectionTitle;

    @FindBy(xpath = ".//div[@class='relatedFiguresSliderContainer']")
    private WebElement carouselRoot;

    @FindBy(xpath = ".//div[@class='relatedFiguresContainer']//div[@class='moreInfo']/p")
    private WebElement viewAllButton;

    @Getter
    private final RelatedPersonasModal modal;
    private final PersonasCarousel carousel;

    public RelatedPersonasComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new PersonasCarousel(driver, carouselRoot);
        this.modal = new RelatedPersonasModal(driver, rootElement);
    }

    public String getTitle() {
        return sectionTitle.getText();
    }

    public List<RelatedPersonasCardComponent> getPersonCards() {
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

    public boolean isViewAllButtonVisible() {
        return viewAllButton.isDisplayed();
    }

    public void clickViewAllButton() {
        viewAllButton.click();
    }

}
