package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class RelatedFiguresComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='slider-item-container']")
    private List<WebElement> figureCardNodes;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    private WebElement rightArrow;
    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    private WebElement leftArrow;

    private List<PersonsCardComponent> figureCards;
    private CarouselsComponent carousel;

    public RelatedFiguresComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.figureCards = figureCardNodes.stream()
                .map(node -> new PersonsCardComponent(driver, node))
                .collect(Collectors.toList());

        this.carousel = new CarouselsComponent(driver, rootElement, leftArrow, rightArrow);
    }
}
