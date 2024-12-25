package com.historycode.ui.page.streetcodes;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class StreetCodesPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;

    @FindBy(xpath = "//div[@class='steetcodeCatalogContainer']")
    private WebElement rootContainerNode;

    private final StreetCodesCatalogComponent streetCodesCatalogComponent;

    public StreetCodesPage(WebDriver driver) {
        super(driver);
        streetCodesCatalogComponent = new StreetCodesCatalogComponent(driver, rootContainerNode);
    }

    public String getStreetCodesTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getStreetCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }

    public List<String> getStreetCodesNames() {
        return streetCodesCatalogComponent.getStreetCodesCatalogItems()
                .stream()
                .map(item -> item.getCatalogItemName().getText())
                .toList();
    }
}
