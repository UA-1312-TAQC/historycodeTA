package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionsComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement categories;
    @FindBy(xpath = "")
    WebElement tags;
    @FindBy(xpath = "")
    WebElement contexts;
    @FindBy(xpath = "")
    WebElement positions;

    public SectionsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
