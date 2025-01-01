package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class addButtonElement extends BaseComponent {
    private static final String BUTTON_XPATH = "//div[@class = 'container-justify-end']//button[contains(@class, 'categories')]";
    private static final String LABEL_XPATH = "//div[@class = 'container-justify-end']//button[contains(@class, 'categories')]//span";

    @FindBy(xpath = BUTTON_XPATH)
    private WebElement button;
    @FindBy(xpath = LABEL_XPATH)
    private WebElement label;

    public addButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickButton() {
        this.button.click();
    }

    public String getButtonText() {
        return label.getText();
    }
}
