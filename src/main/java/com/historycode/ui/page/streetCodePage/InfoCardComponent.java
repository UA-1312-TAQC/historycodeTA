package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoCardComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement categoryTitle;

    public InfoCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void click() {
        rootElement.click();
    }

    public String getTitle() {
        return categoryTitle.getText();
    }
}
