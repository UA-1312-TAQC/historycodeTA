package com.historycode.ui.page.streetcodecatalogpage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;


public class CatalogComponent extends BaseComponent {
    @FindBy(xpath = ".//a[@class='catalogItem']")
    private List<WebElement> catalogElements;

    public CatalogComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<CatalogItemComponent> getItemComponents() {
        return Optional.ofNullable(catalogElements)
                .orElse(List.of())
                .stream()
                .map(element -> new CatalogItemComponent(driver, element))
                .toList();
    }

    public void updateElementsAfterScroll() {
        catalogElements = rootElement.findElements(By.xpath(".//a[@class='catalogItem']"));
    }

    public List<String> getCatalogNames() {
        return getItemComponents()
                .stream()
                .map(CatalogItemComponent::getName)
                .toList();
    }

    public List<String> getCatalogDescriptions() {
        return getItemComponents()
                .stream()
                .map(CatalogItemComponent::getDescription)
                .toList();
    }

    public String getCatalogItemName(int index) {
        return getCatalogNames().get(index);
    }

    public String getCatalogDescriptions(int index) {
        return getCatalogDescriptions().get(index);
    }

    public WebElement getCatalogElement(int index) {
        if (index >= 0 && index < catalogElements.size()) {
            return catalogElements.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public void clickCatalogElement(int index) {
        getCatalogElement(index).click();

    }
}