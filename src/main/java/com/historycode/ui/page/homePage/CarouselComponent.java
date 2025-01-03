package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public abstract class CarouselComponent<T extends BaseComponent> extends BaseComponent {

    @FindBy(xpath = "//button[contains(@class, 'slick-prev')]")
    protected WebElement leftArrow;

    @FindBy(xpath = "//button[contains(@class, 'slick-next')]")
    protected WebElement rightArrow;

    public CarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }


    public void clickLeftArrow() {
        leftArrow.click();
    }


    public void clickRightArrow() {
        rightArrow.click();
    }

    public abstract List<T> getCarouselItems();
}
