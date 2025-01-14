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

    public String getEmailText() {
        return emailText.getText();
    }

    public String getEmailLink() {
        return emailLink.getAttribute("href");
    }

    public boolean isEmailTextDisplayed() {
        return emailText.isDisplayed();
    }

    public boolean isEmailLinkDisplayed() {
        return emailLink.isDisplayed();
    }

    public boolean areSocialNetworksDisplayed() {
        return socialNetworks.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean areSocialNetworksNotEmpty() {
        return socialNetworks.stream()
                .map(WebElement::getText)
                .allMatch(text -> text != null && !text.isEmpty());
    }

    public void clickOnEmailLink() {
        emailLink.click();
    }

    public void clickOnSocialNetwork(String networkName) {
        boolean found = false;

        for (WebElement socialNetwork : socialNetworks) {
            if (socialNetwork.getText().equalsIgnoreCase(networkName)) {
                socialNetwork.click();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Social network not found: " + networkName);
        }
    }

}
