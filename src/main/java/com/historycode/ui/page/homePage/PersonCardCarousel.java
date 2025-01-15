package com.historycode.ui.page.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PersonCardCarousel extends CarouselComponent<PersonCardComponent> {

    @FindBy(xpath = ".//div[@class = 'slick-slide']")
    private List<WebElement> itemElements;

    public PersonCardCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public void clickLeftArrow() {
    }

    @Override
    public void clickRightArrow() {

    }

    public void swipeLeft() {
        new Actions(driver)
                .clickAndHold(rootElement)
                .moveByOffset(-200, 0)
                .release()
                .perform();
    }

    public void swipeRight() {
        new Actions(driver)
                .clickAndHold(rootElement)
                .moveByOffset(200, 0)
                .release()
                .perform();
    }

    @Override
    public List<PersonCardComponent> getCarouselItems() {
        return itemElements.stream()
                .map(el -> new PersonCardComponent(driver, el))
                .collect(Collectors.toList());
    }
}
