package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class TopCarousel extends BaseComponent {
    @FindBy(css = ".slick-dots li")
    private List<WebElement> dots;

    @FindBy(css = ".slick-slide")
    private List<WebElement> slideElements;

    public TopCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public int getDotsCount() {
        return dots.size();
    }

    public void clickDot(int index) {
        dots.get(index).click();
    }


    public boolean isDotActive(int index) {
        String classes = dots.get(index).getAttribute("class");
        return classes != null && classes.contains("slick-active");
    }

    public void waitForAutoScroll(int previousActiveIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> dots.stream()
                        .anyMatch(dot -> Objects.requireNonNull(dot.getAttribute("class")).contains("slick-active") &&
                                dots.indexOf(dot) != previousActiveIndex));
    }
}
