package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
class StreetCodesCatalogComponent extends BaseComponent {

    @FindBy(xpath = "//a[@class='catalogItem']")
    private List<WebElement> streetCodeElements;

    private final List<StreetCodesItemComponent> streetCodesCatalogItems;

    StreetCodesCatalogComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

        streetCodesCatalogItems = streetCodeElements.stream()
                .map(element -> new StreetCodesItemComponent(driver, element))
                .toList();
    }

    public int getStreetCodeItemCount() {
        return streetCodesCatalogItems.size();
    }
}
