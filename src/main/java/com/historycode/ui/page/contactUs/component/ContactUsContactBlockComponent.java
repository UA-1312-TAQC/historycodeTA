package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ContactUsContactBlockComponent extends BaseComponent {
    @Getter @FindBy(xpath = "//div[@class='socials']")
    private List<WebElement> socialNetworks;
    @Getter @FindBy(xpath = "//div[@class='email']")
    private WebElement emailText;
    @Getter @FindBy(xpath = "//div[@class='emailLink']")
    private WebElement emailLink;

    public ContactUsContactBlockComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> getAllKeywords() {
        return socialNetworks.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
