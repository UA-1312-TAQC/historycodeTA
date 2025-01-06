package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.ArtGalleryCardComponent;
import com.historycode.ui.page.streetCodePage.components.PaginationComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ArtGalleryCarousel extends BaseCarousel{
    @FindBy(xpath = ".//div[@class='slider-item-container']/div/div")
    private List<WebElement> artGalleryCardNodes;

    @FindBy(xpath = ".//div[@class='artGallerySliderContainer']//button[@class='slick-arrow slick-prev']")
    private WebElement leftArrow;

    @FindBy(xpath = ".//div[@class='artGallerySliderContainer']//button[@class='slick-arrow slick-next']")
    private WebElement rightArrow;

    @FindBy(xpath = ".//div[@class='artGallerySliderContainer']//ul[@class='slick-dots']")
    private WebElement paginationNode;

    private List<ArtGalleryCardComponent> artGalleryCards;
    private PaginationComponent pagination;

    public ArtGalleryCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.artGalleryCards = artGalleryCardNodes.stream()
                .map(node -> new ArtGalleryCardComponent(driver, node))
                .collect(Collectors.toList());
        this.pagination = new PaginationComponent(driver, paginationNode);
    }

    @Override
    public boolean hasArrows() {
        try {
            return leftArrow.isDisplayed() && rightArrow.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickNext() {
        if (hasArrows()) {
            rightArrow.click();
        }
    }

    public void clickPrevious() {
        if (hasArrows()) {
            leftArrow.click();
        }
    }

    public List<ArtGalleryCardComponent> getCards() {
        return artGalleryCards;
    }

    public PaginationComponent getPagination() {
        return pagination;
    }

    public void goToSlide(int index) {
        pagination.selectDot(index);
    }

    public int getCurrentSlideIndex() {
        return pagination.getActiveIndex();
    }

    public int getTotalSlides() {
        return pagination.getTotalDots();
    }
}
