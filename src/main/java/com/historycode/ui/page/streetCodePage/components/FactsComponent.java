package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.FactsCarousel;
import com.historycode.ui.page.streetCodePage.modals.FactCardModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FactsComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='wow-facts']//h1[@class='blockHeadingText']")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='interestingFactsContainer ']")
    private WebElement carouselRoot;

    @Getter
    private FactCardModal factCardModal;
    @Getter
    private FactsCarousel carousel;

    public FactsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new FactsCarousel(driver, carouselRoot);
        this.factCardModal = new FactCardModal(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    public List<FactCardComponent> getFactCards() {
        return carousel.getCards();
    }

    public void clickNextSlide() {
        carousel.clickNext();
    }

    public void clickPreviousSlide() {
        carousel.clickPrevious();
    }

    public void clickFactCard(int index) {
        if (index >= 0 && index < getFactCards().size()) {
            getFactCards().get(index).click();
        }
    }

    public PaginationComponent getPagination() {
        return carousel.getPagination();
    }

}
