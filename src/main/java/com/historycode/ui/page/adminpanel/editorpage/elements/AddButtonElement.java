package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.EditorBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddButtonElement extends BaseComponent {

    @FindBy(xpath = "./button[contains(@class, 'add-button')]")
    private WebElement button;
    @FindBy(xpath = "./button[contains(@class, 'add-button')]//span")
    private WebElement label;

    public AddButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickButton() {
        button.click();
    }

    public String getButtonText() {
        return label.getText();
    }
}
