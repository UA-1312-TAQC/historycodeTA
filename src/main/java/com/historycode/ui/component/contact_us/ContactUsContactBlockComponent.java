package com.historycode.ui.component.contact_us;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[2]/div[1]/div")
@Getter
public class ContactUsContactBlockComponent extends BaseComponent {
    private List<WebElement> socialNetworks;
    private WebElement emailText;

    public ContactUsContactBlockComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
