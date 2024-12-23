package com.historycode.ui.page;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class StreetCodeCatalogPage extends BasePage {
    final By CATALOG_CONTAINER_LOCATOR = By.xpath("//div[@class='steetcodeCatalogContainer']");

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement catalogTitle;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement catalogCaption;

    CatalogContainerComponent catalogContainer;

    public StreetCodeCatalogPage(WebDriver driver) {
        super(driver);
        catalogContainer = new CatalogContainerComponent(driver, driver.findElement(CATALOG_CONTAINER_LOCATOR));
    }

    public String getCatalogTitle() {
        return catalogTitle.getText();
    }

    public String getCatalogCaption() {
        return catalogCaption.getText();
    }

    public List<String> getStreetItemNames() {
        return catalogContainer.getCatalogNames();
    }
}

class CatalogContainerComponent extends BaseComponent {
    final By CATALOG_ITEM_LOCATOR = By.xpath("/a[@class='catalogItem']");
    final By CATALOG_ITEM_TITLE_LOCATOR = By.xpath("/div/div[@class='heading']/p[1]");
    final By CATALOG_ITEM_DESCRIPTION_LOCATOR = By.xpath("/div/div[@class='heading']/p[2]");

    CatalogContainerComponent(WebDriver driver, WebElement catalogContainer) {
        super(driver, catalogContainer);
    }

    List<String> getCatalogNames() {
        List<String> itemNames = new ArrayList<>();

        List<WebElement> catalogItems = rootElement.findElements(CATALOG_ITEM_LOCATOR);
        for (WebElement webElement : catalogItems) {
            itemNames.add(webElement.findElement(CATALOG_ITEM_TITLE_LOCATOR).getText());
        }

        return itemNames;
    }
}
