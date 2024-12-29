package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NavigationComponent extends BaseComponent {

    @FindBy(xpath = "//a[text()='Попередня новина']")
    private WebElement previousNewsLink;

    @FindBy(xpath = "//a[text()='Наступна новина']")
    private WebElement nextNewsLink;

    public NavigationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickPreviousNewsLink() {
        if (isPreviousNewsLinkEnabled()) {
            previousNewsLink.click();
        } else {
            throw new IllegalStateException("Previous news link is not enabled.");
        }
    }
    public void clickNextNewsLink() {
        if (isNextNewsLinkEnabled()) {
            nextNewsLink.click();
        } else {
            throw new IllegalStateException("Next news link is not enabled.");
        }
    }

    public boolean isPreviousNewsLinkEnabled() {
        try {
            return previousNewsLink.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isNextNewsLinkEnabled() {
        try {
            return nextNewsLink.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
