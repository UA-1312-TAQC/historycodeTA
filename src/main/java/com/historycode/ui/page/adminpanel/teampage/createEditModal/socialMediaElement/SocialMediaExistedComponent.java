package com.historycode.ui.page.adminpanel.teampage.createEditModal.socialMediaElement;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SocialMediaExistedComponent extends BaseComponent {

    @FindBy(xpath = "//div[@class='link-container']/a")
    WebElement platform;
    @FindBy(xpath = "//div[@class='link-container']/p")
    WebElement link;
    @FindBy(xpath = "//div[@class='link-container']/span[@aria-label='delete']")
    WebElement deleteButton;

    public SocialMediaExistedComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void navigateToSocialMediaAccount() {
        platform.click();
    }

    public String getLink() {
        return link.getText().trim();
    }

    public void deleteSocialMedia() {
        deleteButton.click();
    }

}
