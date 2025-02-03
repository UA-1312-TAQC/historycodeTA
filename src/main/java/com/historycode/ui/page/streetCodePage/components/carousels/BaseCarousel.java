package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.PaginationComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;

public abstract class BaseCarousel extends BaseComponent {
    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    protected WebElement leftArrow;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    protected WebElement rightArrow;

    @FindBy(xpath = ".//ul[@class='slick-dots']")
    protected WebElement paginationNode;

    @Getter
    protected PaginationComponent pagination;

    public BaseCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean hasArrows() {
        waitUntilElementVisible(leftArrow);
        waitUntilElementVisible(rightArrow);
        try {
            return leftArrow.isDisplayed() && rightArrow.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean hasPagination() {
        try {
            return paginationNode.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public BaseCarousel clickNextArrow() {
        if (hasArrows()) {
            rightArrow.click();
        }
        return this;
    }

    public BaseCarousel clickPreviousArrow() {
        if (hasArrows()) {
            leftArrow.click();
        }
        return this;
    }

    protected void initializePagination(WebDriver driver) {
        try {
            this.pagination = new PaginationComponent(driver, paginationNode);
        } catch (NoSuchElementException e) {
            //pagination is optional for carousel
        }
    }

    public void goToSlide(int index) {
        if (pagination != null) {
            pagination.selectDot(index);
        }
    }

    public int getCurrentSlideIndex() {
        return pagination != null ? pagination.getActiveIndex() : 0;
    }

    public int getTotalSlides() {
        return pagination != null ? pagination.getTotalDots() : 0;
    }
}
