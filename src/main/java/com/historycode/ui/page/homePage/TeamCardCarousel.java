package com.historycode.ui.page.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TeamCardCarousel extends CarouselComponent<TeamCardComponent> {
    @FindBy(css = ".team-carousel-item")
    private List<WebElement> itemElements;

    public TeamCardCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public List<TeamCardComponent> getCarouselItems() {
        return itemElements.stream()
                .map(el -> new TeamCardComponent(driver, el))
                .collect(Collectors.toList());
    }
}
