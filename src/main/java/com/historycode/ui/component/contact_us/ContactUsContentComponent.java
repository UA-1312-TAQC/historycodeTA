package com.historycode.ui.component.contact_us;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div[2]/div/div/div/div[3]")
@Getter
public class ContactUsContentComponent extends BaseComponent {
    private List<WebElement> text;
    private WebElement button;

    public ContactUsContentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
