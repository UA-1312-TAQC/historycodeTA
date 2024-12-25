package com.historycode.ui.page.streetcodes;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class StreetCodesPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitle;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaption;

    @FindBy(xpath = "//div[@class='steetcodeCatalogContainer']")
    private WebElement rootContainerElement;

    private final StreetCodesCatalogComponent streetCodesCatalogComponent;

    public StreetCodesPage(WebDriver driver) {
        super(driver);
        streetCodesCatalogComponent = new StreetCodesCatalogComponent(driver, rootContainerElement);
    }

    public String getCatalogTitle() {
        return streetCodesTitle.getText();
    }

    public String getCatalogCaption() {
        return streetsCodesCaption.getText();
    }

    public int getStreetCodesItemCount() {
        return streetCodesCatalogComponent.getStreetCodesItemCount();
    }
}
