package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.ArtGalleryCarousel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArtGalleryComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='art-gallery']//h1")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='artGallerySliderContainer']")
    private WebElement carouselRoot;

    private ArtGalleryCarousel carousel;

    public ArtGalleryComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new ArtGalleryCarousel(driver, carouselRoot);
    }

    public String getTitle() {
        return title.getText();
    }

    public void nextSlide() {
        carousel.clickNext();
    }

    public void previousSlide() {
        carousel.clickPrevious();
    }

    public List<ArtGalleryCardComponent> getArtCards() {
        return carousel.getCards();
    }

    public boolean hasNavigationArrows() {
        return carousel.hasArrows();
    }

    public void goToSlide(int index) {
        carousel.goToSlide(index);
    }

    public int getCurrentSlideIndex() {
        return carousel.getCurrentSlideIndex();
    }

    public int getTotalSlides() {
        return carousel.getTotalSlides();
    }
}
