package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RunningLineComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='tickerItem']")
    private WebElement tickerContent;

    public RunningLineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void hoverTickerLine() {
        Actions actions = new Actions(driver);
        actions.moveToElement(tickerContent).perform();
    }

    public String getTickerText() {
        return tickerContent.getText();
    }

    public boolean isTickerVisible() {
        return tickerContent.isDisplayed();
    }
}
