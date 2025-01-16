package com.historycode.ui.page.streetcodespage;

import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetCodesPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;

    @Getter
    @FindBy(xpath = "//div[@class='steetcodeCatalogContainer']")
    private WebElement containerRootNode;
    @Getter
    private final CatalogComponent streetCodesCatalogComponent;

    public StreetCodesPage(WebDriver driver) {
        super(driver);
        streetCodesCatalogComponent = new CatalogComponent(driver, containerRootNode);
    }

    public WebElement getStreetCodesTitleElement() {
        return streetCodesTitleNode;
    }

    public String getStreetCodesTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getStreetsCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }

    public StreetCodePage clickCatalogItemByName(int index) {
        streetCodesCatalogComponent.getItemComponents().get(index).getNameNode().click();
        return new StreetCodePage(driver);
    }
    public void ScrollDownStreetCodes() {
        waitForElementThenScrollUntilLoaderDisappears(getContainerRootNode());
    }
}
