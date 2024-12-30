package com.historycode.ui.page.streetcodes;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StreetCodesPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;

    @FindBy(xpath = "//div[@class='steetcodeCatalogContainer']")
    private WebElement containerRootNode;

    private final CatalogComponent streetCodesCatalogComponent;

    public StreetCodesPage(WebDriver driver) {
        super(driver);
        streetCodesCatalogComponent = new CatalogComponent(driver, containerRootNode);
    }

    public String getStreetCodesTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getStreetsCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }

    public List<String> getCatalogNames() {
        return streetCodesCatalogComponent.getItemComponents()
                .stream()
                .map(CatalogItemComponent::getName)
                .collect(Collectors.toList());
    }

    public List<String> getCatalogDescriptions() {
        return streetCodesCatalogComponent.getItemComponents()
                .stream()
                .map(CatalogItemComponent::getDescription)
                .collect(Collectors.toList());
    }

    private void validateIndex(int index, List<?> list) {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    public String getCatalogItemName(int index) {
        List<String> catalogNames = getCatalogNames();
        validateIndex(index, catalogNames);

        return catalogNames.get(index);
    }

    public String getCatalogItemDescription(int index) {
        List<String> catalogDescriptions = getCatalogDescriptions();
        validateIndex(index, catalogDescriptions);

        return catalogDescriptions.get(index);
    }

    public StreetCodePage clickCatalogItem(int index) {

        List<CatalogItemComponent> items = streetCodesCatalogComponent.getItemComponents();
        validateIndex(index, items);

        items
            .get(index)
            .getRootNode()
            .click();

        return new StreetCodePage(driver);
    }
}
