package com.historycode.ui.page.streetcodes;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class StreetCodesPage extends BasePage {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;

    @Getter
    private CatalogComponent streetCodesCatalogComponent;
    @Getter
    private CatalogItemComponent catalogItemComponent;

    public StreetCodesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.streetCodesCatalogComponent = new CatalogComponent(driver);
        this.catalogItemComponent = new CatalogItemComponent(driver);
    }

    public String getStreetCodesTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getStreetsCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }


    public void clickOnCatalogItem(int index) {
        CatalogComponent catalogComponent = getStreetCodesCatalogComponent();
            catalogComponent.clickCatalogElement(index);

    }
}

