package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsPersonasCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='figureSlideText']/div/p")
    private WebElement nameNode;
    public TagsPersonasCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get persona name")
    public String getName() {
        waitUntilElementVisible(nameNode);
        return nameNode.getText();
    }
}
