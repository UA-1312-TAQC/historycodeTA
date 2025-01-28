package com.historycode.ui.page.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PersonCardCarousel extends CarouselComponent<PersonCardComponent> {

    @FindBy(xpath = "//div[@class='streetcodeSliderComponent']//div[contains(@class, 'slick-slide') and not(contains(@class,'slick-cloned')) and not(@dir)]")
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

    @Override
    public List<PersonCardComponent> getCarouselItems() {
        return itemElements.stream()
                .map(el -> new PersonCardComponent(driver, el))
                .collect(Collectors.toList());
    }

    @Override
    public PersonCardComponent getActiveSlideComponent() {
        return new PersonCardComponent(driver, slickActive);
    }
}
