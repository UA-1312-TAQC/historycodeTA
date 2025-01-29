package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
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
        toggleProgressBar();
        if(!isProgressBarVisible()) {
            toggleProgressBar();
        }
        if (sectionNumber > 0 && sectionNumber <= sectionNumbers.size()) {
            WebElement element = sectionNumbers.get(sectionNumber - 1);
            waitUntilElementClickable(element);
            actions.moveToElement(element).click().perform();
        } else {
            throw new IllegalArgumentException("Invalid section number");
        }
    }

    public void toggleProgressBar() {
        actions.moveToElement(hideButton).click().perform();
        waitUntilElementClickable(sectionNumbers.getFirst());
    }

    public boolean isProgressBarVisible() {
        return Objects.requireNonNull(this.hideButton.getDomAttribute("class")).contains("visible");
    }

    public int getSectionsCount() {
        return sectionNumbers.size();
    }
}
