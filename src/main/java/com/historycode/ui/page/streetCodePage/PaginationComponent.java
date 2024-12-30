package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationComponent extends BaseComponent {
    @FindBy(xpath = "")
    private List<WebElement> paginationDots;

    public PaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickDot(int index) {
        paginationDots.get(index).click();
    }

    public int getActiveDotIndex() {
        for (int i = 0; i < paginationDots.size(); i++) {
            if (paginationDots.get(i).getAttribute("class").contains("active")) {
                return i;
            }
        }
        return -1;
    }

    public int getTotalDots() {
        return paginationDots.size();
    }
}
