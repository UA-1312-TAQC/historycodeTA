package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.ArtGalleryCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ArtGalleryCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='slider-item-container']/div/div")
    private List<WebElement> artGalleryCardNodes;

    private final List<ArtGalleryCardComponent> artGalleryCards;

    public ArtGalleryCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.artGalleryCards = artGalleryCardNodes.stream()
                .map(node -> new ArtGalleryCardComponent(driver, node))
                .collect(Collectors.toList());
        initializePagination(driver);
    }

    public List<ArtGalleryCardComponent> getCards() {
        return artGalleryCards;
    }
}
