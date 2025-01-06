package com.historycode.ui.component.adminPanel.gridAdminPanel;


import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//This is component which is shown when table contains no rows
public class NoDataComponent extends BaseComponent {

    @FindBy(css = ".ant-empty-image svg")
    private WebElement image;

    @FindBy(css = ".ant-empty-description")
    private WebElement description;

    public NoDataComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getImage() {
        return image;
    }

    public String getDescription() {
        return description.getText().trim();
    }
}
