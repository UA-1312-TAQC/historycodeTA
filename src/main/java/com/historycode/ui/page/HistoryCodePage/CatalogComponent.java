package com.historycode.ui.page.HistoryCodePage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CatalogComponent extends BaseComponent {

    @Getter
    private ChronologyComponent chronologyComponent;

    @Getter
    @FindBy(xpath = ".//a[@class='catalogItem']")
    private List<WebElement> catalogElements;

    public CatalogComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
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