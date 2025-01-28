package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TopCarousel extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//ul[contains(@class, 'slick-dots')]//li")
    private List<WebElement> dots;

    @FindBy(xpath = ".//ul[contains(@class, 'slick-dots')]//li[contains(@class, 'slick-active')]")
    private WebElement activeDot;

    @Getter
    @FindBy(xpath = "//div[contains(@class,'top-carousel')]//div[contains(@class, 'slick-slide') and not(contains(@class,'slick-cloned')) and not(@dir)]")
    private List<WebElement> slideElements;

    @Getter
    @FindBy(xpath = "//div[contains(@class,'top-carousel')]//div[contains(@class, 'slick-slide') and contains(@class,'slick-active')]")
    private WebElement activeSlide;

    public TopCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public int getDotsCount() {
        return dots.size();
    }

    public void clickDot(int index) {
        if (index >= 0 && index < dots.size()) {
            dots.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Invalid dot index: " + index);
        }
    }

    public boolean isDotActive(int index) {
        if (index >= 0 && index < dots.size()) {
            String classes = dots.get(index).getAttribute("class");
            return classes != null && classes.contains("slick-active");
        }
        return false;
    }

    public void waitForAutoScroll(int previousActiveIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> dots.stream()
                        .anyMatch(dot -> {
                            String classes = dot.getAttribute("class");
                            return classes != null && classes.contains("slick-active") && dots.indexOf(dot) != previousActiveIndex;
                        }));
    }

    public String getActiveSlideImage() {
        if (activeSlide != null) {
            return activeSlide.getAttribute("src");
        }
        return null;
    }

}
