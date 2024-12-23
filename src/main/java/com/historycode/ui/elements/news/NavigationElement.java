package com.historycode.ui.elements.news;

import com.historycode.ui.elements.BaseElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationElement extends BaseElement {

    @FindBy(xpath = "//a[text()='Попередня новина']")
    private WebElement previousNewsLink;

    @FindBy(xpath = "//a[text()='Наступна новина']")
    private WebElement nextNewsLink;

    public NavigationElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPreviousNewsLink() {
        if (isPreviousNewsLinkEnabled()) {
            previousNewsLink.click();
        } else {
            throw new IllegalStateException("Previous news link is not enabled.");
        }
    }
    public void clickNextNewsLink() {
        if (isPreviousNewsLinkEnabled()) {
            nextNewsLink.click();
        } else {
            throw new IllegalStateException("Previous news link is not enabled.");
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
