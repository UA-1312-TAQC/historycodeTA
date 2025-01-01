package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ContactUsContentComponent extends BaseComponent {
    @Getter @FindBy(xpath = "//div[@class='']")
    private List<WebElement> text;
    @Getter @FindBy(xpath = "//div[@class='contactUsBtnContainer']")
    private WebElement button;

    public ContactUsContentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> getAllKeywords() {
        return text.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
