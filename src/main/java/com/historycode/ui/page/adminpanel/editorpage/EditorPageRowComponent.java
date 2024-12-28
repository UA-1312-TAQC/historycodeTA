package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditorPageRowComponent  extends BaseComponent {
    @FindBy(xpath = "")
    WebElement title;
    @FindBy(xpath = "")
    WebElement picture;
    @FindBy(xpath = "")
    WebElement deleteAction;
    @FindBy(xpath = "")
    WebElement editAction;

    public EditorPageRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
