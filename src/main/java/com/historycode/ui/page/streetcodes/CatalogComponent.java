package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Getter
class CatalogComponent extends BaseComponent {
    private final String CATALOG_ITEMS_LOCATOR = ".//a[@class='catalogItem']";

    @FindBy(xpath = CATALOG_ITEMS_LOCATOR)
    private List<WebElement> catalogElements;
    private final List<CatalogItemComponent> itemComponents;

    CatalogComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        itemComponents = catalogElements.stream()
                .map(element -> new CatalogItemComponent(driver, element))
                .collect(Collectors.toList());
    }

    private void updateItemsAfterScroll() {
        List<WebElement> updatedElements = rootElement.findElements(By.xpath(CATALOG_ITEMS_LOCATOR));

        for (WebElement element: updatedElements) {
            if (!catalogElements.contains(element)) {
                catalogElements.add(element);
                itemComponents.add(new CatalogItemComponent(driver, element));
            }
        }
    }

    public void scrollAndWaitForNewItems() {
        int initialSize = catalogElements.size();

        scrollToEndOfPage();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(CATALOG_ITEMS_LOCATOR), initialSize));

        updateItemsAfterScroll();
    }
}