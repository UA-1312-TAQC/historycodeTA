package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ContactUsContentComponent extends BaseComponent {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[3]")
    private List<WebElement> text;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[3]/button")
    private WebElement button;

    public ContactUsContentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
