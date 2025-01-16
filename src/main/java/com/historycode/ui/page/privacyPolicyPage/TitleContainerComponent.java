package com.historycode.ui.page.privacyPolicyPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TitleContainerComponent extends BaseComponent {

    @FindBy(xpath = "./div[@class='title']")
    private WebElement titleContainer;

    public TitleContainerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String titleName() {
        WebElement nameElement = titleContainer.findElement(By.xpath("./div[@class='titleBig']"));
        return nameElement.getText();
    }

    public String subTitle() {
        WebElement subTitleElement = titleContainer.findElement(By.xpath("./div[@class='subTitle']"));
        return subTitleElement.getText();
    }

    public String disclaimer() {
        WebElement disclaimerElement = titleContainer.findElement(By.xpath("./div[@class='disclaimer']"));
        return disclaimerElement.getText();
    }
}
