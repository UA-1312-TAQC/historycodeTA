package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PageNavigationBarComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='progressBarPopupContent']/div[span]")
    private List<WebElement> sectionNumbers;

    @FindBy(xpath = "./div")
    private WebElement hideButton;

    @FindBy(xpath = ".//div[@class='progressBarPopupContent']")
    private WebElement progressBar;

    public PageNavigationBarComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickSection(int sectionNumber) {
        if(!isProgressBarVisible()) {
            toggleProgressBar();
        }

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
        return progressBar.isDisplayed();
    }

    public int getSectionsCount() {
        return sectionNumbers.size();
    }
}
