package com.historycode.ui.page.streetcodes;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getCatalogTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getCatalogCaption() {
        return streetsCodesCaptionNode.getText();
    }

    public int getStreetCodesItemCount() {
        return streetCodesCatalogComponent.getStreetCodeItemCount();
    }
}
