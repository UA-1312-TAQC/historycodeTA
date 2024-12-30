package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VerticalProgressComponent extends BaseComponent {
    @FindBy(xpath = "")
    private List<WebElement> sectionNumbers;

    @FindBy(xpath = "")
    private WebElement hideButton;

    public VerticalProgressComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void toggle() {
        if (isHidden()) {
            hideButton.click();
        } else {
            hideButton.click();
        }
    }

    public boolean isHidden() {
        return !rootElement.isDisplayed();
    }
}
