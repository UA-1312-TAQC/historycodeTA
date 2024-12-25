package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
class StreetCodesCatalogComponent extends BaseComponent {

    @FindBy(xpath = ".//a[@class='catalogItem']")
    private List<WebElement> streetCodeElements;

    StreetCodesCatalogComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<StreetCodesItemComponent> getStreetCodesCatalogItems() {
        return streetCodeElements.stream()
                .map(element -> new StreetCodesItemComponent(driver, element))
                .toList();
    }
}
