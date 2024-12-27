package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
class StreetCodesItemComponent extends BaseComponent {
    @FindBy(xpath = ".//div/div[@class='heading']/p[1]")
    WebElement catalogItemName;

    @FindBy(xpath = ".//div/div[@class='heading']/p[2]")
    WebElement catalogItemDescription;

    StreetCodesItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}