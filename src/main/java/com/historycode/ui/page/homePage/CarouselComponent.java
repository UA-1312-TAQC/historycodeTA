package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CarouselComponent<T extends BaseComponent> extends BaseComponent {

    @FindBy(css = ".carousel-item")
    private List<WebElement> carouselItems;

    @FindBy(css = ".carousel-arrow-left")
    private WebElement leftArrow;

    @FindBy(css = ".carousel-arrow-right")
    private WebElement rightArrow;

    private final Class<T> componentClass;

    public CarouselComponent(WebDriver driver, WebElement rootElement, Class<T> componentClass) {
        super(driver, rootElement);
        PageFactory.initElements(rootElement, this);
        this.componentClass = componentClass;
    }

    public List<T> getCarouselItems() {
        return carouselItems.stream()
                .map(e -> {
                    try {
                        return componentClass.getConstructor(WebDriver.class, WebElement.class)
                                .newInstance(driver, e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Cant create component: " + componentClass.getSimpleName(), ex);
                    }
                })
                .collect(Collectors.toList());
    }

    public void clickLeftArrow() {
        leftArrow.click();
    }

    public void clickRightArrow() {
        rightArrow.click();
    }
}