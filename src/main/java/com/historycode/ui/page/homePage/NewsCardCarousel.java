package com.historycode.ui.page.homePage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NewsCardCarousel extends CarouselComponent<NewsCardComponent> {

    @FindBy(xpath = "//div[@class='newsSliderContent']//div[contains(@class,'slick-slide') and not(contains(@class,'slick-cloned')) and not(@dir)]")
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

