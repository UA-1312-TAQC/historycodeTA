package com.historycode.ui.page.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class NewsCardCarousel extends CarouselComponent<NewsCardComponent> {

    @FindBy(css = ".news-carousel-item")
    private List<WebElement> itemElements;

    public NewsCardCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public List<NewsCardComponent> getCarouselItems() {
        return itemElements.stream()
                .map(element -> new NewsCardComponent(driver, element))
                .collect(Collectors.toList());
    }
}

