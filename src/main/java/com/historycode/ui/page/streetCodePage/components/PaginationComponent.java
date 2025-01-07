package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationComponent extends BaseComponent {
    @FindBy(xpath = ".//ul[@class='slick-dots']//li")
    private List<WebElement> dotButtons;

    @FindBy(xpath = ".//ul[@class='slick-dots']//li[@class='slick-active']")
    private WebElement activeDot;
    private List<WebElement> paginationDots;

    public PaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void selectDot(int index) {
        if (index >= 0 && index < dotButtons.size()) {
            dotButtons.get(index).click();
        }
    }

    public int getActiveIndex() {
        for (int i = 0; i < dotButtons.size(); i++) {
            if (dotButtons.get(i).getAttribute("class").contains("slick-active")) {
                return i;
            }
        }
        return 0;
    }
    public int getTotalDots() {
        return paginationDots.size();
    }

    public boolean isDotActive(int index) {
        if (index >= 0 && index < dotButtons.size()) {
            return dotButtons.get(index).getAttribute("class").contains("slick-active");
        }
        return false;
    }
}
