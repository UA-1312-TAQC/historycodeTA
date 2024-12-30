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

public class TopCarousel extends BaseComponent {
    @FindBy(css = ".carousel-dots .dot")
    private List<WebElement> dots;

    @FindBy(css = ".top-carousel-item")
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
        return classes != null && classes.contains("active");
    }

    public void waitForAutoScroll(int previousActiveIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            for (int i = 0; i < getDotsCount(); i++) {
                if (isDotActive(i) && i != previousActiveIndex) {
                    return true;
                }
            }
            return false;
        });
    }
}
