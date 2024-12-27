package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarouselsComponent extends BaseComponent {
    private final WebElement leftArrow;
    private final WebElement rightArrow;

    public CarouselsComponent(WebDriver driver, WebElement rootElement,
                             WebElement leftArrow, WebElement rightArrow) {
        super(driver, rootElement);
        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
    }
}
