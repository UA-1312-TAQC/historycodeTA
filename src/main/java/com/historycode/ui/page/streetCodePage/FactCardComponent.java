package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FactCardComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement image;

    @FindBy(xpath = "")
    private WebElement title;

    @FindBy(xpath = "")
    private WebElement description;

    @FindBy(xpath = "")
    private WebElement hoverDescription;

    public FactCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void click() {
        rootElement.click();
    }

    public void hover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(image).perform();
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getHoverDescription() {
        return hoverDescription.getText();
    }

    public String getImageUrl() {
        return image.getAttribute("src");
    }
}
