package com.historycode.ui.component.header;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BaseComponent {

    @FindBy(xpath = "//div[@class = 'logoContainer']")
    WebElement logoNode;

    @FindBy(xpath = "//div[@class = 'searchHeaderSkeleton']")
    WebElement searchNode;

    @FindBy(xpath = "//div[@class = 'burgerMenuContainer']")
    WebElement burgerMenuNode;

    @FindBy(xpath = "//button[contains(@class, 'loginBtn')]")
    WebElement joinBtnNode;

    @Getter
    SearchElement searchElement;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        searchElement = new SearchElement(driver, searchNode);
    }
}
