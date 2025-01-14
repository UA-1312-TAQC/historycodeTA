package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ContactUsContentComponent extends BaseComponent {
    @Getter @FindBy(xpath = "//div[@class='contactUsContent']")
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

    public boolean areAllTextsDisplayed() {
        return text.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean areAllTextsNotEmpty() {
        return text.stream()
                .map(WebElement::getText)
                .allMatch(t -> t != null && !t.isEmpty());
    }

    public String getTextByIndex(int index) {
        if (index >= 0 && index < text.size()) {
            return text.get(index).getText();
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ". List size: " + text.size());
        }
    }

    public int getTextCount() {
        return text.size();
    }
}
