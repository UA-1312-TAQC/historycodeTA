package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddButtonElement extends BaseComponent {

    @Getter
    @FindBy(xpath = "./button[contains(@class, 'add-button')]")
    private WebElement button;
    @Getter
    @FindBy(xpath = "./button[contains(@class, 'add-button')]//span")
    private WebElement label;

    public AddButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click Add Button.")
    public void clickButton() {
        button.click();
    }

    public String getButtonText() {
        return label.getText();
    }
}
