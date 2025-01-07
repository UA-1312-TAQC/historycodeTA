package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class addButtonElement extends BaseComponent {
    private static final String BUTTON_XPATH = ".//button[contains(@class, 'add-button')]";
    private static final String LABEL_XPATH = ".//button[contains(@class, 'add-button')]//span";

    @FindBy(xpath = BUTTON_XPATH)
    private WebElement button;
    @FindBy(xpath = LABEL_XPATH)
    private WebElement label;

    public addButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickButton() {
        BasePage.moveToElement(driver, button);
        button.click();
    }

    public String getButtonText() {
        return label.getText();
    }
}
