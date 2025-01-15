package com.historycode.ui.page.streetcodespage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CatalogItemComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@class='heading']/p[1]")
    private WebElement nameNode;

    @Getter
    @FindBy(xpath = "//div[@class='catalogItemText']")
    private WebElement catalogItemTextArea;

    @FindBy(xpath = ".//div[@class='heading']/p[2]")
    private WebElement descriptionNode;

    public CatalogItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getName() {
        return nameNode.getText();
    }

    public String getDescription() {
        return descriptionNode.getText();
    }

}
