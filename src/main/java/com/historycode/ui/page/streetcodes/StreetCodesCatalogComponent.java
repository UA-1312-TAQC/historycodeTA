package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
class StreetCodesCatalogComponent extends BaseComponent {

    private List<StreetCodesItemComponent> streetCodesCatalogItems;

    StreetCodesCatalogComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public int getStreetCodeItemCount() {
        return streetCodesCatalogItems.size();
    }
}
