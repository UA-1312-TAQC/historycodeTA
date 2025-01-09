package com.historycode.ui.page.streetcodes;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class CatalogComponent extends BaseComponent {

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

    public boolean isCatalogElementVisible(int index) {
        return getCatalogElement(index).isDisplayed();
    }

    public void clickCatalogElement(int index) {
        getCatalogElement(index).click();
    }

}
