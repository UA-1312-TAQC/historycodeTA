package com.historycode.ui.page.HistoryCodePage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryCodePage extends BaseComponent {

    @FindBy(xpath = "//h1[@class='streetcodeCatalogHeading']")
    private WebElement streetCodesTitleNode;

    @FindBy(xpath = "//p[@class='streetcodeCatalogCaption']")
    private WebElement streetsCodesCaptionNode;
    @Getter
    @FindBy(xpath = "//a[@class='ant-breadcrumb-link activeLink']")
    private WebElement streetsCodesLink;

    @Getter
    private final CatalogComponent historyCodesCatalogComponent;


    public HistoryCodePage(WebDriver driver) {
        super(driver);
        this.historyCodesCatalogComponent = new CatalogComponent(driver);
        PageFactory.initElements(driver, this);

    }

    public String getStreetCodesTitle() {
        return streetCodesTitleNode.getText();
    }

    public String getStreetsCodesCaption() {
        return streetsCodesCaptionNode.getText();
    }


    public void clickOnCatalogComponent(int index) {
        sleep(10000);
        CatalogComponent catalogComponent = getHistoryCodesCatalogComponent();
        catalogComponent.clickCatalogElement(index);

    }
}