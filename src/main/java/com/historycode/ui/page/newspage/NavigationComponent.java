package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NavigationComponent extends BaseComponent {

    @FindBy(xpath = "//a[text()='Попередня новина']")
    private WebElement previousNewsLink;

    @FindBy(xpath = "//a[text()='Наступна новина']")
    private WebElement nextNewsLink;

    public NavigationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isPreviousNewsLinkDisplay() {
        waitUntilElementVisible(previousNewsLink);
        return previousNewsLink.isDisplayed();
    }

    public boolean isNextNewsLinkDisplay() {
        waitUntilElementVisible(nextNewsLink);
        return nextNewsLink.isDisplayed();
    }

    public void clickPreviousNewsLink(){
        waitUntilElementClickable(previousNewsLink);
        previousNewsLink.click();
    }

    public void clickNextNewsLink(){
        waitUntilElementClickable(nextNewsLink);
        nextNewsLink.click();
    }
}
