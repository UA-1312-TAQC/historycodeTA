package com.historycode.ui.page.streetcodecatalogpage;

import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetCodeCatalogPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;

    @FindBy(xpath = "//div[@class='steetcodeCatalogContainer']")
    private WebElement containerRootNode;

    @Getter
    private final CatalogComponent streetCodesCatalogComponent;

    public StreetCodeCatalogPage(WebDriver driver) {
        super(driver);
        streetCodesCatalogComponent = new CatalogComponent(driver, containerRootNode);
    }

    public WebElement getStreetCodesTitle() {
        return streetCodesTitleNode;
    }

    public String getStreetsCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }

    public StreetCodePage clickCatalogItemByIndex(int index) {
        waitUntilElementVisible(streetCodesCatalogComponent.getItemComponents().getFirst().getNameNode());
        scrollToElement(streetCodesCatalogComponent.getItemComponents().get(index).getNameNode());
        waitUntilElementVisible(streetCodesCatalogComponent.getItemComponents().get(index).getNameNode());
        streetCodesCatalogComponent.getItemComponents().get(index).getNameNode().click();
        return new StreetCodePage(driver);
    }

    public void clickOnCatalogComponent(int index) {
        CatalogComponent catalogComponent = getStreetCodesCatalogComponent();
        waitUntilElementVisible(containerRootNode);
        catalogComponent.clickCatalogElement(index);
    }
}
