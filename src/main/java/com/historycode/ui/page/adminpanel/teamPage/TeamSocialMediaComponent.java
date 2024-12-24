package com.historycode.ui.page.adminpanel.teamPage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamSocialMediaComponent extends BaseComponent {

    @FindBy(xpath = "")
    private WebElement logo;
    @FindBy(xpath = "")
    private WebElement platform;
    @FindBy(xpath = "")
    private WebElement url;

    public TeamSocialMediaComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
