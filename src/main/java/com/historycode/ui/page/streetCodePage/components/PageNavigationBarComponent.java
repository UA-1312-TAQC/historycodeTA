package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PageNavigationBarComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='progressBarPopupContent']/div/span")
    private List<WebElement> sectionNumbers;

    @FindBy(xpath = ".//div[@class='progressBarPopupContainer']")
    private WebElement hideButton;

    public PageNavigationBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickSection(int sectionNumber) {
        if (sectionNumber > 0 && sectionNumber <= sectionNumbers.size()) {
            sectionNumbers.get(sectionNumber - 1).click();
        } else {
            throw new IllegalArgumentException("Invalid section number");
        }
    }

    public void toggleProgressBar() {
        hideButton.click();
    }

    public boolean isProgressBarVisible() {
        return sectionNumbers.get(0).isDisplayed();
    }

    public int getSectionsCount() {
        return sectionNumbers.size();
    }
}
